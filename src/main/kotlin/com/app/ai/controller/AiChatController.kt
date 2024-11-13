package com.app.ai.controller

import com.app.ai.dto.AiChatRequest
import com.app.ai.dto.AiChatResponse
import com.app.ai.service.AiChatService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/chat")
class AiChatController(
    private val aiChatService: AiChatService
) {

    @PostMapping
    fun chat(@RequestBody aiChatRequest: AiChatRequest): AiChatResponse {
        return aiChatService.chat(aiChatRequest)
    }
}