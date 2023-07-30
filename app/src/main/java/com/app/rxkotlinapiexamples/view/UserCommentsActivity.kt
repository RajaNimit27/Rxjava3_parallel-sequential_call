package com.app.rxkotlinapiexamples.view
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.rxkotlinapiexamples.R
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.rxkotlinapiexamples.adapters.UserCommentsAdapter
import com.app.rxkotlinapiexamples.base.Status
import com.app.rxkotlinapiexamples.databinding.ActivityUserPostsBinding
import com.app.rxkotlinapiexamples.models.CommentModel
import com.app.rxkotlinapiexamples.networkservice.ApiClient
import com.app.rxkotlinapiexamples.viewmodel.HomeViewModel


class UserCommentsActivity : AppCompatActivity() {

    lateinit var userPostsBinding:ActivityUserPostsBinding
    lateinit var userCommentsAdapter: UserCommentsAdapter
    lateinit var homeViewModel: HomeViewModel
    var commentsArrayList= arrayListOf<CommentModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userPostsBinding=DataBindingUtil.setContentView(this@UserCommentsActivity,R.layout.activity_user_posts)
        initView()
        initViewModel()
        setUpObserver()
    }

    private fun initView() {
        try {
            var lm = LinearLayoutManager(this@UserCommentsActivity, LinearLayoutManager.VERTICAL,false)
            userPostsBinding.recyclerview.layoutManager = lm
            userCommentsAdapter= UserCommentsAdapter()
            userPostsBinding.recyclerview.adapter=userCommentsAdapter
        } catch (e: Exception) {
            e.stackTrace
        }
    }


    private fun initViewModel() {
        try {
            homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
            homeViewModel.getUserComments()
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    /*Livedata from View Model*/
    private fun setUpObserver() {
        try {
            homeViewModel.resourcePosts.observe(this@UserCommentsActivity) {
                when (it.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                       if(it.data?.isNotEmpty()==true){
                           userCommentsAdapter.commentList.addAll(it.data)
                           userCommentsAdapter.notifyDataSetChanged()
                        }

                    }
                    Status.ERROR -> {
                        ApiClient.onErrorHandler(it.error!!, this@UserCommentsActivity)
                    }
                }

            }

        } catch (e: Exception) {
            e.stackTrace
        }
    }



}