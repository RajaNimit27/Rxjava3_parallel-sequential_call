package com.app.rxkotlinapiexamples.networkservice

import android.content.Context
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

class ApiClientPosts(context: Context) {

    private var retrofit: Retrofit? = null
    var apiInterface: ApiInterface? = null
    private var httpClient = OkHttpClient.Builder()
    val BASE_URL = "https://jsonplaceholder.typicode.com/"

    init {
        getClient(context)
        apiInterface = setInterFace()
    }

    private fun getClient(context: Context): Retrofit? {
        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
            httpClient.connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(ConnectivityInterceptor(context))
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return retrofit
    }


    private fun setInterFace(): ApiInterface {
        return retrofit!!.create(ApiInterface::class.java)
    }

    companion object {
        private var apiClient: ApiClientPosts? = null

        fun getInstance(context: Context): ApiClientPosts {
            if (apiClient == null) {
                apiClient = ApiClientPosts(context)
            }
            return apiClient as ApiClientPosts
        }

        fun getResponse(response: ResponseBody): JSONObject? {
            var `object`: JSONObject? = null
            try {
                val result = response.string()
                `object` = JSONObject(result)
            } catch (e: JSONException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return `object`
        }

        fun onErrorHandler(t: Throwable, context: Context) {
            try {
                t.printStackTrace()
                when {
                    t.javaClass.name.equals(
                        NoConnectivityException::class.java.name,
                        ignoreCase = true
                    ) -> {
                        //Handle error message
                    }
                    t.javaClass.name.equals(
                        SocketTimeoutException::class.java.name,
                        ignoreCase = true
                    ) -> {
//Handle error message
                    }
                    else -> {
                        if (t is HttpException) {
                            var errorBody = t.response()!!.errorBody()!!.string()
                            //Handle error message
                        }else{
                            //Handle error message
                        }

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun isSuccess(data: JSONObject): Boolean {
            return data.optString("status") == "success"
        }
    }
}