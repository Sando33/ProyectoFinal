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

class RVCotelesAdapter : ListAdapter<Coteles, RVCotelesAdapter.ViewHolder>(CocktailDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemcoteles, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cocktail = getItem(position)
        holder.bind(cocktail)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cocktailNameTextView: TextView = itemView.findViewById(R.id.cocktailNameTextView)
        private val cocktailImageView: ImageView = itemView.findViewById(R.id.cocktailImageView)

        fun bind(cocktail: Coteles) {
            cocktailNameTextView.text = cocktail.strDrink
            Glide.with(itemView.context)
                .load(cocktail.strDrinkThumb)
                .into(cocktailImageView)
        }
    }
}

class CocktailDiffCallback : DiffUtil.ItemCallback<Coteles>() {
    override fun areItemsTheSame(oldItem: Coteles, newItem: Coteles): Boolean {
        return oldItem.idDrink == newItem.idDrink
    }

    override fun areContentsTheSame(oldItem: Coteles, newItem: Coteles): Boolean {
        return oldItem == newItem
    }
}