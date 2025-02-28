package com.example.healthcareappointment.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthcareappointment.Adapter.TopDoctorAdapter
import com.example.healthcareappointment.Adapter.TopDoctorAdapter2
import com.example.healthcareappointment.R
import com.example.healthcareappointment.ViewModel.MainViewModel
import com.example.healthcareappointment.databinding.ActivityTopDoctors2Binding

class TopDoctorsActivity2 : BaseActivity() {
    private lateinit var binding:ActivityTopDoctors2Binding
    private val viewModel=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTopDoctors2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        initTopDoctors()

    }
    private fun initTopDoctors() {
        binding.apply {
            progressBarTopDoctor1.visibility= View.VISIBLE
            viewModel._doctors.observe(this@TopDoctorsActivity2, Observer{
                viewTopDoctorList.layoutManager= LinearLayoutManager(
                    this@TopDoctorsActivity2, LinearLayoutManager.VERTICAL,false)
                viewTopDoctorList.adapter= TopDoctorAdapter2(it)
                progressBarTopDoctor1.visibility= View.GONE
            })
            viewModel.loadDoctors()

            backBtn1.setOnClickListener{finish()}
        }




    }
}