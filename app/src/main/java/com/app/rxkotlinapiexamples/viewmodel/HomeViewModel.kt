package com.app.rxkotlinapiexamples.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.rxkotlinapiexamples.base.BaseViewModel
import com.app.rxkotlinapiexamples.base.Resource
import com.app.rxkotlinapiexamples.models.CommentModel
import com.app.rxkotlinapiexamples.models.EmployeeAndStudent
import com.app.rxkotlinapiexamples.networkservice.ApiClient
import com.app.rxkotlinapiexamples.networkservice.ApiClientPosts
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


class HomeViewModel(application: Application) : BaseViewModel(application) {

    var resource = MutableLiveData<Resource<EmployeeAndStudent>>()
    var resourcePosts = MutableLiveData<Resource<List<CommentModel>>>()

    @SuppressLint("CheckResult")
    fun getStudentEmployeeList() {
        try {
            Observable.zip(
                ApiClient.getInstance(context).apiInterface!!.getEmployeeList()
                    .onErrorResumeNext { _: Throwable ->
                        Observable.empty()
                    },
                ApiClient.getInstance(context).apiInterface!!.getStudentList()
                    .onErrorResumeNext { _: Throwable ->
                        Observable.empty()
                    }
            ) { t1, t2 -> EmployeeAndStudent(t1, t2) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    resource.postValue(Resource.success(it))
                }, {
                    resource.postValue(Resource.error(it))
                }, {
                })

        } catch (e: Exception) {
            e.stackTrace
        }
    }

    @SuppressLint("CheckResult")
    fun getUserComments() {
        try {
            Observable.just(ApiClientPosts.getInstance(context).apiInterface!!.getUsers()
                .concatMapIterable { users ->
                    users
                }.concatMap { user ->
                    ApiClientPosts.getInstance(context).apiInterface!!.getPostsByUserId(user.id)
                }.concatMapIterable { posts ->
                    posts
                }.concatMap { post ->
                    ApiClientPosts.getInstance(context).apiInterface!!.getCommentsByPostId(post.id)
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    resourcePosts.postValue(Resource.success(it))
                }, {
                    resourcePosts.postValue(Resource.error(it))
                }, {
                }))

        } catch (e: Exception) {
            e.stackTrace
        }
    }
}