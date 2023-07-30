package com.app.rxkotlinapiexamples.base

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val error: Throwable?
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }
        fun <T> error(e: Throwable?): Resource<T> {
            return Resource(Status.ERROR, null, null, e)
        }
        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null, null)
        }

    }

}

