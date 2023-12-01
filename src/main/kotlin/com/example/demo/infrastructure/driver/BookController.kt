package com.example.demo.infrastructure.driver

import com.example.demo.domaine.usecases.BookManagement
import com.example.demo.infrastructure.driver.dto.BookDTO
import org.springframework.web.bind.annotation.*
import com.example.demo.domaine.modele.Book

@RestController
@RequestMapping("/books")
class BookController(private val bookManagement: BookManagement) {

    @GetMapping
    fun getBooks(): List<BookDTO> {
        val books = bookManagement.listBooks()
        return books.map { BookDTO(it.title, it.author) }
    }

    @PostMapping
    fun createBook(@RequestBody bookDTO: BookDTO): BookDTO {
        val createdBook = bookManagement.createBook(Book(bookDTO.title, bookDTO.author))
        return BookDTO(createdBook.title, createdBook.author)
    }
}
