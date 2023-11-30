package com.example.demo.domaine.usecases

import assertk.assertions.isEqualTo
import com.example.demo.domaine.modele.Book
import com.example.demo.domaine.modele.Library
import net.jqwik.api.*
import org.junit.jupiter.api.Test


class BookManagementTest {

    @Test
    fun createBook(){
        //Arrange
        val library : Library = Library()
        val title: String = "Le Petit Chaperon Rouge"
        val author: String = "Charles Perrault"

        //Act
        val books = BookManagement(library)
        val res  = books.createBook(title, author)


        //Assert
        assertk.assertThat(res).isEqualTo(Book(title, author))

    }
    //firstBook
    @Test
    fun listFirstBooks(){
        //Arrange
        val library : Library = Library()
        val title: String = "Le Petit Chaperon Rouge"
        val author: String = "Charles Perrault"

        //Act
        val books = BookManagement(library)
        val res  = books.createBook(title, author)
        val res2 = books.listBooks()

        //Assert
        assertk.assertThat(res2[0]).isEqualTo(Book(title, author))

    }

    @Test
    fun inOrder(){
        //Arrange
        val library : Library = Library()
        val book1: Book = Book("Blanche-Neige et les Sept Nains","Frères Grimm")
        val book2: Book = Book("Alice au pays des merveilles","Lewis Carroll")
        val book3: Book = Book("Cendrillon","Charles Perrault")

        //Act
        val books = BookManagement(library)
        val res  = books.createBook(book1)
        books.createBook(book2)
        books.createBook(book3)
        val res2 = books.listBooks()

        //Assert
        assertk.assertThat(res2[0]).isEqualTo(book2)
        assertk.assertThat(res2[1]).isEqualTo(book1)
        assertk.assertThat(res2[2]).isEqualTo(book3)

    }

    @Test
    fun containAll(){
        //Arrange
        val book1: Book = Book("Blanche-Neige et les Sept Nains","Frères Grimm")
        val book2: Book = Book("Alice au pays des merveilles","Lewis Carroll")
        val book3: Book = Book("Cendrillon","Charles Perrault")

        val library : Library = Library()
        val createdBooks = mutableListOf<Book>()
        createdBooks.add(book1)
        createdBooks.add(book2)
        createdBooks.add(book3)

        //Act
        val books = BookManagement(library)
        books.createBook(book1)
        books.createBook(book2)
        books.createBook(book3)
        val res2 = books.listBooks()

        //Assert
        res2.containsAll(createdBooks)

    }
    @Property
    fun forAllcontainAll(
    @ForAll("correctString") title: String,
    @ForAll("correctString") author: String){
        val book : Book = Book(title, author)
        val library : Library = Library()

        //Act
        val books = BookManagement(library)
        books.createBook(book)
        val res2 = books.listBooks()
        val createdBooks = mutableListOf<Book>()
        createdBooks.add(book)

        //Assert
        res2.containsAll(createdBooks)

    }


    @Provide
    fun correctString(): Arbitrary<String> {
        return Arbitraries.strings().ofMinLength(1) // on suppose qu'il n'y a pas de titre avec qu'une seule lettre
    }

}