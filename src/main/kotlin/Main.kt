package org.example

import com.lowagie.text.Document
import com.lowagie.text.html.simpleparser.HTMLWorker
import com.lowagie.text.pdf.PdfWriter
import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.output.StringOutput
import java.io.ByteArrayOutputStream
import java.io.StringReader


fun main() {
  val templateEngine = TemplateEngine.createPrecompiled(ContentType.Html)
  val output = StringOutput()
  templateEngine.render("example.kte", mapOf("name" to "Iustin"), output)

  val document = Document()
  val byteArrayOutputStream = ByteArrayOutputStream()
  PdfWriter.getInstance(document, byteArrayOutputStream)

  document.open()
  val htmlString = output.toString()
  val htmlWorker = HTMLWorker(document)
  htmlWorker.parse(StringReader(htmlString))
  document.close()
  println(java.util.Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray()))
  println("PDF created successfully.")


  println(output)
}
