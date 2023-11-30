package com.example.demo

import assertk.assertions.isBetween
import assertk.assertions.isEqualTo
import net.jqwik.api.*
import org.junit.jupiter.api.Test

class CesarTest2 {

    @Test
    fun cesarA2equalC(){
        //Arrange
        val a: Char = 'A'
        val num: Int = 2
        val cesar = Cesar2()

        //Act
        val res = cesar.cypher2(a, num)

        //Assert
        assertk.assertThat(res).isEqualTo('C')
    }

    @Test
    fun cesarA5equalF(){
        //Arrange
        val a: Char = 'A'
        val num: Int = 5
        val cesar = Cesar2()

        //Act
        val res = cesar.cypher2(a, num)

        //Assert
        assertk.assertThat(res).isEqualTo('F')
    }

    @Test
    fun cesarZtoA(){
        //Arrange
        val a: Char = 'Z'
        val num: Int = 1
        val cesar = Cesar2()

        //Act
        val res = cesar.cypher2(a, num)

        //Assert
        assertk.assertThat(res).isEqualTo('A')
    }

    @Test
    fun cesarWhile(){
        //Arrange
        val a: Char = 'Y'
        val num: Int = 5
        val cesar = Cesar2()

        //Act
        val res = cesar.cypher2(a, num)

        //Assert
        assertk.assertThat(res).isEqualTo('D')
    }

    @Test
    fun cesarBigNum(){
        //Arrange
        val a: Char = 'A'
        val num: Int = 26
        val cesar = Cesar2()

        //Act
        val res = cesar.cypher2(a, num)

        //Assert
        assertk.assertThat(res).isEqualTo('A')
    }

    @Test
    fun cesarNegativNum(){
        //Arrange
        val a: Char = 'A'
        val num: Int = -1
        val cesar = Cesar2()

        //Act
        val res = cesar.cypher2(a, num)

        //Assert
        assertk.assertThat(res).isEqualTo('!')
    }

    @Property //cypher(a, 0) = a
    fun cesarO(
        @ForAll ("correctChar") a: Char
    ){
        //Arrange
        val num: Int = 0
        val cesar = Cesar2()

        //Act
        val res = cesar.cypher(a, num)

        //Assert
        assertk.assertThat(res).isEqualTo(a)
    }

   // @Property //cypher(a, num) = cypher(a, num + y*26)
    //fun cesarMod26(
    //        @ForAll ("positifInt") num: Int,
    //        @ForAll ("positifInt") num2: Int,
    //        @ForAll ("correctChar") a: Char
    //){
        //Arrange
        //val cesar = Cesar2()
        //val cesar2 = Cesar2()

        //Act
        //val res = cesar.cypher(a, num)
        //val res2 = cesar2.cypher(a, num + num2 * 26 )

        //Assert
        //assertk.assertThat(cesar.cypher(a, num)).isEqualTo(cesar2.cypher(a, num + num2 * 26 ))
    //}

    @Property //decypher(cypher(a)) = a
    fun cesarDecypherCypher(
            @ForAll ("positifInt") num: Int,
            @ForAll ("correctChar") a: Char
    ){
        //Arrange
        val cesar = Cesar2()

        //Act
        val res = cesar.cypher2(a, num)
        val res2 = cesar.decypher(res, num)

        //Assert
        assertk.assertThat(res2).isEqualTo(a)
    }

    @Property //cypher de (for) entre a et z
    fun cesarAToZr(
            @ForAll ("positifInt") num: Int,
            @ForAll ("correctChar") a: Char
    ){
        //Arrange
        val cesar = Cesar2()

        //Act
        val res = cesar.cypher2(a, num)

        //Assert
        assertk.assertThat(res).isBetween('A', 'Z')
    }

    @Provide
    fun positifInt(): Arbitrary<Int>{
        return Arbitraries.integers().greaterOrEqual(0)
    }
    @Provide
    fun correctChar(): Arbitrary<Char>{
        return Arbitraries.chars().range('A', 'Z')
    }

}