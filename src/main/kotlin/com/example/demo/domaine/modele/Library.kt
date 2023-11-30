package com.example.demo.domaine.modele

class Library {

    val books = mutableListOf<Book>()

    fun saveBook(book: Book) {
        books.add(book)
    }

    fun getAllBooks(): List<Book> {
        return books.toList()
    }
}