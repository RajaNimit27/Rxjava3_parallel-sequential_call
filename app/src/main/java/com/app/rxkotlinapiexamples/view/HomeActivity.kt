package com.app.rxkotlinapiexamples.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.rxkotlinapiexamples.R
import com.app.rxkotlinapiexamples.databinding.ActivityHomeBinding
import com.app.rxkotlinapiexamples.helpers.Utils


class HomeActivity : AppCompatActivity() {

    lateinit var homeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = DataBindingUtil.setContentView(this@HomeActivity, R.layout.activity_home)
        homeBinding.activity = this
    }

    fun parallelCallRequest() {
        try {
            Utils.gotoActivity(this@HomeActivity, MainActivity::class.java, false)
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    fun sequentialCallRequest() {
        try {
            Utils.gotoActivity(this@HomeActivity, UserCommentsActivity::class.java, false)
        } catch (e: Exception) {
            e.stackTrace
        }
    }


}