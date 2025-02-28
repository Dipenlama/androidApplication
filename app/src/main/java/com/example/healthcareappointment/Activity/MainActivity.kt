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

import com.example.healthcareappointment.ViewModel.MainViewModel
import com.example.healthcareappointment.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
   private val viewModel=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()

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