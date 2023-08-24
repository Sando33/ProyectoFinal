package layout

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.model.Coteles
import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.TextView
import com.example.proyectofinal.R

class FireAdapter : RecyclerView.Adapter<FireAdapter.ViewHolder>() {

    private val dataList = mutableListOf<Coteles>()

    fun setData(data: List<Coteles>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val drinkNameTextView: TextView = itemView.findViewById(R.id.drinkNameTextView)
        val categoryTextView: TextView = itemView.findViewById(R.id.categoryTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemfirebase, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]

        holder.drinkNameTextView.text = currentItem.strDrink
        holder.categoryTextView.text = currentItem.strCategory
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}