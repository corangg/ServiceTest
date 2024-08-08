package com.example.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MyWorkerManager(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    private val database: UserRoomDatabase = UserRoomDatabase.getDatabase(context)
    private val userDao: UserDao = database.userDao()

    override suspend fun doWork(): Result {
        var count = inputData.getInt("count", 0)

        while (true) {
            insertData(count)
            count += 1
            delay(60000)
        }

        return Result.success()
    }

    private suspend fun insertData(data: Int) {
        withContext(Dispatchers.IO) {
            val dateTime = getDateTime()
            val dataValue = Data(data, dateTime)
            userDao.insert(dataValue)
        }
    }
}