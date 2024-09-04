package heo.boot.my.controller

import heo.boot.my.domain.Board
import heo.boot.my.service.BoardService
import heo.boot.my.service.SpringDataBoardService
import io.github.oshai.kotlinlogging.KLogging
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class BoardController(private val boardService: SpringDataBoardService) {

    companion object : KLogging()

    @GetMapping("/")
    fun boardMain(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "5") size: Int,
        model: Model
    ): String {
        val pageable = PageRequest.of(page, size)
        val boardList = boardService.findAll(pageable)
        model.addAttribute("searchForm", SearchForm())
        model.addAttribute("boardList", boardList)
        return "/th/list"
    }

    @GetMapping("/boards/{id}")
    fun boardDetail(@PathVariable("id") id: Long, model: Model): String {
        val findOne = boardService.findOne(id)
        model.addAttribute("board", findOne)
        return "/th/content"
    }

    @GetMapping("/boards/write")
    fun boardWrite(model: Model): String {
        val board = Board()
        model.addAttribute("board", board)
        return "/th/write"
    }

    @PostMapping("/boards/write")
    fun boardWrite(board: Board): String {
        boardService.save(board)
        return "redirect:/boards/${board.id}"
    }

    @GetMapping("/boards/update/{id}")
    fun boardUpdateForm(@PathVariable id: Long, model: Model): String {
        val findOne = boardService.findOne(id)
        model.addAttribute("board", findOne)
        return "/th/update"
    }

    @PostMapping("/boards/update/{id}")
    fun boardUpdate(@PathVariable id: Long, @ModelAttribute("board") form: UpdateForm): String {
        val email = form.email
        val subject = form.subject
        val content = form.content
        boardService.update(id, email, subject, content)
        return "redirect:/boards/${id}"
    }

    @GetMapping("/boards/delete/{id}")
    fun boardDelete(@PathVariable id: Long): String {
        boardService.delete(id)
        return "redirect:/"
    }

    @GetMapping("/boards/search")
    fun boardSearch(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "5") size: Int,
        @ModelAttribute("searchForm") searchForm: SearchForm,
        model: Model): String {
        println("searchForm: ${searchForm.subject}")
        val pageable = PageRequest.of(page, size)
        val boardList = boardService.findAllBySubject(pageable,searchForm)
        model.addAttribute("boardList", boardList)
        return "/th/search-list"
    }

}