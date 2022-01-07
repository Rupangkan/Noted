package com.repose.noted.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.repose.noted.R
import com.repose.noted.data.Starred
import com.repose.noted.model.AppViewModel

class StarredAdapter(private val model: AppViewModel, private val ctx: Context, private val item: Starred): ListAdapter<Starred, StarredAdapter.StarredViewHolder>(DiffCallback) {

    private val myModel = model

    class StarredViewHolder(private var view: View) :
        RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(R.id.textView2)
            val imageView: ImageView = view.findViewById(R.id.imageView2)
            val cardView: ConstraintLayout = view.findViewById(R.id.card)

//        fun bind(item: Starred) {
//            binding.textView2.text = item.pdf
////            binding.itemPrice.text = item.getFormattedPrice()
////            binding.itemQuantity.text = item.quantityInStock.toString()
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarredAdapter.StarredViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_grid_layout, parent, false)

        return StarredAdapter.StarredViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: StarredAdapter.StarredViewHolder, position: Int) {
        val current = getItem(position)
        holder.textView.text = item.dbname
        holder.imageView.setOnClickListener{

        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Starred>() {
            override fun areItemsTheSame(oldItem: Starred, newItem: Starred): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Starred, newItem: Starred): Boolean {
                return oldItem.dbname == newItem.dbname
            }
        }
    }

    override fun getItemCount() = item.id



}