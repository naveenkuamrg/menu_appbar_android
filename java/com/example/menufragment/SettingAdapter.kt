package com.example.menufragment

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.ContextMenu
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView

class SettingAdapter(val context : Context,val data : MutableList<String>) : RecyclerView.Adapter<CViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CViewHolder {
       return CViewHolder(RItemView(context).apply {
       })
    }

    override fun getItemCount(): Int {
       return  data.count()
    }

    override fun onBindViewHolder(holder: CViewHolder, position: Int) {
        holder.textView.text = data[position]
    }
}


class CViewHolder(itemView: RItemView) : RecyclerView.ViewHolder(itemView),View.OnCreateContextMenuListener{
    init {
        itemView.setOnCreateContextMenuListener(this)
    }

    var textView = itemView.findViewById<TextView>(R.id.text)


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu?.add(adapterPosition,101,0,"remove")
        menu?.add(adapterPosition,102,0,"add")
    }


}

class RItemView(context : Context) : LinearLayout(context){
    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        addView(TextView(context).apply {
           layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT).apply {
               setMargins(10,10,10,10)
               id = R.id.text
               minHeight = 150
           }
            setBackgroundColor(Color.DKGRAY)
        })
        gravity = Gravity.CENTER_VERTICAL
        setBackgroundColor(Color.LTGRAY)
    }

}