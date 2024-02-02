package com.example.menufragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


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

        //custome actionBar
//        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
//        supportActionBar?.setDisplayShowCustomEnabled(true)
//        supportActionBar?.setCustomView(R.layout.custom_action_bar)
        val colorDrawable = ColorDrawable(Color.parseColor("#FF000000"))
        supportActionBar?.setBackgroundDrawable(colorDrawable)
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
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_subject)
            setDisplayHomeAsUpEnabled(true);
        }
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_setting -> {
               val  transition = fragmentManager.beginTransaction()
                transition.replace(fragmentContainer.id,SettingFragment(),"back")
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
                if(fragmentManager.findFragmentByTag("back") != null) {
                    fragmentManager.popBackStack()
                }else{
                    val  transition = fragmentManager.beginTransaction()
                    transition.add(fragmentContainer.id,NavigationViewFragment(),"back")
                    transition.addToBackStack("setting")
                    transition.commit()
                }
            }
            R.id.filter ->{
                PopupMenu(this@MainActivity,findViewById<View>(R.id.filter)).apply {
                     val menu1 = menu.addSubMenu(66,66,66,"news")
                     menu1.add(0,0,0,"naveen")
//                     menu1.setGroupCheckable(0,true,true);
                     val inflater: MenuInflater = menuInflater
                     inflater.inflate(R.menu.filter_menu, menu)
                    menu1.setGroupEnabled(0,true)
                    menu1.setGroupVisible(0,true)
                    menu1.setGroupCheckable(0,true,false)
                    menu.setGroupCheckable(R.id.group_delete,true,true)
                    menu.setGroupVisible(R.id.group_delete,true)
                    menu.setGroupEnabled(R.id.group_delete,true)

                     show()

                  setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
                      override fun onMenuItemClick(item: MenuItem?): Boolean {
                          when(item?.itemId){
                              0 ->{
                                  item.isChecked = !item.isChecked
                              }
                          }

                          return true
                      }

                  })
                }
            }


        }

        return true
    }





}