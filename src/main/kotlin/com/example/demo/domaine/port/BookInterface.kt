package com.example.demo.domaine.port
import com.example.demo.domaine.modele.Book

interface BookInterface {

    fun saveBook(book: Book)

    fun getAllBooks(): List<Book>
}