package com.repose.noted.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.repose.noted.R
import com.repose.noted.SemesterFragmentDirections
import com.repose.noted.model.AppViewModel
import com.repose.noted.model.Semester

class SemesterAdapter(private val model: AppViewModel, private val ctx: Context, private val dataset: List<Semester>): RecyclerView.Adapter<SemesterAdapter.SemesterViewHolder>() {

    private val myModel = model

    class SemesterViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.textView2)
        val imageView: ImageView = view.findViewById(R.id.imageView2)
        val cardView: ConstraintLayout = view.findViewById(R.id.card)
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

        holder.cardView.setOnClickListener {
            val action = SemesterFragmentDirections.actionSemesterFragmentToPdfContainerFragment()
            with(model){
                setSemester(holder.textView.text.toString())
            }
            Log.d("course", model.coursesSelected.value.toString())
            Log.d("semester", model.semesterSelected.value.toString())
            holder.view.findNavController().navigate(action)
        }
    }



    override fun getItemCount() = dataset.size


}