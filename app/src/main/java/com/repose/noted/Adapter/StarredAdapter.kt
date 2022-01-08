package com.repose.noted.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.repose.noted.PdfViewer
import com.repose.noted.R
import com.repose.noted.data.Starred
import com.repose.noted.model.AppViewModel

class StarredAdapter(private val model: AppViewModel, private val ctx: Context): ListAdapter<Starred, StarredAdapter.StarredViewHolder>(DiffCallback) {

        class StarredViewHolder(val view: View): RecyclerView.ViewHolder(view){
            val textView: TextView = view.findViewById(R.id.textView2)
            val imageView: ImageView = view.findViewById(R.id.imageView2)
            val star: ImageView = view.findViewById(R.id.star)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarredAdapter.StarredViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.notes_card_layout, parent, false)

        return StarredAdapter.StarredViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: StarredViewHolder, position: Int) {
        val current = getItem(position)

        holder.textView.text = current.dbname
//        holder.imageView.setImageResource(item.imageResourceId)
        holder.star.visibility = View.GONE
        holder.imageView.setOnClickListener{
            Log.d("Click", "Clicked")
            val intent = Intent(
                ctx,
                PdfViewer::class.java
            )
            intent.putExtra("Path", "${current.path}")
//            intent.putExtra("", "${model.semesterSelected.value.toString()}")
            intent.putExtra("PDF", "${current.dbname}")
            ctx.startActivity(intent)
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




}