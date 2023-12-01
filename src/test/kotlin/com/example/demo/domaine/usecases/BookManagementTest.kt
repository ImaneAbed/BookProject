package com.example.demo.domaine.usecases

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.containsExactlyInAnyOrder
import com.example.demo.domaine.modele.Book
import com.example.demo.domaine.port.BookInterface
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import io.mockk.verify
import net.jqwik.api.*
import net.jqwik.api.Combinators.combine
import net.jqwik.api.lifecycle.BeforeProperty
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
class BookManagementTest {

    @InjectMockKs
    private lateinit var bookManagement: BookManagement

    @MockK
    private lateinit var bookInterface: BookInterface

    @Test
    fun listBooks() {
        every { bookInterface.getAllBooks() } returns listOf(
            Book("Le Petit Chaperon Rouge", "Charles Perrault"),
            Book("Blanche-Neige et les Sept Nains", "Frères Grimm")
        )

        val res = bookManagement.listBooks()

        assertThat(res).containsExactly(
            Book("Blanche-Neige et les Sept Nains", "Frères Grimm"),
            Book("Le Petit Chaperon Rouge", "Charles Perrault")
        )
    }

    @Test
    fun createBook() {
        justRun { bookInterface.saveBook(any()) }

        val book = Book("Le Petit Chaperon Rouge", "Charles Perrault")

        bookManagement.createBook(book)

        verify(exactly = 1) { bookInterface.saveBook(book) }
    }

    @BeforeProperty
    fun initMocks() {
        MockKAnnotations.init(this)
    }

    @Property
    fun `get all all book should have all books stored in db`(
        @ForAll("bookGenerator") books: List<Book>
    ) {
        every { bookInterface.getAllBooks() } returns books

        val res = bookManagement.listBooks()

        assertThat(res).containsExactlyInAnyOrder(*books.toTypedArray())
    }

    @Provide
    fun bookGenerator(): Arbitrary<List<Book>> {
        return combine(
            Arbitraries.strings().ofMinLength(1).ofMaxLength(20).alpha(),
            Arbitraries.strings().ofMinLength(1).ofMaxLength(20).alpha()).`as` { title: String, author: String ->
            Book(title, author)
        }.list().uniqueElements().ofSize(10)
    }

}
    /*@Test
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
        return Arbitraries.strings().ofMinLength(1)
    }

}*/