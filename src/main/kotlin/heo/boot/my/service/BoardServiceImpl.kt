package heo.boot.my.service

import heo.boot.my.controller.SearchForm
import heo.boot.my.domain.Board
import heo.boot.my.repository.BoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

//@Service
@Transactional(readOnly = true)
class BoardServiceImpl(private val boardRepository: BoardRepository) : BoardService {

    @Transactional
    override fun save(board: Board) {
        boardRepository.save(board)
    }

    override fun findOne(id: Long): Board {
        return boardRepository.findOne(id)
    }

    override fun findAll(pageable: Pageable): Page<Board> {
        return boardRepository.findAll(pageable)
    }

    override fun findAllBySubject(pageable: Pageable, searchForm: SearchForm): Page<Board> {
       return boardRepository.findAllBySubject(pageable, searchForm)
    }

    @Transactional
    override fun update(boardId: Long, email: String, subject: String, content: String) {
        val findBoard = boardRepository.findOne(boardId)
        Board.changeBoard(findBoard, email, subject, content)
    }


    @Transactional
    override fun delete(boardId: Long) {
        val findOne = boardRepository.findOne(boardId)
        boardRepository.delete(findOne)
    }
}