package com.example.healthcareappointment.Activity

import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.healthcareappointment.Domain.DoctorsModel
import com.example.healthcareappointment.R
import com.example.healthcareappointment.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    private lateinit var binding:ActivityDetailBinding
    private lateinit var item:DoctorsModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()

    }
    private fun getBundle(){
        item=intent.getParcelableExtra("object")!!

        binding.apply {
            titleTxt.text=item.Name
            SpecialTxt.text=item.Special
            patientsTxt.text=item.Patients.toString()
            bioTxt.text=item.Biography
            addressTxt.text=item.Address
            experienceTxt.text=item.Experience.toString()+"years"
            ratingTxt.text="${item.Rating}"
            backBtn.setOnClickListener{finish()}

            websiteBtn.setOnClickListener{
                val i= Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(item.Site))
                startActivity(i)
            }

            messageBtn.setOnClickListener{
                val uri=Uri.parse("smsto:${item.Mobile}")
                val intent= Intent(Intent.ACTION_SENDTO,uri)
                intent.putExtra("sms_body", "the SMS text")
                startActivity(intent)
            }

            callBtn.setOnClickListener{
                val uri="tel:"+item.Mobile.trim()
                val intent=Intent(Intent.ACTION_DIAL,
                    Uri.parse(uri))
                startActivity(intent)
            }
            directionBtn.setOnClickListener{
                val intent=Intent(Intent.ACTION_VIEW,
                    Uri.parse(item.Location))
                startActivity(intent)
            }

            shareBtn.setOnClickListener{
                val intent=Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT, item.Name)
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    item.Name+ " "+ item.Address+ " "+ item.Mobile
                )
                startActivity(Intent.createChooser(intent, "Choose one"))
            }

            Glide.with(this@DetailActivity)
                .load(item.Picture)
                .into(img)

        }

    }
}