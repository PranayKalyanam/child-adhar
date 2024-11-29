package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.LostChildDetailsItemCardBinding
import com.example.myapplication.model.LostChildDetailsModel

class LostChildDetailsAdapter(
    private val lostChildDetailsModelList: List<LostChildDetailsModel>,
) : RecyclerView.Adapter<LostChildDetailsAdapter.LostChildViewHolder>() {

    // ViewHolder class to hold references to views
    inner class LostChildViewHolder(private val binding: LostChildDetailsItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(lostChild: LostChildDetailsModel) {
            // Bind data to the views in the item layout
            val fullName = "${lostChild.firstName} ${lostChild.lastName}"
            binding.childNameText.text = fullName
            binding.childFatherNameText.text = lostChild.fatherName
            binding.childMotherNameText.text = lostChild.motherName
            binding.childFatherPhoneNumberText.text = lostChild.fatherPhone?.toString() ?: "N/A"
        }
    }

    // Create new views (called by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LostChildViewHolder {
        val binding = LostChildDetailsItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LostChildViewHolder(binding)
    }

    // Replace the contents of a view (called by the layout manager)
    override fun onBindViewHolder(holder: LostChildViewHolder, position: Int) {
        val lostChild = lostChildDetailsModelList[position]
        holder.bind(lostChild)
    }

    // Return the size of the data set (called by the layout manager)
    override fun getItemCount(): Int {
        return lostChildDetailsModelList.size
    }
}