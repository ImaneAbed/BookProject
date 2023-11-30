package com.example.demo.domaine.modele

import org.junit.jupiter.api.Test

class BookTest {

    //title ""
    @Test
    fun emptyTitle(){
        //Arrange
        val title: String = ""
        val author: String = "Charles Perrault"

        //Act & Assert
        val res = Book(title, author)

    }
    //title is " "
    @Test
    fun blankTitle(){
        //Arrange
        val title: String = " "
        val author: String = "Charles Perrault"

        //Act & Assert
        val res = Book(title, author)

    }

    //author ""
    @Test
    fun emptyAuthor(){
        //Arrange
        val title: String = "Le Petit Chaperon Rouge"
        val author: String = ""

        //Act & Assert
        val res = Book(title, author)

    }
    //author is " "
    @Test
    fun blankAuthor(){
        //Arrange
        val title: String = "Le Petit Chaperon Rouge"
        val author: String = " "

        //Act & Assert
        val res = Book(title, author)

    }

}