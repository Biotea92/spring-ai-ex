package com.app.ai.controller

import com.app.ai.dto.AiDocumentChatRequest
import com.app.ai.dto.AiDocumentChatResponse
import com.app.ai.service.AiDocumentService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/documents")
class AiDocumentController(
    private val aiDocumentService: AiDocumentService
) {

    @PostMapping("/markdown")
    fun createDocumentFromMarkDown() {
        return aiDocumentService.storeDocumentsFormMarkdown()
    }

    @PostMapping("/chat")
    fun chat(@RequestBody chatAiDocumentRequest: AiDocumentChatRequest): AiDocumentChatResponse {
        return aiDocumentService.chat(chatAiDocumentRequest)
    }
}