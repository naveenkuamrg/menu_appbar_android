package com.example.menufragment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    val fragmentManager = supportFragmentManager
    lateinit var bottomNavigationView : BottomNavigationView
    lateinit var fragmentContainer: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val layout = ConstraintLayout(this).apply {
//            val _fragmentContainer = FragmentContainerView(this@MainActivity).apply {
//                id = R.id.fragment_container_view_tag
//                setBackgroundColor(Color.CYAN)
//            }
//            val _bottomNavigationView = BottomNavigationView(this@MainActivity).apply {
//                id = R.id.bottomNavigationView
//            }
//            addView(_fragmentContainer)
//            val constraintSet = ConstraintSet()
//            constraintSet.clone(this);
//            constraintSet.connect(_fragmentContainer.id,ConstraintSet.LEFT,id,ConstraintSet.LEFT)
//            constraintSet.connect(_fragmentContainer.id,ConstraintSet.RIGHT,id,ConstraintSet.RIGHT)
//            constraintSet.connect(_fragmentContainer.id,ConstraintSet.TOP,id,ConstraintSet.TOP)
//            constraintSet.connect(_fragmentContainer.id,ConstraintSet.BOTTOM,_bottomNavigationView.id,ConstraintSet.TOP)
//
//            constraintSet.connect(_bottomNavigationView.id,ConstraintSet.LEFT,id,ConstraintSet.LEFT)
//            constraintSet.connect(_bottomNavigationView.id,ConstraintSet.RIGHT,id,ConstraintSet.RIGHT)
//            constraintSet.connect(_bottomNavigationView.id,ConstraintSet.BOTTOM,this.id,ConstraintSet.BOTTOM)
//            constraintSet.constrainHeight(_bottomNavigationView.id,70)
//
//            constraintSet.applyTo(this)
//        }

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
                   fragmentManager.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                   val transaction = fragmentManager.beginTransaction()
                   transaction.replace(fragmentContainer.id, HomeFragment())
                   transaction.commit()
               }
               R.id.nav_setting ->{
                   fragmentManager.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                   val transaction = fragmentManager.beginTransaction()
                   transaction.replace(fragmentContainer.id, SecondFragment())
                   transaction.commit()
               }
               R.id.nav_delete ->{
                   fragmentManager.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE)
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