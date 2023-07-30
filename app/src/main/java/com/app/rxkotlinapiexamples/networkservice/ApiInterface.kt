package com.app.rxkotlinapiexamples.networkservice

import com.app.rxkotlinapiexamples.models.CommentModel
import com.app.rxkotlinapiexamples.models.Employee
import com.app.rxkotlinapiexamples.models.Post
import com.app.rxkotlinapiexamples.models.Student
import com.app.rxkotlinapiexamples.models.User
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*


interface ApiInterface {

    @GET("/v2/5cdf27653000002b00430d14")
    fun getEmployeeList(): Observable<List<Employee>>

    @GET("/v2/5cdf2f353000004600430d29")
    fun getStudentList(): Observable<List<Student>>

    @GET("users")
    fun getUsers(): Observable<List<User>>

    @GET("posts")
    fun getPostsByUserId(@Query("userId") id: Int): Observable<List<Post>>

    @GET("comments")
    fun getCommentsByPostId(@Query("postId") id: Int): Observable<List<CommentModel>>
}