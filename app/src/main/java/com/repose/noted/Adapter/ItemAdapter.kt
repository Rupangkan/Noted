package com.repose.noted.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.repose.noted.R
import com.repose.noted.StartFragmentDirections
import com.repose.noted.model.Courses

class ItemAdapter(private val ctx: Context, private val dataset: List<Courses>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.textView2)
        val imageView: ImageView = view.findViewById(R.id.imageView2)
        val cardView: ConstraintLayout = view.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_grid_layout, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = ctx.resources.getString(item.stringResourseId)
        holder.imageView.setImageResource(item.imageResourceId)
        holder.cardView.setOnClickListener  {
            // Create an action from WordList to DetailList
            // using the required arguments
//            val action = LetterListFragmentDirections.actionLetterListFragmentToWordListFragment(letter = holder.button.text.toString())4
            val action = StartFragmentDirections.actionStartFragmentToSemesterFragment()
            // Navigate using that action
            holder.view.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = dataset.size


}