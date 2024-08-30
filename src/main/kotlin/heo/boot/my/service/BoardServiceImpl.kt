package heo.boot.my.service

import heo.boot.my.domain.Board
import heo.boot.my.repository.BoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
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

    @Transactional
    override fun update(board: Board) {
        boardRepository.save(board)
    }

    @Transactional
    override fun delete(id: Long) {
        boardRepository.deleteById(id)
    }
}