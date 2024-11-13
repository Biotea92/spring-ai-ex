package com.app.ai.dto

import jakarta.validation.constraints.NotBlank

data class AiDocumentChatRequest (
    @NotBlank(message = "User input must not be blank")
    val userInput: String = ""
)
