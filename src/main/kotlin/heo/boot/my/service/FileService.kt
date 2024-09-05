package heo.boot.my.service

import heo.boot.my.domain.File
import org.springframework.stereotype.Repository
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Repository
interface FileService {

    //(1) 파일 업로드
    fun saveFile(multipartFile: MultipartFile):Long

    //(2) 파일 다운로드
    fun findFileAll(): List<File>

    fun fileUpload(fileId: Long): File
}