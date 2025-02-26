package com.torrriks.studenthelper.StudentHelperApp.services

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID

@Service
class FileReadService {

    @Value("\${app.upload.dir}")
    private lateinit var uploadDir: String

    /**
     * Processes a PDF file and extracts text content
     *
     * @param file The uploaded PDF file
     * @return Extracted text from the PDF
     * @throws IllegalArgumentException if text extraction fails
     */
    fun processAndExtractText(file: MultipartFile): String {
        val savedFile = saveFile(file)
        val extractedText = extractTextFromPdf(savedFile)

        // Clean up the temporary file
        savedFile.delete()

        if (extractedText.isBlank()) {
            throw IllegalArgumentException("Could not extract text from PDF.")
        }

        return extractedText
    }

    /**
     * Saves the uploaded file to a temporary location
     */
    private fun saveFile(file: MultipartFile): File {
        val uploadPath = Paths.get(uploadDir)
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath)
        }

        val filePath = uploadPath.resolve("${UUID.randomUUID()}.pdf")
        val fileToSave = filePath.toFile()
        file.transferTo(fileToSave)

        return fileToSave
    }

    /**
     * Extracts text content from a PDF file
     */
    private fun extractTextFromPdf(file: File): String {
        return PDDocument.load(file).use { document ->
            PDFTextStripper().getText(document)
        }
    }
}

