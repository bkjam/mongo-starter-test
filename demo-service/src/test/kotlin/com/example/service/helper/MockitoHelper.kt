package com.example.service.helper

import org.mockito.Mockito

/**
 * Workaround solution from http://derekwilson.net/blog/2018/08/23/mokito-kotlin
 **/
object MockitoHelper {
    fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    @Suppress("UNCHECKED_CAST")
    fun <T> uninitialized(): T =  null as T
}