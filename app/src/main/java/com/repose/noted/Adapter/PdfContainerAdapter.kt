package com.repose.noted.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.repose.noted.R
import com.repose.noted.model.AppViewModel
import com.repose.noted.model.PdfContainer
import com.repose.noted.model.Semester

class PdfContainerAdapter(private val model: AppViewModel, private val ctx: Context, private val dataset: List<PdfContainer>): RecyclerView.Adapter<PdfContainerAdapter.PdfViewHolder>() {

    class PdfViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.textView2)
        val imageView: ImageView = view.findViewById(R.id.imageView2)
        val cardView: ConstraintLayout = view.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PdfContainerAdapter.PdfViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_grid_layout, parent, false)

        return PdfContainerAdapter.PdfViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: PdfContainerAdapter.PdfViewHolder, position: Int) {
        val item = dataset[position]
//        holder.textView.text = ctx.resources.getString(item.stringResourseId)
//        holder.imageView.setImageResource(item.imageResourceId)
        holder.textView.text = item.stringResourseId.toString()
    }

    override fun getItemCount(): Int = dataset.size
}