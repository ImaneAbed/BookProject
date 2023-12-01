package com.example.demo.domaine.usecases

import com.example.demo.domaine.modele.Book
import com.example.demo.domaine.port.BookInterface

data class BookManagement (val bookInterface: BookInterface){

    fun createBook(title: String, author: String): Book {
        val newBook = Book(title, author)
        bookInterface.saveBook(newBook)
        return newBook
    }
    fun createBook(book : Book): Book {
        bookInterface.saveBook(book)
        return book
    }

    fun listBooks(): List<Book> {
        return bookInterface.getAllBooks().sortedBy { it.title.lowercase() }
    }
}
