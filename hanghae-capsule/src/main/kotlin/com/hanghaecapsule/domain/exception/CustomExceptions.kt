package com.hanghaecapsule.domain.exception

class UnAuthorizedException(
    message: String = "인증에 실패하였습니다.",
) : RuntimeException(message)
