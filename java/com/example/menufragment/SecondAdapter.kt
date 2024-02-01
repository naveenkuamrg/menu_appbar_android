package com.example.menufragment

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.ActionMode
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class SecondAdapter(var context : Context,var data : MutableList<VMode>) : RecyclerView.Adapter<SecondViewHolder>() {

    var actionMode : ActionMode? = null
    private  val actionModeCallback  = object : ActionMode.Callback{
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.contextual_menu,menu)
            Log.i("Tag","vn - ${actionMode?.menu.toString()}")
            return  true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when(item?.itemId){
                R.id.delete -> {

                    val size = data.size
                    Log.i("check"," ch - ${data[size - 1].name}")
                    for(i in size-1 downTo  0){
                        if(data[i].isSelected){
                            data.removeAt(i)
                        }
                    }
                }
            }

            notifyDataSetChanged()
            return  true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
            for(i in data){
                i.isSelected = false
            }
            notifyDataSetChanged()
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondViewHolder {
        return  SecondViewHolder(CView(context))
    }

    override fun getItemCount(): Int {
        return  data.count()
    }

    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {
       holder.textView.text = data[position].name
        holder.itemView.setOnClickListener {
            if(actionMode != null) {
                data[position].isSelected = !data[position].isSelected
                notifyItemChanged(position)
            }
        }
        if(data[position].isSelected){
            holder.itemView.setBackgroundColor(Color.GRAY)
        }else{
            holder.itemView.setBackgroundColor(Color.BLACK)
        }

        holder.itemView.setOnLongClickListener {
            data[position].isSelected = !data[position].isSelected
            notifyItemChanged(position)
            when (actionMode) {
                null -> {
                    // Start the CAB using the ActionMode.Callback defined earlier.
                    actionMode = (context as AppCompatActivity).startActionMode(actionModeCallback)
                    data[position].isSelected = true
                    true
                }
                else -> false
            }

        }
    }
}


class SecondViewHolder(itemView: CView) : RecyclerView.ViewHolder(itemView){
    var textView = itemView.findViewById<TextView>(R.id.text)
}

class CView(context: Context) : LinearLayout(context){
    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        addView(TextView(context).apply {
            id = R.id.text
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



class VMode (val name : String){
    var isSelected = false
}