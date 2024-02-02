package com.example.menufragment

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebIconDatabase.IconListener
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SettingFragment : Fragment(R.layout.home_fragment){
    val data = mutableListOf<String>().apply {
        for(i in 1..100) {
            add(i.toString())
        }
    }
    lateinit var  recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = RecyclerView(requireContext()).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SettingAdapter(requireContext(),data)
        }
        recyclerView = view

        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    override fun onStop() {
        super.onStop()

    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = "Setting"
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
            setDisplayHomeAsUpEnabled(true);
        }
        menu.clear()
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        Log.i("TAG","New check")
        when(item.itemId){
            101->{
                data.removeAt(item.groupId)
                recyclerView.adapter?.notifyItemRemoved(item.groupId)
            }
            102 -> {
                data.add(item.groupId,"new insert Row")
                recyclerView.adapter?.notifyItemInserted(item.groupId)
            }
        }
        return super.onContextItemSelected(item)
    }

}