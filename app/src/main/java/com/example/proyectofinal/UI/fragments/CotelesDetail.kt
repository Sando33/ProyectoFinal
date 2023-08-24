package com.example.proyectofinal.UI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.proyectofinal.R
import com.example.proyectofinal.databinding.FragmentCotelesDetailBinding
import com.example.proyectofinal.model.Coteles

class CotelesDetail : Fragment() {

    private lateinit var b: FragmentCotelesDetailBinding
    private val args: CotelesDetailArgs by navArgs()
    private lateinit var coteles: Coteles
    private lateinit var viewModel: CotelesDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coteles = args.cotel
        viewModel= ViewModelProvider(requireActivity())[CotelesDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentCotelesDetailBinding.inflate(inflater,container,false)
        return  b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b.cocktailNameTextView.text = coteles.strDrink
        b.txtCategory.text = coteles.strCategory
        if (coteles.strDrinkThumb.isNotEmpty()){
            b.cocktailImg.load(coteles.strDrinkThumb)
        }else{
            b.cocktailImg.setImageResource(R.drawable.img_coteles)
        }
        setImage()
        b.btnFavorite.setOnClickListener{
            if(coteles.isFavorite){
                // Eliminar de favoritos
                coteles.isFavorite = false
                viewModel.deleteFromFavorite(coteles)
                setImage()
            }else{
                coteles.isFavorite=true
                viewModel.addToFovorite(coteles)
                setImage()
            }
        }
    }

    private fun setImage(){
        if(coteles.isFavorite){
            b.btnFavorite.setImageResource(R.drawable.ic_favorite_fill)
        }
        else{
            b.btnFavorite.setImageResource(R.drawable.ic_favorite)
        }
    }

}