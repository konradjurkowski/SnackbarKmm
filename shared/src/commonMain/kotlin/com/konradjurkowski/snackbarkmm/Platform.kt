package com.konradjurkowski.snackbarkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform