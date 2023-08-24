package com.example.proyectofinal.UI.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.example.proyectofinal.R
import com.example.proyectofinal.UI.Login
import com.example.proyectofinal.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var email: String

    override fun onCreate(savedinstanceState: Bundle?) {
        super.onCreate(savedinstanceState)
        sharedPreferences = requireActivity().getSharedPreferences(
            Login.SESSION_PREFERENCES_KEY,
            Context.MODE_PRIVATE
        )
        email = sharedPreferences.getString(Login.EMAIL_DATA, "") ?: ""
        firebaseAuth = Firebase.auth
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtEmail.text = email
        binding.btnLogout.setOnClickListener {
            with(sharedPreferences.edit()){
                putString(Login.EMAIL_DATA, "")
                apply()
            }
            firebaseAuth.signOut()
            goToLogin()
        }

        // Obtener el switch del layout
        val switchDarkMode = binding.swtich

        // Establecer el listener para el switch
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun goToLogin() {
        val intent = Intent(requireActivity(),Login::class.java)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }

}