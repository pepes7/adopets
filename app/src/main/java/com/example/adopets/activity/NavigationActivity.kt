package com.example.adopets.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import com.example.adopets.PetsFragment
import com.example.adopets.R

class NavigationActivity : AppCompatActivity() {


    lateinit var toolbar: ActionBar


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_pets -> {
                val petsFragment = PetsFragment()
                openFragment(petsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mapa -> {

                val mapaFragment = MapaFragment.newInstance()
                openFragment(mapaFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_perfil -> {

                val perfilFragment = PerfilFragment.newInstance()
                openFragment(perfilFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_ajustes -> {

                val ajustesFragment = AjustesFragment.newInstance()
                openFragment(ajustesFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.nav_view)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
