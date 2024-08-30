package heo.boot.my.service

import heo.boot.my.domain.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BoardService {

    fun save(board: Board)

    fun findOne(id: Long): Board

    fun findAll(pageable: Pageable): Page<Board>

    fun update(board: Board)

    fun delete(id: Long)
}