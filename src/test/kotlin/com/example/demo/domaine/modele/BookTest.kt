package com.example.demo.domaine.modele

import assertk.assertFailure
import assertk.assertions.hasMessage
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Test

class BookTest {

    //title ""
    @Test
    fun emptyTitle(){
        //Arrange
        val title: String = ""
        val author: String = "Charles Perrault"

        //Act & Assert
        assertFailure { Book(title, author) }
            .isInstanceOf(Exception::class)
            .hasMessage("empty title")

    }
    //title is " "
    //@Test
    //fun blankTitle(){
        //Arrange
    //    val title: String = " "
    //    val author: String = "Charles Perrault"

        //Act & Assert
    //    val res = Book(title, author)

    //}

    //author ""
    @Test
    fun emptyAuthor(){
        //Arrange
        val title: String = "Le Petit Chaperon Rouge"
        val author: String = ""

        //Act & Assert
        assertFailure { Book(title, author) }
            .isInstanceOf(Exception::class)
            .hasMessage("empty author")

    }
    //author is " "
    //@Test
    //fun blankAuthor(){
        //Arrange
    //    val title: String = "Le Petit Chaperon Rouge"
    //    val author: String = " "

        //Act & Assert
    //    val res = Book(title, author)

    //}

}
