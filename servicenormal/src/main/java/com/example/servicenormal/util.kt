package com.example.servicenormal

import java.time.LocalDateTime

fun getDateTime():String{
    return LocalDateTime.now().toString()
}