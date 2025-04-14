package com.torrriks.studenthelper.StudentHelperApp.controllers

import com.torrriks.studenthelper.StudentHelperApp.services.summary.FileReadService
import com.torrriks.studenthelper.StudentHelperApp.services.summary.SummaryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import java.util.*


@Controller
@RequestMapping("/summarize")
class SummarizeController @Autowired constructor(
    private val fileReadService: FileReadService,
    private val summaryService: SummaryService
) {

    // Store the last extracted text for regeneration
    private var lastExtractedText: MutableMap<String, String> = mutableMapOf()

    @GetMapping
    fun index(): String {
        return "summarize/index"
    }

    @PostMapping("/upload")
    fun handleFileUpload(
        @RequestParam("file") file: MultipartFile,
        model: Model
    ): String {
        if (file.isEmpty || !file.originalFilename?.endsWith(
                ".pdf",
                ignoreCase = true
            )!! == true
        ) {
            model.addAttribute("error", "Please upload a valid PDF file.")
            return "summarize/index"
        }

        return try {
            val extractedText = fileReadService.processAndExtractText(file)
            
            val fileId = UUID.randomUUID().toString()
            
            lastExtractedText[fileId] = extractedText

            val summary = summaryService.getSummary(extractedText)

            model.addAttribute("summary", summary)
            model.addAttribute("fileId", fileId)
            model.addAttribute("title", "Конспект: ${file.originalFilename}")

            "summarize/result"
        } catch (e: Exception) {
            model.addAttribute("error", "Error processing file: ${e.message}")
            "summarize/index"
        }
    }
    
    @GetMapping("/regenerate")
    fun regenerateSummary(
        @RequestParam("id", required = false) fileId: String?,
        model: Model
    ): String {
        if (fileId == null || !lastExtractedText.containsKey(fileId)) {
            model.addAttribute("error", "No file found for regeneration. Please upload a file again.")
            return "summarize/index"
        }
        return try {
            val extractedText = lastExtractedText[fileId]!!
            val summary = summaryService.getSummary(extractedText)
            model.addAttribute("summary", summary)
            model.addAttribute("fileId", fileId)
            model.addAttribute("title", "Оновлений конспект")
            "summarize/result"
        } catch (e: Exception) {
            model.addAttribute("error", "Error regenerating summary: ${e.message}")
            "summarize/index"
        }
    }
}