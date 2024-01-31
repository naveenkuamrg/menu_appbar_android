package com.example.menufragment

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SettingAdapter(val context : Context,val data : MutableList<String>) : RecyclerView.Adapter<CViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CViewHolder {
       return CViewHolder(TextView(context))
    }

    override fun getItemCount(): Int {
       return  data.count()
    }

    override fun onBindViewHolder(holder: CViewHolder, position: Int) {
        holder.textView.text = data[position]
    }
}


class CViewHolder(itemView: TextView) : RecyclerView.ViewHolder(itemView){
    var textView = itemView.apply {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}