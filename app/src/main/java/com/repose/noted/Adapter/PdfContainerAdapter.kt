package com.repose.noted.Adapter

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableWrapper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.repose.noted.PdfViewer
import com.repose.noted.R
import com.repose.noted.data.Starred
import com.repose.noted.model.AppViewModel
import com.repose.noted.model.PdfContainer
import com.repose.noted.model.RoomViewModel
import android.R.integer
import android.widget.Toast
import androidx.cardview.widget.CardView


class PdfContainerAdapter(private val roomViewModel: RoomViewModel, private val model: AppViewModel, private val ctx: Context, private val dataset: List<PdfContainer>, private val paths: String): RecyclerView.Adapter<PdfContainerAdapter.PdfViewHolder>() {

    class PdfViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        var pdfInStarred: Boolean = false
        val textView: TextView = view.findViewById(R.id.textView2)
        val imageView: ImageView = view.findViewById(R.id.imageView2)
        val star: ImageView = view.findViewById(R.id.star)
        var card: CardView = view.findViewById(R.id.cardView)
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
        val bool = retrieveItem(pdfName)

        if(bool)
            holder.star.setImageResource(R.drawable.ic_star_fill)
        else
            holder.star.setImageResource(R.drawable.ic_star_empty)

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
//            addNewItem(paths, pdfName)
//            Log.d("imageAlpha", holder.star.drawable)
//            holder.star.setTag(R.drawable.ic_star_empty)
//            var itemTag: Int = holder.star.getTag(R.drawable.ic_star_empty) as Int
//            itemTag = if (itemTag == null) 0 else itemTag

//            Log.d("itemTag", itemTag.toString())

//            if(itemTag == R.drawable.ic_star_empty){
//                holder.star.setImageResource(R.drawable.ic_star_fill)
//            }else {
//                holder.star.setImageResource(R.drawable.ic_star_empty) // 2131230884
//            }


            Log.d("bool", bool.toString())
            with(model){
                setPath(paths)
            }
            if(!bool) {
                addNewItem(paths, pdfName)
                holder.star.setImageResource(R.drawable.ic_star_fill)
                Toast.makeText(holder.card.context, "$pdfName added to Starred", Toast.LENGTH_SHORT).show()
            } else {
                deleteItem(pdfName)
                holder.star.setImageResource(R.drawable.ic_star_empty)
                Toast.makeText(holder.card.context, "$pdfName removed from Starred", Toast.LENGTH_SHORT).show()
            }
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

    private fun deleteItem(name: String) {
        try {
            var item = roomViewModel.retrieveItem(name)
            Log.d("item", item.toString())
            roomViewModel.deleteItem(item)
        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    private fun retrieveItem(name: String): Boolean {
        var item: Starred? = null

        item = roomViewModel.retrieveItem(name)
        if(item==null) return false
        return true
    }

    override fun getItemCount(): Int = dataset.size

}