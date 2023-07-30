package com.app.rxkotlinapiexamples.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.rxkotlinapiexamples.R
import com.app.rxkotlinapiexamples.databinding.EmployeeLayoutBinding
import com.bumptech.glide.Glide
import com.app.rxkotlinapiexamples.models.Employee


class EmployeeItemAdapter(context: Context) :
    RecyclerView.Adapter<EmployeeItemAdapter.ViewHolder>() {


    var employeeList: List<Employee> = arrayListOf()
    private var context: Context = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.employee_layout, parent, false
            )
        )

    }


    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(employeeList[position])
    }

    inner class ViewHolder(itemView: EmployeeLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding: EmployeeLayoutBinding = itemView
        fun bind(employee: Employee) {
            binding.data = employee
            employee.profile_image?.let {
                loadImage(
                    context = context,
                    imageView = binding.profileImage,
                    it
                )
            }
            binding.executePendingBindings()
        }

        private fun loadImage(context: Context, imageView: ImageView, url: String) {
            try {
                Glide
                    .with(context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView);
            } catch (e: Exception) {
                e.stackTrace
            }
        }

    }


}