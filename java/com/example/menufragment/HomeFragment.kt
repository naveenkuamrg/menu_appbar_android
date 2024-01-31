package com.example.menufragment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.home_fragment) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = "Home"
        view.findViewById<TextView>(R.id.text).text = "HOME"
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        Log.i("TAG","onPrepareOptionsMenu home")
        menu.findItem(R.id.search_bar).isVisible = false
        menu.findItem(R.id.clear_history).isVisible = false
    }
}


