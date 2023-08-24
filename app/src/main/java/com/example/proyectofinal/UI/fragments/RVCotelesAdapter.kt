package com.example.proyectofinal.UI.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.proyectofinal.R
import com.example.proyectofinal.databinding.ItemcotelesBinding
import com.example.proyectofinal.model.Coteles

class RVCotelesAdapter(var coteles: List<Coteles>,val onClickProduct:(Coteles) -> Unit): RecyclerView.Adapter<ProductVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        val binding = ItemcotelesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductVH(binding, onClickProduct)
    }

    override fun getItemCount(): Int = coteles.size

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        holder.bind(coteles[position])
    }

}

class ProductVH(private val binding : ItemcotelesBinding,val onClickProduct:(Coteles) -> Unit): RecyclerView.ViewHolder(binding.root){
    fun bind(coteles: Coteles) {
        binding.cocktailNameTextView.text = coteles.strDrink
        binding.txtCategory.text = coteles.strCategory
        if (coteles.strDrinkThumb.isNotEmpty()){
            binding.cocktailImg.load(coteles.strDrinkThumb)
        }else{
            binding.cocktailImg.setImageResource(R.drawable.img_coteles)
        }
        if (coteles.isFavorite){
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_fill)
        }else{
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite)
        }
        binding.root.setOnClickListener{
            onClickProduct(coteles)
        }
    }

}