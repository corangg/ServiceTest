package com.example.servicenormal

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.servicenormal.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        startService()
    }

    private fun startService(){
        Intent(this, MyService::class.java).also { intent ->
            startService(intent)
        }
    }

    private suspend fun getUsersFromDatabase(): List<Data> {
        return withContext(Dispatchers.IO) {
            userDao.getAllUsers()
        }
    }
}