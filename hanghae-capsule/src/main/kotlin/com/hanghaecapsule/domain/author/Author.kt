package com.hanghaecapsule.domain.author

import com.hanghaecapsule.domain.exception.UnAuthorizedException
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

private const val AUTH_KEY_LENGTH = 6

@Entity
class Author(
    @Column(name = "auth_key", length = AUTH_KEY_LENGTH, nullable = false)
    var authKey: String,

    @Column(name = "last_issue_auth_key_at")
    var lastIssueAuthKeyAt: LocalDateTime,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "authorized", nullable = false)
    var authorized: Boolean = false,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) {
    init {
        check(authKey.length == AUTH_KEY_LENGTH) { "인증키는 $AUTH_KEY_LENGTH 글자로 제한됩니다." }
    }

    fun authorize(authKey: String): Boolean {
        if (authorized) {
            throw UnAuthorizedException("이미 인증이 완료된 작성자입니다.")
        }

        if (authKey == this.authKey) {
            authorized = true
        }

        return authorized
    }

    fun isNotAuthorized(): Boolean = !authorized
}
