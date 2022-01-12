package com.repose.noted.Adapter

import android.content.Context
import android.content.Intent
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.repose.noted.PdfViewer
import com.repose.noted.R
import com.repose.noted.Utils.Download
import com.repose.noted.data.Starred
import com.repose.noted.model.AppViewModel

class StarredAdapter(private val model: AppViewModel, private val ctx: Context): ListAdapter<Starred, StarredAdapter.StarredViewHolder>(DiffCallback) {

    private lateinit var dbref: StorageReference

    class StarredViewHolder(val view: View): RecyclerView.ViewHolder(view){
            val textView: TextView = view.findViewById(R.id.textView2)
            val imageView: ImageView = view.findViewById(R.id.imageView2)
            val star: ImageView = view.findViewById(R.id.star)
            val download: ImageView = view.findViewById(R.id.download)
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

        holder.download.setOnClickListener{
            dbref = FirebaseStorage.getInstance().reference.child("${model.path.value.toString()}/${holder.textView.text}")
            Log.d("Path", "${model.path.value.toString()}/${holder.textView.text}")
            try {
                dbref.downloadUrl.addOnSuccessListener {
                    Download().downloadFile(ctx, holder.textView.text.toString(), DIRECTORY_DOWNLOADS, it.toString())
                    Toast.makeText(ctx, "Downloading ${holder.textView.text}", Toast.LENGTH_SHORT).show()
                }
                    .addOnFailureListener{
                        Toast.makeText(ctx, "Downloading ${holder.textView.text} failed", Toast.LENGTH_SHORT).show()
                        it.printStackTrace()
                    }
            }catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(ctx, "Downloading ${holder.textView.text} failed", Toast.LENGTH_SHORT).show()


            }

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