package com.repose.noted.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.repose.noted.R
import com.repose.noted.model.Semester

class SemesterAdapter(private val ctx: Context, private val dataset: List<Semester>): RecyclerView.Adapter<SemesterAdapter.SemesterViewHolder>() {
    class SemesterViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.textView2)
        val imageView: ImageView = view.findViewById(R.id.imageView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemesterAdapter.SemesterViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_grid_layout, parent, false)

        return SemesterAdapter.SemesterViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: SemesterAdapter.SemesterViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = ctx.resources.getString(item.stringResourseId)
        holder.imageView.setImageResource(item.imageResourceId)
    }

    override fun getItemCount() = dataset.size


}