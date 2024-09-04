package heo.boot.my.repository

import heo.boot.my.controller.SearchForm
import heo.boot.my.domain.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface BoardRepository {

    fun save(board: Board)

    fun findOne(id: Long): Board

    fun findAll(pageable: Pageable): Page<Board>

    fun findAllBySubject(pageable: Pageable, searchForm: SearchForm): Page<Board>

    fun delete(board: Board)


}