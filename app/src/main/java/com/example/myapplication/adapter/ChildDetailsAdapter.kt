package com.example.myapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ChildrenDetailsActivity
import com.example.myapplication.databinding.ChildItemCardBinding
import com.example.myapplication.model.ChildDetailsModel

class ChildDetailsAdapter(
    private val childDetailsModelList: List<ChildDetailsModel>,
):
    RecyclerView.Adapter<ChildDetailsAdapter.ChildViewHolder>(){
    // Create the ViewHolder for each child item
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChildViewHolder {
        // Inflate the layout for each child item
        val binding = ChildItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }

    // Bind the child data to the views in the ViewHolder
    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val child = childDetailsModelList[position]
        holder.bind(child)
    }

    // Return the total number of items in the list
    override fun getItemCount(): Int {
        return childDetailsModelList.size
    }

    //ViewHolder to hold and bind the view for each child Item
    class ChildViewHolder(private val binding: ChildItemCardBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(child: ChildDetailsModel){
            // Bind data to the views in the layout
            binding.childNameText.text = child.firstName
            binding.childFatherNameText.text = child.fatherName
            binding.childMotherNameText.text = child.motherName
            binding.childDateOfBirthText.text = child.dateOfBirth

            // Set click listener on the Edit button
            binding.seeDetailsButton.setOnClickListener {
                // Create an intent to navigate to ChildDetailsActivity
                val intent = Intent(binding.root.context, ChildrenDetailsActivity::class.java)

                // Send the childId and child details to the ChildDetailsActivity
                intent.putExtra("child_details_model", child)
                binding.root.context.startActivity(intent)
            }
        }
    }
}