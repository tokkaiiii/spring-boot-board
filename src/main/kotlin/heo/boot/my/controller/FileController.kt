package heo.boot.my.controller

import heo.boot.my.service.FileService
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.util.UriUtils
import java.nio.charset.StandardCharsets

@Controller
@RequestMapping("/file")
class FileController(private val fileService: FileService) {

    @GetMapping("/upload")
    fun form() = "/th/file"

    @PostMapping("/upload")
    fun upload(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("files") files: List<MultipartFile>
    ): String {
        fileService.saveFile(file)
        files.stream().forEach { fileService.saveFile(it) }
        return "redirect:/file/upload"
    }

    @GetMapping("/list")
    fun listForm(model: Model): String {
        val findFileAll = fileService.findFileAll()
        model.addAttribute("files", findFileAll)
        return "/th/file-list"
    }

    //첨부파일 다운로드
    @GetMapping("/download/{id}")
    fun download(
        @PathVariable("id") id: Long
    ): ResponseEntity<Resource> {
        val fileUpload = fileService.fileUpload(id)
        val urlResource = UrlResource("file:${fileUpload.savednm}")
        val encodedFileName = fileUpload.orgnm?.let { UriUtils.encode(it, StandardCharsets.UTF_8) }
        val contentDisposition = "attachment; filename=/${encodedFileName}/"
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
            .body(urlResource)

    }

    @GetMapping("/images/{id}")
    fun downloadImage(@PathVariable("id") id: Long): Resource {
        val fileUpload = fileService.fileUpload(id)
        return UrlResource("file:${fileUpload.savednm}")
    }

}