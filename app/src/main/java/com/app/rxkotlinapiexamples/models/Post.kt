package com.app.rxkotlinapiexamples.models

data class Post (
     val id:Int = 0,
     val userId:Int =0,
     val title: String? = null,
     val body: String? = null // Add other post properties, getters, and setters
)