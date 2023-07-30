package com.app.rxkotlinapiexamples.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.rxkotlinapiexamples.R
import com.app.rxkotlinapiexamples.databinding.EmployeeLayoutBinding
import com.app.rxkotlinapiexamples.databinding.StudentLayoutBinding
import com.bumptech.glide.Glide
import com.app.rxkotlinapiexamples.models.Employee
import com.app.rxkotlinapiexamples.models.Student


class StudentItemAdapter(context: Context) :
    RecyclerView.Adapter<StudentItemAdapter.ViewHolder>() {


    var studentList: List<Student> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.student_layout, parent, false
            )
        )

    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(studentList[position])
    }

    inner class ViewHolder(itemView: StudentLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding: StudentLayoutBinding = itemView
        fun bind(student: Student?) {
            binding.data= student
            binding.executePendingBindings()
        }
    }


}