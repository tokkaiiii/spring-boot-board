package heo.boot.my.service

import heo.boot.my.controller.SearchForm
import heo.boot.my.domain.Board
import heo.boot.my.repository.SpringDataJpaBoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SpringDataBoardService(private val jpaRepository: SpringDataJpaBoardRepository): BoardService {
    override fun save(board: Board) {
        jpaRepository.save(board)
    }

    override fun findOne(id: Long): Board {
       return jpaRepository.findBoardById(id)
    }

    override fun findAll(pageable: Pageable): Page<Board> {
        return jpaRepository.findAllByOrderByIdDesc(pageable)
    }

    override fun findAllBySubject(pageable: Pageable, searchForm: SearchForm): Page<Board> {
        return jpaRepository.findAllBySubjectContainingOrderByIdDesc(pageable, searchForm.subject)
    }

    override fun update(boardId: Long, email: String, subject: String, content: String) {
        val findBoard = jpaRepository.findBoardById(boardId)
        Board.changeBoard(findBoard, email, subject, content)
    }

    override fun delete(boardId: Long) {
        jpaRepository.deleteBoardById(boardId)
    }
}