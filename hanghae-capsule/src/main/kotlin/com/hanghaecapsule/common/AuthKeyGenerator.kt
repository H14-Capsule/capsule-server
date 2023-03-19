package com.hanghaecapsule.common

private val AUTH_KEY_SYMBOLS = (0..9) + ('A'..'Z') + ('a'..'z')
private const val AUTH_KEY_LENGTH = 6

object AuthKeyGenerator {
    fun generate(): String {
        return AUTH_KEY_SYMBOLS.shuffled()
            .subList(0, AUTH_KEY_LENGTH)
            .joinToString("")
    }
}
