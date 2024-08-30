package heo.boot.my.domain

import jakarta.persistence.*
import java.time.Instant
import java.util.*

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

    var date: Date? = Date.from(Instant.now())
)