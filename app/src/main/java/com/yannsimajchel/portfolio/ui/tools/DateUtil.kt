package com.yannsimajchel.portfolio.ui.tools

import java.util.*
import java.util.Calendar.*

fun getDiffYears(first: Date, last: Date): Int {
    val a: Calendar = getCalendar(first)
    val b: Calendar = getCalendar(last)
    var diff: Int = b.get(YEAR) - a.get(YEAR)
    if (a.get(MONTH) > b.get(MONTH) ||
        a.get(MONTH) === b.get(MONTH) && a.get(DATE) > b.get(DATE)
    ) {
        diff--
    }
    return diff
}

fun getCalendar(date: Date): Calendar {
    val cal: Calendar = getInstance(Locale.FRANCE)
    cal.time = date
    return cal
}