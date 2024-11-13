package com.app.ai.service

import org.springframework.ai.chat.messages.Message
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.chat.prompt.PromptTemplate
import org.springframework.ai.chat.prompt.SystemPromptTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class AiPromptService(
    @Value("classpath:prompts/chat/system-message.st")
    private val systemResource: Resource,
    @Value("classpath:prompts/chat/user-message.st")
    private val userResource: Resource,
) {

    // 유저 입력을 메시지로 변환
    private fun createUserMessage(userInput: String): Message =
        PromptTemplate(userResource).createMessage(
            mapOf("userInput" to userInput)
        )

    // 시스템 메시지 생성
    private fun createSystemMessage(): Message =
        SystemPromptTemplate(systemResource).createMessage(
            mapOf("name" to "Jerry")
        )

    // 프롬프트 생성
    fun createPrompt(userInput: String): Prompt =
        Prompt(listOf(createUserMessage(userInput), createSystemMessage()))
}