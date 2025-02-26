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
            // Save the file and extract text
            val extractedText = fileReadService.processAndExtractText(file)

            // Get summary from OpenAI
            val summary = summaryService.getSummary(extractedText)

            // Add to model
            model.addAttribute("summary", summary)

            "summarize/result"
        } catch (e: Exception) {
            model.addAttribute("error", "Error processing file: ${e.message}")
            "summarize/index"
        }
    }
}