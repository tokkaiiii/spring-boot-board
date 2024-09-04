package heo.boot.my.repository

import heo.boot.my.controller.SearchForm
import heo.boot.my.domain.Board
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.security.auth.Subject

@Repository
interface SpringDataJpaBoardRepository : JpaRepository<Board,Long> {

    fun save(board: Board)

    fun findBoardById(id: Long): Board

    fun findAllByOrderByIdDesc(pageable: Pageable): Page<Board>

    fun findAllBySubjectContainingOrderByIdDesc(pageable: Pageable,subject: String?): Page<Board>

    fun deleteBoardById(id: Long)

}