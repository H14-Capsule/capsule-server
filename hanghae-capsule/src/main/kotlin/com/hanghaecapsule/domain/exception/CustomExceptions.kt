package com.hanghaecapsule.domain.exception

open class UnAuthorizedException(
    message: String = "인증에 실패하였습니다.",
) : RuntimeException(message)

class ExpireAuthKeyException(
    message: String = "인증키의 유효기간이 만료되었습니다.",
) : UnAuthorizedException(message)

class AlreadyAuthorizedAuthorException(
    message: String = "이미 인증이 완료된 작성자입니다.",
) : UnAuthorizedException(message)
