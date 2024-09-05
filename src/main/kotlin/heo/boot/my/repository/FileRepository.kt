package heo.boot.my.repository

import heo.boot.my.domain.File
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository: JpaRepository<File, Long> {
    fun findFileById(fileId: Long): File
}