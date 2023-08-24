package com.example.proyectofinal.UI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {
    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[FavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVCotelesAdapter(listOf()){product ->
            val directions = FavoriteFragmentDirections.actionFavoriteFragmentToCotelesDetail(product)
            findNavController().navigate(directions)
        }
        binding.rvFavorites.adapter = adapter
        binding.rvFavorites.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        viewModel.favorites.observe(requireActivity()){coteles ->
            adapter.coteles = coteles
            adapter.notifyDataSetChanged()
        }
        viewModel.getFavorites()
    }
}