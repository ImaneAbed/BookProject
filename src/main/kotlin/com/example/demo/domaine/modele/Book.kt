package com.example.demo.domaine.modele

data class Book (val title : String, val author : String){

        init{
            if(title == ""){
                throw Exception("empty title")
            }
            //if(title == " "){
            //    throw Exception("blank title")
            //}
            if(author == ""){
                throw Exception("empty author")
            }
            //if(author == " "){
            //    throw Exception("blank author")
            //}
        }


}