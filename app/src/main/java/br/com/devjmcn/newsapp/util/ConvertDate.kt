package br.com.devjmcn.newsapp.util

import java.time.ZonedDateTime

fun String.convertDate(): String {
    return ZonedDateTime.parse(this).toLocalDateTime().toString()
}