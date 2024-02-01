package com.example.menufragment

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.ActionMode
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class SecondAdapter(var context : Context,var data : MutableList<VMode>) : RecyclerView.Adapter<SecondViewHolder>(),PopupMenu.OnMenuItemClickListener {

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
            mode?.finish()
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
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
//        return SecondViewHolder(itemView)
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

        holder.imagView.setOnClickListener {
            showMenu(holder.imagView, position)
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

    fun showMenu(v: View,position: Int) {
        PopupMenu(context, v).apply {
            // MainActivity implements OnMenuItemClickListener.
            setOnMenuItemClickListener(this@SecondAdapter)
            menu.add(position,101,0,"delete")
            show()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId){
            101 ->{
                data.removeAt(item.groupId)
//                notifyItemRemoved(item.groupId)
                notifyDataSetChanged()
            }
        }
        return  true
    }
}


class SecondViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var textView = itemView.findViewById<TextView>(R.id.textView)
    var imagView = itemView.findViewById<ImageView>(R.id.image_view)
}

class CView(context: Context) : ConstraintLayout(context){
    val textView =  TextView(context).apply {
        id = R.id.text
        textAlignment = TEXT_ALIGNMENT_CENTER
        layoutParams = LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT).apply {
            setMargins(10,10,0,10)
            setPadding(10,30,10,10)
            id = R.id.textView
            minHeight = 100
        }
        setBackgroundColor(Color.DKGRAY)
    }

    val imageView = ImageView(context).apply {
        id = R.id.image_view
        setImageResource(R.drawable.ic_arrow_drop_down)
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            0
        )
        setBackgroundColor(Color.DKGRAY)

    }
    init {
        id = View.generateViewId()
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT).apply {
//            setMargins(10,10,10,10)
        }
        addView(textView)
        addView(imageView)



        val constraintSet = ConstraintSet()
        constraintSet.clone(this)
        //TextView
        constraintSet.connect(textView.id,ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP)
        constraintSet.connect(textView.id,ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM)
        constraintSet.connect(textView.id,ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START)
        constraintSet.connect(textView.id,ConstraintSet.END,imageView.id,ConstraintSet.START)


        //ImageView
        constraintSet.connect(imageView.id,ConstraintSet.TOP,textView.id,ConstraintSet.TOP)
        constraintSet.connect(imageView.id,ConstraintSet.BOTTOM,textView.id,ConstraintSet.BOTTOM)
        constraintSet.connect(imageView.id,ConstraintSet.END,this.id,ConstraintSet.END)


        constraintSet.applyTo(this)


        setBackgroundColor(Color.LTGRAY)

    }
}



class VMode (val name : String){
    var isSelected = false
}