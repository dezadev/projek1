package com.umkm.pos.utils

import android.content.Context
import android.graphics.pdf.PdfDocument
import java.io.File

object ReceiptGenerator {
    fun generateSimplePdf(context: Context, content: String, filename: String): File {
        val doc = PdfDocument()
        val page = doc.startPage(PdfDocument.PageInfo.Builder(300, 600, 1).create())
        page.canvas.drawText(content, 16f, 24f, android.graphics.Paint())
        doc.finishPage(page)

        val outFile = File(context.cacheDir, filename)
        outFile.outputStream().use { doc.writeTo(it) }
        doc.close()
        return outFile
    }
}
