package com.example.laredoutebetatest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private var title: List<String>,private var images: List<Int>, private var details: List<String>) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemtitle: TextView = itemView.findViewById(R.id.questionTextView)
        val itemImage: ImageView = itemView.findViewById(R.id.questionImageView)
        val itemDetails: TextView = itemView.findViewById(R.id.Validate) // This refers to the button should refer to questions



    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.itemtitle.text = title[position]

        holder.itemDetails.text = details[position]
    }

    override fun getItemCount(): Int {
        return title.size
    }


}