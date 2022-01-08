package com.repose.noted.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.repose.noted.PdfViewer
import com.repose.noted.R
import com.repose.noted.data.Starred
import com.repose.noted.model.AppViewModel
import com.repose.noted.model.PdfContainer
import com.repose.noted.model.RoomViewModel


class PdfContainerAdapter(private val activity: FragmentActivity, private val roomViewModel: RoomViewModel, private val model: AppViewModel, private val ctx: Context, private val dataset: List<PdfContainer>, private val paths: String): RecyclerView.Adapter<PdfContainerAdapter.PdfViewHolder>() {

    class PdfViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        var pdfInStarred: Boolean = false
        val textView: TextView = view.findViewById(R.id.textView2)
        val imageView: ImageView = view.findViewById(R.id.imageView2)
        val star: ImageView = view.findViewById(R.id.star)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PdfViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.notes_card_layout, parent, false)

        return PdfViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: PdfViewHolder, position: Int) {
        val item = dataset[position]

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
            val bool = retrieveItem(pdfName, holder)
            Log.d("bool", bool.toString())
            with(model){
                setPath(paths)
            }
//            if(!bool) {
//                addNewItem(paths, pdfName)
//                holder.star.setImageResource(R.drawable.ic_star_fill)
//            } else {
//                holder.star.setImageResource(R.drawable.ic_star_empty)
//            }
            var pdfName = holder.textView.text.toString()
            Log.d("PathPdfContainerAdapter", paths)
            Log.d("pdfNamePdfAdapter", pdfName)
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

    private fun retrieveItem(name: String, holder: PdfViewHolder): Boolean {
        var item: Starred? = null
        roomViewModel.retrieveItem(name).observe(activity, Observer { selectedItem ->
            item = selectedItem
            if(item!=null){
                holder.pdfInStarred = true
            }
            Log.d("itemfromdb", item.toString())
        })

        return holder.pdfInStarred
//        Log.d("itemfromdb", "${item}" )
    }

    override fun getItemCount(): Int = dataset.size

}