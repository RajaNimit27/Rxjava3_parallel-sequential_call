package com.app.rxkotlinapiexamples.networkservice



import java.io.IOException

class NoConnectivityException : IOException() {

    fun getMessages(): String {

        return "No internet"
    }
}