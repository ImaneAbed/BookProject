package com.example.demo

class Cesar2 {
    fun cypher (a: Char, num: Int): Char {
        if(num == 0){
            return a
        }
        if(a=='Z'){
            return cypher('A', num-1)
        }
        if(num < 0){
            throw Exception("invalid number !")
        }
        if(num > 25){
            return cypher(a, num % 26)
        }
        if((a+num).toChar()> 'Z'){
            return cypher((a+1).toChar(), num-1)
        }

        if(a < 'A' || a > 'Z'){
            throw Exception("invalid letter !")
        }
        return (a+num).toChar()
    }

    fun cypher2(inputChar: Char, shift: Int): Char {
        require(shift >= 0) { "Invalid shift value: $shift" }

        if (inputChar !in 'A'..'Z') {
            throw IllegalArgumentException("Invalid input letter: $inputChar")
        }

        if (shift == 0) {
            return inputChar
        }

        val normalizedShift = shift % 26
        val resultChar = inputChar + normalizedShift

        return if (resultChar > 'Z') {
            cypher2('A', resultChar - 'Z' - 1)
        } else {
            resultChar
        }
    }

    fun decypher(inputChar: Char, shift: Int): Char {
        require(shift >= 0) { "Invalid shift value: $shift" }

        if (inputChar !in 'A'..'Z') {
            throw IllegalArgumentException("Invalid input letter: $inputChar")
        }

        if (shift == 0) {
            return inputChar
        }

        val normalizedShift = shift % 26
        val resultChar = inputChar - normalizedShift

        return if (resultChar < 'A') {
            decypher('Z', 'A' - resultChar - 1)
        } else {
            resultChar
        }
    }

}