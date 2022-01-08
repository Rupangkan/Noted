package com.repose.noted.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.repose.noted.PdfViewer
import com.repose.noted.R
import com.repose.noted.model.AppViewModel
import com.repose.noted.model.PdfContainer
import com.repose.noted.model.PdfName
import com.repose.noted.model.RoomViewModel


class PdfContainerAdapter(private val roomViewModel: RoomViewModel, private val model: AppViewModel, private val ctx: Context, private val dataset: List<PdfContainer>, private val paths: String): RecyclerView.Adapter<PdfContainerAdapter.PdfViewHolder>() {

    class PdfViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.textView2)
        val imageView: ImageView = view.findViewById(R.id.imageView2)
        val cardView: ConstraintLayout = view.findViewById(R.id.card)
        val star: ImageView = view.findViewById(R.id.star)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PdfContainerAdapter.PdfViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.notes_card_layout, parent, false)

        return PdfContainerAdapter.PdfViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: PdfContainerAdapter.PdfViewHolder, position: Int) {
        val item = dataset[position]

//        holder.textView.text = ctx.resources.getString(item.stringResourseId)
//        holder.imageView.setImageResource(item.imageResourceId)
        holder.textView.text = item.stringResourseId
        val pdfName = holder.textView.text.toString()


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
        holder.star.setOnClickListener{
//            retriveItem(pdfName)

            with(model){
                setPath(paths)
            }

            var pdfName = holder.textView.text.toString()
            Log.d("PathPdfContainerAdapter", paths)
            Log.d("pdfNamePdfAdapter", pdfName)
//            ldf.arguments = args
            Toast.makeText(ctx, "${model.pdfName.toString()} added to Starred", Toast.LENGTH_LONG)
            addNewItem( paths, pdfName)
            holder.star.setImageResource(R.drawable.ic_star_fill)
        }

    }

    private fun isEntryValid(path: String, pdfName: String): Boolean {
        return roomViewModel.isEntryValid(
            path,
            pdfName,
        )
    }

    private fun addNewItem( path: String, name: String) {
        if(isEntryValid(path, name)) {
            roomViewModel.addNewItem(
                path,
                name,
            )
        }
    }

//    private fun retriveItem(name: String) {
//        val retrieveItem = roomViewModel.retrieveItem(name)
//        val item = retrieveItem.toString()
//        Log.d("itemfromdb", item)
//    }


    override fun getItemCount(): Int = dataset.size
//
//    interface IUInterface{
//        fun addToDb(path: String, name: String)
//    }
}