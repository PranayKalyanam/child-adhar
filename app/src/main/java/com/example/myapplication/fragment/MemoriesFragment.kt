package com.example.myapplication.fragment

import android.os.Bundle
import android.content.Intent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MemoriesPage
import com.example.myapplication.adapter.MemoriesItemAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMemoriesBinding


class MemoriesFragment : Fragment() {
    private lateinit var binding: FragmentMemoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_memories, container, false)
        binding = FragmentMemoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create some static data
        val titles = listOf("Memory 1", "Memory 2", "Memory 3")
        val dates = listOf("01 Jan 2022", "15 Mar 2022", "20 Sep 2022")
        val images = listOf(R.drawable.banner, R.drawable.siblings, R.drawable.banner)

        setUpAdapter(titles, dates, images)

        binding.appCompatButton.setOnClickListener {
            // Navigate to ActivityMemoriesPage
            val intent = Intent(requireContext(), MemoriesPage::class.java)
            startActivity(intent)
        }


    }

    private fun setUpAdapter(titles: List<String>, dates: List<String>, images: List<Int>) {
// Initialize the adapter with static data
        val adapter = MemoriesItemAdapter(titles, dates, images, requireContext())
        // Set up RecyclerView
        binding.memoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.memoriesRecyclerView.adapter = adapter
    }
}