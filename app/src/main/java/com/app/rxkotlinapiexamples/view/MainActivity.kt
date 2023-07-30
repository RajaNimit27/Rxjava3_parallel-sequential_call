package com.app.rxkotlinapiexamples.view
import com.app.rxkotlinapiexamples.adapters.EmployeeItemAdapter
import com.app.rxkotlinapiexamples.adapters.StudentItemAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.rxkotlinapiexamples.R
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.rxkotlinapiexamples.base.Status
import com.app.rxkotlinapiexamples.databinding.ActivityMainBinding
import com.app.rxkotlinapiexamples.networkservice.ApiClient
import org.json.JSONObject
import com.app.rxkotlinapiexamples.viewmodel.HomeViewModel


class MainActivity : AppCompatActivity() {

    lateinit var mainBinding:ActivityMainBinding
    lateinit var concatAdapter: ConcatAdapter
    lateinit var studentitemAdapter: StudentItemAdapter
    lateinit var employeeItemAdapter: EmployeeItemAdapter
    lateinit var homeViewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding=DataBindingUtil.setContentView(this@MainActivity,R.layout.activity_main)
        initView()
        initViewModel()
        setUpObserver()
    }

    private fun initView() {
        try {
            var lm = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
            mainBinding.recyclerview.layoutManager = lm

            studentitemAdapter= StudentItemAdapter(this@MainActivity)
            employeeItemAdapter = EmployeeItemAdapter(this@MainActivity)

            concatAdapter = ConcatAdapter(studentitemAdapter,employeeItemAdapter)
            mainBinding.recyclerview.adapter=concatAdapter
        } catch (e: Exception) {
            e.stackTrace
        }
    }


    private fun initViewModel() {
        try {
            homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
            homeViewModel.getStudentEmployeeList()
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    /*Livedata from View Model*/
    private fun setUpObserver() {
        try {
            homeViewModel.resource.observe(this@MainActivity) {
                when (it.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        if(it.data?.studentList?.isNotEmpty() == true){
                            studentitemAdapter.studentList= it.data.studentList
                            studentitemAdapter.notifyDataSetChanged()
                        }
                        if(it.data?.employeeList?.isNotEmpty() == true){
                            employeeItemAdapter.employeeList= it.data.employeeList
                            employeeItemAdapter.notifyDataSetChanged()
                        }
                    }
                    Status.ERROR -> {
                        ApiClient.onErrorHandler(it.error!!, this@MainActivity)
                    }
                }

            }

        } catch (e: Exception) {
            e.stackTrace
        }
    }

    private fun updateUI(jsonObject: JSONObject) {
        try {

        } catch (e: Exception) {
            e.stackTrace
        }
    }

}