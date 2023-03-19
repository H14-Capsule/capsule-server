package com.hanghaecapsule.domain.author

import com.hanghaecapsule.domain.exception.AlreadyAuthorizedAuthorException
import com.hanghaecapsule.domain.exception.ExpireAuthKeyException
import java.time.Duration
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

private const val AUTH_KEY_LENGTH = 6
private const val EXPIRE_AUTH_KEY_MINUTES = 3

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

    fun authorize(authKey: String, currentTime: LocalDateTime): Boolean {
        if (authorized) {
            throw AlreadyAuthorizedAuthorException()
        }

        val duration = Duration.between(lastIssueAuthKeyAt, currentTime)
        if (duration.toMinutes() >= EXPIRE_AUTH_KEY_MINUTES) {
            throw ExpireAuthKeyException()
        }

        if (authKey == this.authKey) {
            authorized = true
        }

        return authorized
    }

    fun isNotAuthorized(): Boolean = !authorized

    fun reIssueAuthKey(newAuthKey: String) {
        if (authorized) {
            throw AlreadyAuthorizedAuthorException()
        }

        authKey = newAuthKey
        lastIssueAuthKeyAt = LocalDateTime.now()
    }
}
