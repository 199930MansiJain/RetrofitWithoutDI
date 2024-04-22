package com.example.retrofitwithoutdi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.retrofitwithoutdi.R
import com.example.retrofitwithoutdi.model.RecyclerData

class CustomAdapter :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private lateinit var itemList : ArrayList<RecyclerData>

    fun setItem(list: ArrayList<RecyclerData>){
        this.itemList = list
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTextView: TextView = itemView.findViewById(R.id.textView1)
        val itemTextView2: TextView = itemView.findViewById(R.id.textView2)
        val itemImageView: ImageView = itemView.findViewById(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemTextView.text = currentItem.name
        holder.itemTextView2.text = currentItem.description
        holder.itemImageView.load(currentItem.owner.avatar_url) {
            placeholder(R.drawable.ic_launcher_background)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
