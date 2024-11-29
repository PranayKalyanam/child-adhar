package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityFingerPrintBinding
import com.example.myapplication.model.ChildDetailsModel

@Suppress("DEPRECATION")
class FingerPrintActivity : AppCompatActivity() {
    //Binding View
    private lateinit var binding: ActivityFingerPrintBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFingerPrintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the child details passed via Intent
        val childDetails = intent.getParcelableExtra<ChildDetailsModel>("child_details_model")

        binding.fingerPrintVerifyButton.setOnClickListener {
            // Create an intent to navigate to ChildDetailsActivity
            val intent = Intent(binding.root.context, ChildrenDetailsActivity::class.java)

            // Send the childId and child details to the ChildDetailsActivity
            intent.putExtra("child_details_model", childDetails)
            binding.root.context.startActivity(intent)
            finish()
        }
    }
}