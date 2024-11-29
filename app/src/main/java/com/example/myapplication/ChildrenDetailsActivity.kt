package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityChildrenDetailsBinding
import com.example.myapplication.model.ChildDetailsModel

@Suppress("DEPRECATION")
class ChildrenDetailsActivity : AppCompatActivity() {
    //Binding view
    private lateinit var binding: ActivityChildrenDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildrenDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the child details passed via Intent
        val childDetails = intent.getParcelableExtra<ChildDetailsModel>("child_details_model")

        // Check if the data is not null
        if(childDetails != null){
            // Hide the 'No Info' message
            binding.childDetailsScrollView.visibility = android.view.View.VISIBLE
            binding.noInfoText.visibility = android.view.View.GONE

            //show details in Child details Page
            binding.childFirstNameEditText.setText(childDetails.firstName)
            binding.childSurnameEditText.setText(childDetails.lastName)
            binding.childFatherNameEditText.setText(childDetails.fatherName)
            binding.childMotherNameEditText.setText(childDetails.motherName)
            binding.childFatherPhoneEditText.setText(childDetails.fatherPhone.toString())
            binding.childDateOfBirthEditText.setText(childDetails.dateOfBirth)
            binding.childPlaceOfBirthEditText.setText(childDetails.placeOfBirth)
            binding.childTimeOfBirthEditText.setText(childDetails.timeOfBirth)
            binding.childGenderEditText.setText(childDetails.gender)
            binding.childDisabilityEditText.setText(childDetails.disability)
            binding.childAddressEditText.setText(childDetails.permanentAddressOfParents)
        } else {
            // Hide the 'child details' message and show "No info" message
            binding.childDetailsScrollView.visibility = android.view.View.GONE
            binding.noInfoText.visibility = android.view.View.VISIBLE
        }
    }
}