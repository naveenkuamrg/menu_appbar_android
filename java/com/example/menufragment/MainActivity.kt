package com.example.menufragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentContainer
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    val fragmentManager = supportFragmentManager
    lateinit var bottomNavigationView : BottomNavigationView
    lateinit var fragmentContainer: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentContainer = findViewById<View>(R.id.fragment_container_view_tag)
        if(savedInstanceState == null){
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(fragmentContainer.id, HomeFragment())
            transaction.commit()
        }
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){

               R.id.nav_home ->{
                   val transaction = fragmentManager.beginTransaction()
                   transaction.replace(fragmentContainer.id, HomeFragment())
                   transaction.commit()
               }
               R.id.nav_setting ->{
                   val transaction = fragmentManager.beginTransaction()
                   transaction.replace(fragmentContainer.id, SecondFragment())
                   transaction.commit()
               }
               R.id.nav_delete ->{
                   val transaction = fragmentManager.beginTransaction()
                   transaction.replace(fragmentContainer.id, DeleteFragment())
                   transaction.commit()
               }

           }



            return@setOnItemSelectedListener true
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fragment_menu,menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_setting -> {
               val  transition = fragmentManager.beginTransaction()
                transition.replace(fragmentContainer.id,SettingFragment())
                transition.addToBackStack("setting")
                transition.commit()
            }
            R.id.clear_history->{
                Toast.makeText(this,"Clear History",Toast.LENGTH_SHORT).show()
            }
            R.id.search_bar->{
                Toast.makeText(this,"Search Bar",Toast.LENGTH_SHORT).show()
            }
            android.R.id.home->{
               fragmentManager.popBackStack()
            }


        }

        return super.onOptionsItemSelected(item)
    }





}