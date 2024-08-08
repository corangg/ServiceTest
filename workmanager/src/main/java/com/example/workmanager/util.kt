package com.example.workmanager

import java.time.LocalDateTime

fun getDateTime():String{
    return LocalDateTime.now().toString()
}