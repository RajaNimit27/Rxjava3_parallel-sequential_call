package com.app.rxkotlinapiexamples.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel


open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext


}