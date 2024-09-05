package heo.boot.my.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
open class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    var id: Long? = null,

    var email: String? = null,

    var writer: String? = null,

    var subject: String? = null,

    var content: String? = null,

    @CreationTimestamp
    var date: LocalDateTime? = LocalDateTime.now()
) {

    companion object {

        fun changeBoard(
            board: Board,
            email: String,
            subject: String,
            content: String
        ) {
            board.email = email
            board.subject = subject
            board.content = content
        }
    }
}