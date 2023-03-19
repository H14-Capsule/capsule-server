package com.hanghaecapsule.service.author

import com.hanghaecapsule.domain.author.AuthorRepository
import com.hanghaecapsule.service.external.service.SpyEmailSenderClient
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class AuthorSaveServiceTest {
    @Autowired
    private lateinit var authorRepository: AuthorRepository

    private lateinit var authorSaveService: AuthorSaveService

    @BeforeEach
    internal fun setUp() {
        authorSaveService = AuthorSaveService(
            authorRepository = authorRepository,
            emailSender = SpyEmailSenderClient()
        )
    }

//    @Test
//    internal fun testCreateAuthor() {
//        // given
//        val email = "test@Gmail.com"
//
//        // when
//        authorSaveService.createAuthor(email)
//
//        // then
//
//    }
}
