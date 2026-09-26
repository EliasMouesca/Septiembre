package com.elicapo.yaesseptiembre

import java.util.Calendar
import org.junit.Assert.assertEquals
import org.junit.Test

class SeptemberDateUtilsTest {
    @Test
    fun august31_returnsOneDay() {
        assertEquals(1, daysUntilSeptember(date(2025, Calendar.AUGUST, 31)))
    }

    @Test
    fun september_returnsZeroDays() {
        assertEquals(0, daysUntilSeptember(date(2025, Calendar.SEPTEMBER, 1)))
        assertEquals(0, daysUntilSeptember(date(2025, Calendar.SEPTEMBER, 30)))
    }

    @Test
    fun leapYearOctober_usesNextSeptember() {
        assertEquals(335, daysUntilSeptember(date(2024, Calendar.OCTOBER, 1)))
    }

    @Test
    fun decemberAndJanuary_crossYearBoundary() {
        assertEquals(244, daysUntilSeptember(date(2024, Calendar.DECEMBER, 31)))
        assertEquals(243, daysUntilSeptember(date(2025, Calendar.JANUARY, 1)))
    }

    private fun date(year: Int, month: Int, day: Int): Calendar =
        Calendar.getInstance().apply {
            clear()
            set(year, month, day)
        }
}
