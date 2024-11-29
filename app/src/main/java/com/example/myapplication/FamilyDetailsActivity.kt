package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.databinding.ActivityFamilyDetailsBinding
import com.example.myapplication.model.FamilyDetailsModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FamilyDetailsActivity : AppCompatActivity() {
    //Binding View
    private lateinit var binding: ActivityFamilyDetailsBinding

    //Firebase auth and database
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    //string to store family id
    private var familyId:String?= null

    //Family Details model to store
    private var familyDetailsModel: FamilyDetailsModel?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFamilyDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = auth.currentUser?.uid
        if(userId != null){
            // Fetch the familyId associated with the user
            database.child("users").child(userId).child("familyId").get()
                .addOnSuccessListener {
                    familyId = it.getValue(String::class.java)
                    if(familyId == null){
                        Toast.makeText(this, "No family associated with this user", Toast.LENGTH_SHORT).show()
                    } else {
                        fetchFamilyDetails()
                    }
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Error fetching family ID", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "User is not authenticated", Toast.LENGTH_SHORT).show()
        }

    }

    private fun fetchFamilyDetails() {
        // Check if familyId is not null before proceeding
        if (familyId != null) {
            database.child("Families").child(familyId!!).get()
                .addOnSuccessListener {
                    if(it.exists()) {
                        // Fetch the family details directly
                        familyDetailsModel = it.getValue(FamilyDetailsModel::class.java)
                        // Update the UI if family details are found
                        if (familyDetailsModel != null) {
                            updateUi(familyDetailsModel!!)
                        } else {
                            // Show message if no details found for the familyId
                            binding.familyDetailsScrollView.visibility = View.GONE
                            binding.noInfoText.visibility = View.VISIBLE
                            Toast.makeText(this, "Family details not found", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // If no data is found under the given familyId
                        binding.familyDetailsScrollView.visibility = View.GONE
                        binding.noInfoText.visibility = View.VISIBLE
                        Toast.makeText(this, "No family details found", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error fetching family details", Toast.LENGTH_SHORT).show()
                }
        }

    }

    private fun updateUi(familyDetailsModel: FamilyDetailsModel) {
        // Update the UI with the fetched family details
        binding.familyDetailsScrollView.visibility = View.VISIBLE
        binding.noInfoText.visibility = View.GONE

        binding.familyNameEditText.setText(familyDetailsModel.familyName)
        binding.familyIncomeEditText.setText(familyDetailsModel.familyIncome)
        binding.familyAddressEditText.setText(familyDetailsModel.familyAddress)
    }
}