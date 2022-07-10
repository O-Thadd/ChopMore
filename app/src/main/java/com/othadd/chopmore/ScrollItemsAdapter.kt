package com.othadd.chopmore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.othadd.chopmore.databinding.CakeScrollListItemBinding
import com.othadd.chopmore.databinding.CakesListItemBinding

class ScrollItemsAdapter:  RecyclerView.Adapter<ScrollItemsAdapter.ViewHolder>() {

    private var items = listOf<ScrollItem>()
    fun updateItemList(items: List<ScrollItem>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CakeScrollListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(var binding: CakeScrollListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(scrollItem: ScrollItem){
            binding.apply {
                circleView.setBackgroundResource(if(scrollItem.selected) R.drawable.scroll_indicator_selected else R.drawable.scroll_indicator)
            }
        }
    }
}