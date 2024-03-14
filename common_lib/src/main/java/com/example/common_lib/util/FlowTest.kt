package com.example.common_lib.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        flow {
            emit(1)
            delay(1000)
            emit(2)
            delay(1000)
            emit(3)
        }
            .onEach {
                println("$it is ready")
            }
            .buffer()
            .collect{
                delay(1000)
                println("$it is finish")
            }
    }
}