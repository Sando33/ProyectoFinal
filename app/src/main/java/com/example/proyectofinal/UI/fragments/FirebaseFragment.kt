package com.example.proyectofinal.UI.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.UI.AddProductActivity
import com.example.proyectofinal.UI.CreateUser
import com.example.proyectofinal.databinding.FragmentFavoriteBinding
import com.example.proyectofinal.databinding.FragmentFirebaseBinding

import com.example.proyectofinal.model.Coteles
import com.google.firebase.firestore.FirebaseFirestore
import layout.FireAdapter


class FirebaseFragment : Fragment() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var adapter: FireAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding : FragmentFirebaseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View? {
        binding = FragmentFirebaseBinding.inflate(inflater, container, false)
        firestore = FirebaseFirestore.getInstance()
        adapter = FireAdapter()
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        loadFirestoreData()

        // Configura el clic del botón
        binding.addProduct.setOnClickListener { goToAddProductActivity() }

        return binding.root


    }

    // Carga los datos de FireBase
    private fun loadFirestoreData() {
        firestore.collection("cotelesfire").get()
            .addOnSuccessListener { querySnapshot ->
                val cotelesList = mutableListOf<Coteles>()
                for (document in querySnapshot.documents) {
                    val idDrink = document.getString("idDrink") ?: ""
                    val strDrink = document.getString("strDrink") ?: ""
                    val strCategory = document.getString("strCategory") ?: ""
                    val isFavoriteBoolean = document.getBoolean("isfavorite") ?: false
                    val isFavoriteString = if (isFavoriteBoolean) "true" else "false"
                    val coteles = Coteles(id, idDrink, strDrink, strCategory, isFavoriteString)
                    cotelesList.add(coteles)
                }
                adapter.setData(cotelesList)
            }
            .addOnFailureListener { exception ->
                // Handle failure
            }
    }
    // Función para ir a AddProductActivity
    private fun goToAddProductActivity() {
        val intent = Intent(requireContext(), AddProductActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}