package com.othadd.chopmore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.othadd.chopmore.databinding.CategoriesListItemBinding

class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private var items = listOf<Category>()
    fun updateItemList(items: List<Category>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoriesListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(var binding: CategoriesListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category){
            binding.apply {
                nameTextView.text = category.name
                pictureImageView.setImageResource(category.picture)
            }
        }
    }

}