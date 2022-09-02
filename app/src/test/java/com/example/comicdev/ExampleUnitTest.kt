package com.example.comicdev

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun mutableList(){
        val numbers = mutableListOf("one", "two", "three", "four")
        numbers.add("five")
        assertEquals(5, numbers.size)
    }

    @Test
    fun collectionList(){
        val stringList = listOf("one", "two", "one")
        val stringSet = setOf("one", "two", "three")
        for(s in stringList) assertEquals(true, stringSet.contains(s))
    }
}