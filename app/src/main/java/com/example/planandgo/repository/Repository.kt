package com.example.planandgo.repository

class Repository private constructor(

){

    companion object{
        @Volatile
        private var instance : Repository? = null

        fun getInstance() : Repository =
            instance ?: synchronized(this){
                instance ?: Repository()
            }.also { instance = it }
    }
}