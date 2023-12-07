package com.example.demo.domaine.usecases

import com.example.demo.domaine.modele.Book
import com.example.demo.domaine.port.BookInterface
import org.springframework.stereotype.Service

@Service
data class BookManagement (val bookInterface: BookInterface){

    fun createBook(book : Book): Book {
        bookInterface.saveBook(book)
        return book
    }

    fun listBooks(): List<Book> {
        return bookInterface.getAllBooks().sortedBy { it.title.lowercase() }
    }
}
