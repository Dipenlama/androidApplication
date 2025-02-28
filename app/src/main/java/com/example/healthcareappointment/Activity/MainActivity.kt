package com.example.healthcareappointment.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthcareappointment.Adapter.CategoryAdapter
import com.example.healthcareappointment.Adapter.TopDoctorAdapter

import com.example.healthcareappointment.ViewModel.MainViewModel
import com.example.healthcareappointment.databinding.ActivityMainBinding



class MainActivity : BaseActivity() {
   private lateinit var binding: ActivityMainBinding
   private val viewModel=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initTopDoctors()


    }

    private fun initTopDoctors() {
        binding.apply {
            progressBarTopDoctor.visibility=View.VISIBLE
            viewModel._doctors.observe(this@MainActivity, Observer{
                recyclerViewTopDoctor.layoutManager=LinearLayoutManager(
                    this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                recyclerViewTopDoctor.adapter=TopDoctorAdapter(it)
                progressBarTopDoctor.visibility=View.GONE
            })
        }
        viewModel.loadDoctors()
    }

    private fun initCategory(){
        binding.progressBarCategory.visibility= View.VISIBLE
            viewModel.category.observe(this, Observer {
                binding.viewCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding.viewCategory.adapter=CategoryAdapter(it)
                binding.progressBarCategory.visibility=View.GONE
            })
        viewModel.loadCateogry()

        }

    }