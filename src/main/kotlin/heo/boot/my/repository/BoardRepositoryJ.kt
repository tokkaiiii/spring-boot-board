package heo.boot.my.repository

import heo.boot.my.controller.SearchForm
import heo.boot.my.domain.Board
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

//@Repository
class BoardRepositoryJ(@PersistenceContext private val em: EntityManager) : BoardRepository {

    override fun save(board: Board) {
        em.persist(board)
    }

    override fun findOne(id: Long): Board {
        return em.find(Board::class.java, id) ?: throw IllegalStateException("해당 게시물이 없습니다.")
    }


    override fun findAll(pageable: Pageable): Page<Board> {
        val query = em.createQuery("select b from Board b order by b.id desc", Board::class.java)
        query.firstResult = pageable.offset.toInt()
        query.maxResults = pageable.pageSize

        val boards = query.resultList
        val total = em.createQuery("select count(b) from Board b", Long::class.java).singleResult
        return PageImpl(boards, pageable, total)
    }

    override fun findAllBySubject(pageable: Pageable, searchForm: SearchForm): Page<Board> {
        val resultList =
            em.createQuery("select b from Board b where b.subject = :subject", Board::class.java)
                .setParameter("subject", searchForm.subject)
                .setFirstResult(pageable.offset.toInt())
                .setMaxResults(pageable.pageSize)
                .resultList
        val total = em.createQuery("select count(b) from Board b where b.subject like :subject", Long::class.java)
            .setParameter("subject", searchForm.subject)
            .singleResult
        return PageImpl(resultList, pageable, total)
    }


    override fun delete(board: Board) {
        em.remove(board)
    }

}