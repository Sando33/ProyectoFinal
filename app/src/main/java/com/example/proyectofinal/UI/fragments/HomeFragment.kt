package com.example.proyectofinal.UI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.databinding.FragmentHomeBinding
import com.example.proyectofinal.model.Coteles

class HomeFragment : Fragment() {
    private lateinit var cocktailViewModel: HomeViewModel
    private lateinit var adapter: RVCotelesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cocktailViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = RVCotelesAdapter()
        recyclerView.adapter = adapter

        cocktailViewModel.cocktailList.observe(viewLifecycleOwner, { cocktails ->
            adapter.submitList(cocktails)
        })
    }
}