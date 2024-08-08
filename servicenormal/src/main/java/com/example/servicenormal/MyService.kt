package com.example.servicenormal

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log
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
}