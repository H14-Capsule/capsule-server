package com.hanghaecapsule.domain.letter

import com.hanghaecapsule.domain.author.Author
import org.junit.jupiter.api.DisplayName
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
}
