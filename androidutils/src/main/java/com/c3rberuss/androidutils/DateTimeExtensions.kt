package com.c3rberuss.androidutils

import java.util.*

val String.dateTimeFormat: String
    get() {

        val formatYMD = "yyyy-MM-dd"
        val formatHm = "HH:mm"

        if (this.split(" ").size == 2) {

            return when (this.split(" ")[1].split(":").size) {
                3 -> "$formatYMD $formatHm:ss"
                2 -> "$formatYMD $formatHm"
                else -> formatYMD
            }

        }

        if (this.contains(":")) {

            return when (this.split(":").size) {
                3 -> "$formatHm:ss"
                2 -> formatHm
                else -> formatYMD
            }

        }

        return formatYMD
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