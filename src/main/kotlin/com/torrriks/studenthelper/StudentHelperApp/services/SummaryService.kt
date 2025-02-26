package com.torrriks.studenthelper.StudentHelperApp.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class SummaryService(private val webClient: WebClient) {

    @Value("\${gemini.api.key}")
    private lateinit var apiKey: String

    @Value("\${gemini.api.model:gemini-pro}")
    private lateinit var model: String

    /**
     * Gets a summary of the provided text using Google Gemini API
     *
     * @param text The text to summarize
     * @return A summary of the text
     */
    fun getSummary(text: String): String {
        val endpoint = "https://generativelanguage.googleapis.com/v1beta/models/$model:generateContent?key=$apiKey"

        val response = webClient.post()
            .uri(endpoint)
            .bodyValue(createRequestBody(text))
            .retrieve()
            .bodyToMono(GeminiResponse::class.java)
            .block()

        return extractSummaryFromResponse(response)
    }

    /**
     * Creates the request body for the Google Gemini API
     */
    private fun createRequestBody(text: String): Map<String, Any> {
        return mapOf(
            "contents" to listOf(
                mapOf(
                    "role" to "user",
                    "parts" to listOf(
                        mapOf(
                            "text" to "You are an AI that summarizes academic lectures. Summarize the following lecture: $text"
                        )
                    )
                )
            ),
            "generationConfig" to mapOf(
                "temperature" to 0.5,
                "topK" to 40,
                "topP" to 0.95,
                "maxOutputTokens" to 8192
            )
        )
    }

    /**
     * Extracts the summary from the Google Gemini API response
     */
    private fun extractSummaryFromResponse(response: GeminiResponse?): String {
        return try {
            val candidates = response?.candidates
            if (candidates.isNullOrEmpty()) {
                return "No summary available."
            }

            val parts = candidates[0].content?.parts
            if (parts.isNullOrEmpty()) {
                return "No summary available."
            }

            parts[0].text ?: "No summary available."
        } catch (e: Exception) {
            "Error retrieving summary: ${e.message}"
        }
    }

    // Data classes for Gemini API response
    data class GeminiResponse(
        val candidates: List<Candidate>? = null
    )

    data class Candidate(
        val content: Content? = null,
        val finishReason: String? = null
    )

    data class Content(
        val role: String? = null,
        val parts: List<Part>? = null
    )

    data class Part(
        val text: String? = null
    )
}