package com.app.rxkotlinapiexamples.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.rxkotlinapiexamples.R
import com.app.rxkotlinapiexamples.databinding.CommentLayoutItemBinding
import com.app.rxkotlinapiexamples.models.CommentModel


class UserCommentsAdapter() :
    RecyclerView.Adapter<UserCommentsAdapter.ViewHolder>() {


    var commentList = arrayListOf<CommentModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.comment_layout_item, parent, false
            )
        )

    }


    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentList[position])
    }

    inner class ViewHolder(itemView: CommentLayoutItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding: CommentLayoutItemBinding = itemView
        fun bind(comment: CommentModel) {
            binding.data = comment
            binding.executePendingBindings()
        }

    }


}