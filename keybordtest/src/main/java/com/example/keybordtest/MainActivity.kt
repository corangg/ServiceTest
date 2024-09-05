package com.example.keybordtest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    var test = ""

    private var dialogFlag = false
    private val inputDelayHandler = Handler(Looper.getMainLooper())
    private val inputDelayRunnable = Runnable { /*showQRDialog()*/ }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        event?.let {
            if(!dialogFlag){
                dialogFlag = true
                //다이얼로그 오픈
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1000)
                }
            }
        }

        return super.onKeyUp(keyCode, event)
    }

    suspend fun test(){
        delay(1000)
    }
}