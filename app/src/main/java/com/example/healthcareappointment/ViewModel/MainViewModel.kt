package com.example.healthcareappointment.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.example.healthcareappointment.Domain.CategoryModel
import com.example.healthcareappointment.Domain.DoctorsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.sql.Ref

class MainViewModel():ViewModel () {
    private val firebaseDatabase=FirebaseDatabase.getInstance()

    private val _category=MutableLiveData<MutableList<CategoryModel>>()
    public val _doctors=MutableLiveData<MutableList<DoctorsModel>>()

    val category:LiveData<MutableList<CategoryModel>> = _category

    fun loadCateogry(){
        val Ref=firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               val lists= mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(CategoryModel::class.java)
                    if (list !=null){
                        lists.add(list)
                    }
                }
                _category.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error loading categories: ${error.message}")
            }


        })
    }
    fun loadDoctors(){
        val ref=firebaseDatabase.getReference("Doctors")
        ref.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<DoctorsModel>()

                for (childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(DoctorsModel::class.java)

                    if (list!=null){
                        lists.add(list)
                    }
                }
                _doctors.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error loading categories: ${error.message}")
            }

        })
    }

}