package com.repose.noted.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.repose.noted.PdfViewer
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
        holder.textView.text = item.stringResourseId
        holder.imageView.setOnClickListener {
            with(model){
                setPdfName(holder.textView.text.toString())
            }
            Log.d("pdfName:", holder.textView.text.toString())
            val intent = Intent(
                ctx,
                PdfViewer::class.java
            )
            intent.putExtra("Course", "${model.coursesSelected.value.toString()}")
            intent.putExtra("Semester", "${model.semesterSelected.value.toString()}")
            intent.putExtra("PDF", "${model.pdfName.value.toString()}")
            ctx.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = dataset.size
}