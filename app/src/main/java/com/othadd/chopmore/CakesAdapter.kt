package com.othadd.chopmore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.othadd.chopmore.databinding.CakesListItemBinding
import com.othadd.chopmore.databinding.CategoriesListItemBinding

class CakesAdapter: RecyclerView.Adapter<CakesAdapter.ViewHolder>() {

    private var items = listOf<Cake>()
    fun updateItemList(items: List<Cake>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CakesListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(var binding: CakesListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(cake: Cake){
            binding.apply {
                cakeNameTextView.text = cake.name
                cakeImageView.setImageResource(cake.picture)
                priceTextView.text = cake.price
            }
        }
    }
}