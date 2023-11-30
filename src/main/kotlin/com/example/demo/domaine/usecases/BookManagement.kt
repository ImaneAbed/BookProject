package com.example.demo.domaine.usecases

import com.example.demo.domaine.modele.Book
import com.example.demo.domaine.modele.Library

data class BookManagement (val library: Library){

    fun createBook(title: String, author: String): Book {
        val newBook = Book(title, author)
        library.saveBook(newBook)
        return newBook
    }
    fun createBook(book : Book): Book {
        library.saveBook(book)
        return book
    }

    fun listBooks(): List<Book> {
        return library.getAllBooks().sortedBy { it.title }
    }
}
