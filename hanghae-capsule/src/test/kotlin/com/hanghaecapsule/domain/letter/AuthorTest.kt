package com.hanghaecapsule.domain.letter

import com.hanghaecapsule.domain.author.Author
import com.hanghaecapsule.domain.exception.AlreadyAuthorizedAuthorException
import com.hanghaecapsule.domain.exception.ExpireAuthKeyException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime

internal class AuthorTest {

    @DisplayName("작성자를 생성할 때, 인증키가 6글자가 아닌 경우에는 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = ["short", "veryLongAuthKey"])
    @EmptySource
    internal fun testInitLetter(authKey: String) {
        // when // then
        assertThrows<IllegalStateException> {
            Author(
                authKey = authKey,
                lastIssueAuthKeyAt = LocalDateTime.of(2023, 1, 1, 0, 0),
                email = "test@gmail.com",
            )
        }
    }

    @Test
    internal fun testAuthorize() {
        // given
        val authKey = "abcdef"
        val lastIssueAuthKeyAt = LocalDateTime.of(2023, 1, 1, 0, 0)
        val author = Author(
            authKey = authKey,
            lastIssueAuthKeyAt = lastIssueAuthKeyAt,
            email = "test@gmail.com",
        )

        // when
        val actual = author.authorize(authKey, lastIssueAuthKeyAt.plusMinutes(1))

        // then
        assertThat(actual).isTrue
    }

    @Test
    internal fun testAuthorizeByIllegalAuthKey() {
        // given
        val authKey = "abcdef"
        val lastIssueAuthKeyAt = LocalDateTime.of(2023, 1, 1, 0, 0)
        val author = Author(
            authKey = authKey,
            lastIssueAuthKeyAt = lastIssueAuthKeyAt,
            email = "test@gmail.com",
        )

        // when
        val actual = author.authorize("aaaaaa", lastIssueAuthKeyAt.plusMinutes(1))

        // then
        assertThat(actual).isFalse
    }

    @ParameterizedTest
    @ValueSource(longs = [3, 4, 5])
    internal fun testAuthorizeByExpireAuthKey(plusMinute: Long) {
        // given
        val authKey = "abcdef"
        val lastIssueAuthKeyAt = LocalDateTime.of(2023, 1, 1, 0, 0)
        val author = Author(
            authKey = authKey,
            lastIssueAuthKeyAt = lastIssueAuthKeyAt,
            email = "test@gmail.com",
        )

        // when // then
        assertThrows<ExpireAuthKeyException> { author.authorize(authKey, lastIssueAuthKeyAt.plusMinutes(plusMinute)) }
    }

    @Test
    internal fun testAuthorizeByAlreadyAuthorizedAuthor() {
        // given
        val authKey = "abcdef"
        val lastIssueAuthKeyAt = LocalDateTime.of(2023, 1, 1, 0, 0)
        val author = Author(
            authKey = authKey,
            lastIssueAuthKeyAt = lastIssueAuthKeyAt,
            email = "test@gmail.com",
        )

        author.authorize(authKey, lastIssueAuthKeyAt.plusMinutes(1))

        // when // then
        assertThrows<AlreadyAuthorizedAuthorException> { author.authorize(authKey, lastIssueAuthKeyAt.plusMinutes(2)) }
    }
}
