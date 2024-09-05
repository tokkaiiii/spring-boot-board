package heo.boot.my.repository

import heo.boot.my.domain.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataJpaBoardRepository : JpaRepository<Board,Long> {

    fun save(board: Board)

    fun findBoardById(id: Long): Board

    fun findAllByOrderByIdDesc(pageable: Pageable): Page<Board>

    fun findAllBySubjectContainingOrderByIdDesc(pageable: Pageable,subject: String?): Page<Board>

    fun deleteBoardById(id: Long)

}