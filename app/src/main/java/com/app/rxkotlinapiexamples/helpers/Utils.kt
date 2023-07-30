package com.app.rxkotlinapiexamples.helpers

import android.app.*
import android.content.*
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Utils {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    fun gotoActivity(c: Context, ActivityToOpen: Class<out Activity>, isFinish: Boolean) {
        try {
            c.run {
                startActivity(Intent(c, ActivityToOpen))
                if (isFinish) (c as Activity).finish()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}




