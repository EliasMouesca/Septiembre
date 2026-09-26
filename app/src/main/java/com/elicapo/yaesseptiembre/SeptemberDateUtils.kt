package com.elicapo.yaesseptiembre

import java.util.Calendar

internal fun daysUntilSeptember(calendar: Calendar): Int {
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
    val currentYear = calendar.get(Calendar.YEAR)

    if (currentMonth == Calendar.SEPTEMBER) {
        return 0
    }

    val targetYear = if (currentMonth < Calendar.SEPTEMBER) {
        currentYear
    } else {
        currentYear + 1
    }
    val septemberFirst = Calendar.getInstance(calendar.timeZone).apply {
        clear()
        set(targetYear, Calendar.SEPTEMBER, 1)
    }

    return if (currentMonth < Calendar.SEPTEMBER) {
        septemberFirst.get(Calendar.DAY_OF_YEAR) - currentDayOfYear
    } else {
        calendar.getActualMaximum(Calendar.DAY_OF_YEAR) - currentDayOfYear +
            septemberFirst.get(Calendar.DAY_OF_YEAR)
    }
}
