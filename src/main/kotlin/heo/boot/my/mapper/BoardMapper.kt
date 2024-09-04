package heo.boot.my.mapper

import heo.boot.my.domain.Board
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface BoardMapper {

    fun save (board: Board)

    fun update (board: Board)

    fun delete (board: Board)

}