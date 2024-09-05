package heo.boot.my.service

import heo.boot.my.domain.File
import heo.boot.my.repository.FileRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Transactional(readOnly = true)
@Service
class FileServiceImpl(private val fileRepository: FileRepository) : FileService {

    @Value("\${file.dir}")
    private var fileDir: String? = null

    override fun saveFile(multipartFile: MultipartFile): Long {
        if (fileDir == null) {
            return -1
        }
        val originalFilename = multipartFile.originalFilename
        val uuid = UUID.randomUUID().toString()
        val extension = originalFilename?.substring(originalFilename.lastIndexOf(".") + 1)
        val savedName = "${uuid}_${extension}"
        val savePath = "$fileDir/$savedName"
        val file = File(originalFilename, savedName, savePath)
        multipartFile.transferTo(java.io.File(savePath))
        val saveFile = fileRepository.save(file)
        return saveFile.id
    }

    override fun findFileAll(): List<File> = fileRepository.findAll()

    override fun fileUpload(fileId: Long): File = fileRepository.findFileById(fileId)

}