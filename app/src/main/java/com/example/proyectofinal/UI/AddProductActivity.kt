package com.example.proyectofinal.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.proyectofinal.R
import com.example.proyectofinal.UI.fragments.FirebaseFragment
import com.example.proyectofinal.databinding.ActivityAddProductBinding
import com.example.proyectofinal.databinding.FragmentAccountBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    // Nuevo Firbase
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        firestore = Firebase.firestore
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegisterCotel.setOnClickListener{
            val strDrink = binding.tilCocktailName.editText?.text.toString()
            val strCategory = binding.tilCategory.editText?.text.toString()
            val isfavorite = binding.switchFavorite.isChecked
            if(strDrink.isNotEmpty() && strCategory.isNotEmpty()) {
                addProductToFirestore(strDrink,strCategory, isfavorite)
            }
        }
        getCotelesIsFavorite()
    }

    private fun addProductToFirestore(
        strDrink: String,
        strCategory: String,
        isfavorite: Boolean) {

        val coteles = hashMapOf<String, Any>(
            "strDrink" to strDrink,
            "strCategory" to strCategory,
            "isfavorite" to isfavorite
        )
        firestore.collection("cotelesfire").add(coteles)
            .addOnSuccessListener {
                Toast.makeText(this,"cocktail Agregado : ${it.id}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener() {
                Toast.makeText(this,"Ocurrion un error.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getCotelesIsFavorite(){
        firestore.collection("cotelesfire").whereEqualTo("isfavorite",true).get()
            .addOnSuccessListener {
                val coteles = it.documents
                for (document in coteles){
                    Log.d("Productos",document.id)
                }
            }
            .addOnFailureListener{}
    }

    private fun getCoteles(){
        firestore.collection("cotelesfire").get()
            .addOnSuccessListener {
                val coteles = it.documents
                for (document in coteles){
                    // Nuevo
                    val cotelesId = document.id
                    val strDrinkName = document.getString("strDrink")
                    if (strDrinkName != null) {
                        Log.d("Coteles", "ID: $cotelesId, Nombre: $strDrinkName")
                    }
                }
            }
            .addOnFailureListener{}
    }

}

