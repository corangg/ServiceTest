package com.example.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.workmanager.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding


    private lateinit var database: UserRoomDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        database = UserRoomDatabase.getDatabase(this)
        userDao = database.userDao()

        binding.button.setOnClickListener {
            lifecycleScope.launch{
                val data = getUsersFromDatabase()
                binding.count.text = data.size.toString()
            }
        }

        startWorkerManager()
    }

    private fun startWorkerManager(){
        val workRequest = PeriodicWorkRequestBuilder<MyWorkerManager>(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "MyPeriodicWork",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    private suspend fun getUsersFromDatabase(): List<Data> {
        return withContext(Dispatchers.IO) {
            userDao.getAllUsers()
        }
    }
}