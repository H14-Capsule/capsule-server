package com.hanghaecapsule.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AuthKeyGeneratorTest {
    @Test
    internal fun testAuthKeyGenerate() {
        // given when
        val authKey = AuthKeyGenerator.generate()

        // then
        val regex = Regex("[a-zA-Z0-9]+") // 영문 대,소문자 + 숫자 범위
        assertThat(authKey.length).isEqualTo(6)
        assertThat(regex.matches(authKey)).isTrue
    }
}
