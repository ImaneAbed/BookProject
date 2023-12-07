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
    fun allBooks(
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