package com.app.rxkotlinapiexamples.networkservice

import android.content.Context
import com.app.rxkotlinapiexamples.helpers.Utils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException


class ConnectivityInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Utils.isNetworkAvailable(context)) {
            throw NoConnectivityException()
        } else {
            var response: Response?
            try {
                var request = chain.request()
                    .newBuilder()
                    .addHeader("Accept", "*/*")
                    .build()
                response = chain.proceed(request)
            } catch (e: Exception) {
                e.printStackTrace()
                throw SocketTimeoutException()
            }
            return response
        }
    }
}

