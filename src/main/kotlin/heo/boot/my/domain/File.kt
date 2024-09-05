package heo.boot.my.domain

import jakarta.persistence.*
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType



@Entity
@Table(name = "fileUp")
open class File(

    var orgnm: String? = null,

    var savednm: String ,

    var savedpath: String = ""

){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    var id: Long = 0

}