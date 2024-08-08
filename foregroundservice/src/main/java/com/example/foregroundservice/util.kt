package com.example.foregroundservice

import java.time.LocalDateTime

fun getDateTime():String{
    return LocalDateTime.now().toString()
}