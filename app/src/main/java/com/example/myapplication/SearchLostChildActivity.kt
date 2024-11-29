package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.LostChildDetailsAdapter
import com.example.myapplication.databinding.ActivitySearchLostChildBinding
import com.example.myapplication.model.LostChildDetailsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchLostChildActivity : AppCompatActivity() {
    //Binding View
    private lateinit var binding: ActivitySearchLostChildBinding

    //Firebase database
    private val database = FirebaseDatabase.getInstance().getReference("LostChildren")

    //lost child model list
    private val lostChildDetailsModelList = mutableListOf<LostChildDetailsModel>()

    // Adapter for RecyclerView
    private lateinit var adapter: LostChildDetailsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchLostChildBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //In beginning make recycler view and no info invisible
        binding.SearchLostChildDetailsRecyclerView.visibility = View.GONE
        binding.noInfoText.visibility = View.GONE

        // Handle Search Button Click
        binding.lostChildSearchButton.setOnClickListener {
            val fatherName = binding.searchViewFatherName.query.toString().trim()
            if (fatherName.isNotEmpty()) {
                searchByFatherName(fatherName)
            } else {
                Toast.makeText(this, "Please enter a father's name to search.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // Initialize RecyclerView
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Initially, the RecyclerView will not show any data (empty list)
        binding.SearchLostChildDetailsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchLostChildActivity)
            adapter = LostChildDetailsAdapter(lostChildDetailsModelList).also { this@SearchLostChildActivity.adapter = it }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchByFatherName(fatherName: String) {
        lostChildDetailsModelList.clear()
        adapter.notifyDataSetChanged()

    database.orderByChild("fatherName").equalTo(fatherName)
        .addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Clear previous results
                lostChildDetailsModelList.clear()

                // Loop through the children found in Firebase for the given father's name
                for(childSnapshot in snapshot.children){
                    val lostChild = childSnapshot.getValue(LostChildDetailsModel::class.java)

                    if(lostChild != null){
                        lostChildDetailsModelList.add(lostChild)
                    }
                }

                // Notify adapter about data change
                adapter.notifyDataSetChanged()

                //clear text in search view
                binding.searchViewFatherName.setQuery("", false)

                // Show a message if no children found
                if (lostChildDetailsModelList.isEmpty()) {
                    binding.SearchLostChildDetailsRecyclerView.visibility = View.GONE
                    binding.noInfoText.visibility = View.VISIBLE
                    Toast.makeText(this@SearchLostChildActivity,"No records found for father name: $fatherName",Toast.LENGTH_SHORT)
                        .show()
                } else {
                    binding.SearchLostChildDetailsRecyclerView.visibility = View.VISIBLE
                    binding.noInfoText.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Show error message
                Toast.makeText(this@SearchLostChildActivity,"Error during search: ${error.message}",Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

}