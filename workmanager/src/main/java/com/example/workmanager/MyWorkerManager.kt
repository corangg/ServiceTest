package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MyWorkerManager(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    private val database: UserRoomDatabase = UserRoomDatabase.getDatabase(context)
    private val userDao: UserDao = database.userDao()

    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            insertData()
        }
        return Result.success()
    }

    private suspend fun insertData() {
        val dateTime = getDateTime()
        val dataValue = Data(dateTime)
        userDao.insert(dataValue)
    }
}

class WorkerOne(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        delay(5000)
        Log.d("WorkerLog","One")
        return Result.success()
    }
}

class WorkerTwo(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        delay(3000)
        Log.d("WorkerLog","Two")
        return Result.success()
    }
}

class WorkerThree(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        delay(4000)
        Log.d("WorkerLog","Three")
        return Result.success()
    }
}