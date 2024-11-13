package com.app.ai.service

import com.app.ai.dto.AiDocumentChatRequest
import com.app.ai.dto.AiDocumentChatResponse
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor
import org.springframework.ai.reader.TextReader
import org.springframework.ai.transformer.splitter.TokenTextSplitter
import org.springframework.ai.vectorstore.SearchRequest
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class AiDocumentService(
    private val vectorStore: VectorStore,
    private val chatClient: ChatClient,
) {

    @Value("classpath:documents/rules-of-hooks.md")
    var mdResource: Resource? = null

    fun storeDocumentsFormMarkdown() {
        val textReader = TextReader(mdResource)
        val splitDocuments = TokenTextSplitter().split(textReader.read())
        vectorStore.add(splitDocuments)
    }

    fun chat(chatAiDocumentRequest: AiDocumentChatRequest): AiDocumentChatResponse {
        val response = chatClient.prompt()
            .advisors(
                QuestionAnswerAdvisor(
                    vectorStore, SearchRequest.defaults()
                )
            )
            .user(chatAiDocumentRequest.userInput)
            .call()
            .content()

        return AiDocumentChatResponse(response);
    }
}