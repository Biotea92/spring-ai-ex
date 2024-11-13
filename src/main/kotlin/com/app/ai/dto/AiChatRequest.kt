package com.app.ai.dto

import jakarta.validation.constraints.NotNull

data class AiChatRequest(
    @NotNull(message = "User input must not be null")
    val userInput: String
)
