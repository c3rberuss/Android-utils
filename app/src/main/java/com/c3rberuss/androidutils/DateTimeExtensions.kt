package com.c3rberuss.androidutils

import java.util.*

val String.dateFormat: String
    get() {

        var separator = "-"

        if (this.contains(" ") && this.split(" ").size == 2) {
            separator = if (this.split(" ")[0].contains("-")) "-" else "/"

            return when (this.split(" ")[0].split(separator)[0].length) {
                4 -> "yyyy${separator}MM${separator}dd"
                else -> "dd${separator}MM${separator}yyyy"
            }
        }

        if (this.contains(":")) {
            throw Exception("Wrong date format")
        }

        separator = if (this.contains("-")) "-" else "/"

        return when (this.split(separator)[0].length) {
            4 -> "yyyy${separator}MM${separator}dd"
            else -> "dd${separator}MM${separator}yyyy"
        }

    }

val String.dateTimeFormat: String
    get() {

        val formatHm = "HH:mm"

        if (this.split(" ").size == 2) {

            return when (this.split(" ")[1].split(":").size) {
                3 -> "$dateFormat $formatHm:ss"
                2 -> "$dateFormat $formatHm"
                else -> throw Exception("Wrong date format")
            }

        }

        if (this.contains(":")) {

            return when (this.split(":").size) {
                3 -> "$formatHm:ss"
                2 -> formatHm
                else -> throw Exception("Wrong time format")
            }

        }

        return dateFormat
    }

val Calendar.hour24: Int
    get() {
        return this.get(Calendar.HOUR_OF_DAY)
    }

val Calendar.hour: Int
    get() {
        return this.get(Calendar.HOUR)
    }

val Calendar.minutes: Int
    get() {
        return this.get(Calendar.MINUTE)
    }

fun Calendar.hourIsLessOrEqualsThan(hour: String): Boolean {
    val hour24 = hour.split(":")[0].toInt()
    val minutes = hour.split(":")[1].toInt()

    return this.hourIsLessThan(hour) || (this.hour24 == hour24 && this.minutes == minutes)
}

fun Calendar.hourIsGreaterThan(hour: String): Boolean {

    val hour24 = hour.split(":")[0].toInt()
    val minutes = hour.split(":")[1].toInt()

    return this.hour24 > hour24 || (this.hour24 == hour24 && this.minutes > minutes)
}

fun Calendar.hourIsGreaterOrEqualsThan(hour: String): Boolean {
    val hour24 = hour.split(":")[0].toInt()
    val minutes = hour.split(":")[1].toInt()

    return this.hour24 >= hour24 && this.minutes >= minutes
}

fun Calendar.hourIsLessThan(hour: String): Boolean {
    val hour24 = hour.split(":")[0].toInt()
    val minutes = hour.split(":")[1].toInt()

    return this.hour24 < hour24 || (this.hour24 == hour24 && this.minutes < minutes)
}

val Date.isToday: Boolean
    get() {
        val date = Calendar.getInstance().apply {
            time = this@isToday
        }

        val currentDate = Calendar.getInstance().apply {
            time = Date()
        }

        return date.month == currentDate.month &&
                date.dayOfMonth == currentDate.dayOfMonth &&
                date.year == currentDate.year
    }

fun Date.isSameDate(anotherDate: Date): Boolean {
    return this.monthOfYear == anotherDate.monthOfYear &&
            this.dayOfMonth == anotherDate.dayOfMonth &&
            this.dateYear == anotherDate.dateYear
}

fun Date.isSameTime(anotherDate: Date): Boolean {
    return this.hour24 == anotherDate.hour24 &&
            this.timeMinutes == anotherDate.timeMinutes
}

fun Date.isSameDateTime(anotherDate: Date): Boolean {
    return this.monthOfYear == anotherDate.monthOfYear &&
            this.dayOfMonth == anotherDate.dayOfMonth &&
            this.dateYear == anotherDate.dateYear &&
            this.hour24 == anotherDate.hour24 &&
            this.timeMinutes == anotherDate.timeMinutes
}

fun Date.afterDate(anotherDate: Date): Boolean {
    return when {
        this.dateYear < anotherDate.dateYear || this.dateYear == anotherDate.dateYear -> false
        this.monthOfYear < anotherDate.monthOfYear || this.monthOfYear == anotherDate.monthOfYear -> false
        this.dayOfMonth < anotherDate.dayOfMonth || this.dayOfMonth == anotherDate.dayOfMonth -> false
        else -> true
    }
}

fun Date.afterTime(anotherDate: Date): Boolean {
    return when {
        this.hour24 < anotherDate.hour24 || this.hour24 == anotherDate.hour24 -> false
        this.timeMinutes < anotherDate.timeMinutes || this.timeMinutes == anotherDate.timeMinutes -> false
        else -> true
    }
}

val Date.dayOfMonth: Int
    get() = Calendar.getInstance().run {
        time = this@dayOfMonth
        dayOfMonth
    }

val Date.dayOfWeek: Int
    get() = Calendar.getInstance().run {
        time = this@dayOfWeek
        dayOfWeek
    }

val Date.monthOfYear: Int
    get() = Calendar.getInstance().run {
        time = this@monthOfYear
        month
    }

val Date.dateYear: Int
    get() = Calendar.getInstance().run {
        time = this@dateYear
        year
    }

val Calendar.dayOfMonth: Int get() = this.get(Calendar.DAY_OF_MONTH)
val Calendar.dayOfWeek: Int get() = this.get(Calendar.DAY_OF_WEEK)
val Calendar.month: Int get() = this.get(Calendar.MONTH)
val Calendar.year: Int get() = this.get(Calendar.YEAR)

val Date.hour24: Int
    get() = Calendar.getInstance().run {
        time = this@hour24
        hour24
    }

val Date.hour: Int
    get() = Calendar.getInstance().run {
        time = this@hour
        hour
    }

val Date.timeMinutes: Int
    get() = Calendar.getInstance().run {
        time = this@timeMinutes
        minutes
    }