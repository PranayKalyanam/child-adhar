package com.example.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.AllChildrenDetailsActivity
import com.example.myapplication.ChildrenDetailsActivity
import com.example.myapplication.FamilyDetailsActivity
import com.example.myapplication.databinding.FragmentHomeBinding



class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.familyDetailsButton.setOnClickListener{

            val intent = Intent(requireContext(), FamilyDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.childDetailsButton.setOnClickListener{

            val intent = Intent(requireContext(), AllChildrenDetailsActivity::class.java)
            startActivity(intent)
        }
    }

}