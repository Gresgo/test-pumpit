package com.test.pumpit

import org.joda.time.DateTime
import org.junit.Test

import org.junit.Assert.*
import java.text.DateFormatSymbols
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        //val date = LocalDate.parse("2020-03-13T08:11:06Z")
        val date = DateTime.parse("2019-12-17T09:17:37Z")
        print(date.dayOfMonth.toString() + " " + DateFormatSymbols().months[date.monthOfYear - 1]  + ", " + date.year.toString())
        assertEquals(4, 2 + 2)
    }
}
