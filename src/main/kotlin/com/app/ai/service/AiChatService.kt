package com.app.ai.service

import com.app.ai.dto.AiChatRequest
import com.app.ai.dto.AiChatResponse
import org.springframework.ai.chat.client.ChatClient
import org.springframework.stereotype.Service

@Service
class AiChatService(
    private val chatClient: ChatClient,
    private val aiPromptService: AiPromptService
){
    fun chat(aiChatRequest: AiChatRequest): AiChatResponse {
        val prompt = aiPromptService.createPrompt(aiChatRequest.userInput)
        return AiChatResponse(chatClient.prompt(prompt).call().content())
    }
}