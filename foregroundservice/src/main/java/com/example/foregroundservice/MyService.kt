package com.example.foregroundservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService : Service() {
    private lateinit var handler: Handler
    private lateinit var handlerThread: HandlerThread

    private val runnable = object : Runnable {
        override fun run() {
            CoroutineScope(Dispatchers.IO).launch{
                var count = 0
                while (true){
                    insertData(count)
                    count += 1
                    delay(60000)
                }
            }
        }
    }

    private lateinit var database: UserRoomDatabase
    private lateinit var userDao: UserDao

    override fun onCreate() {
        super.onCreate()

        handlerThread = HandlerThread("MyHandlerThread")
        handlerThread.start()

        handler = Handler(handlerThread.looper)

        database = UserRoomDatabase.getDatabase(this)
        userDao = database.userDao()

        startForegroundService()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.post(runnable)
        return START_STICKY
    }

    private suspend fun insertData(data: Int) {
        val dataValue = Data(data)
        userDao.insert(dataValue)
    }

    private fun startForegroundService() {
        val channelId = "ForegroundServiceChannel"
        val channelName = "My Foreground Service"

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val chan = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(chan)
        }

        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Foreground Service")
            .setContentText("test")
            .setSmallIcon(R.drawable.img)
            .build()

        startForeground(1, notification)
    }
}