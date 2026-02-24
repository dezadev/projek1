package com.umkm.pos.utils

import java.security.MessageDigest

object SecurityUtils {
    fun hashPin(pin: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        return digest.digest(pin.toByteArray()).joinToString("") { "%02x".format(it) }
    }

    fun isValidPin(pin: String): Boolean = pin.length in 6..8 && pin.all { it.isDigit() }
}
