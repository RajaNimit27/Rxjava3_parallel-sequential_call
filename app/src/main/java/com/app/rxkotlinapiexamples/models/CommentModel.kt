package com.app.rxkotlinapiexamples.models

data class CommentModel (
    val id:Int = 0,
    val postId:Int = 0,
    val name: String? = null,
    val body: String? = null // Add other comment properties, getters, and setters
)