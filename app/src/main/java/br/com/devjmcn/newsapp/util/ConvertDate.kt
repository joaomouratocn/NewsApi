package br.com.devjmcn.newsapp.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun String.convertDate(): String {
    val zonedDateTime = ZonedDateTime.parse(this)

    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
        .withLocale(Locale.getDefault())

    return zonedDateTime.format(formatter)
}