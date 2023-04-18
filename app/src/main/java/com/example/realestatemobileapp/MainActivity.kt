package com.example.realestatemobileapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var mPropertyDetailsViewModel: PropertyDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.main_toolbar))

        mPropertyDetailsViewModel = ViewModelProvider(this).get(PropertyDetailsViewModel::class.java)

        mPropertyDetailsViewModel.selectedProperty.observe(this) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PropertyDetailsFragment.newInstance())
                .addToBackStack("list_fragment")
                .commit()
        }

        mPropertyDetailsViewModel.editedProperty.observe(this) {
            supportFragmentManager.popBackStack()
        }

        if (savedInstanceState == null) {
            loadFragment(PropertyListFragment.newInstance())
        }
    }

    fun loadFragment(fragment: Fragment)
    {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onBackPressed() {

        val currentFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.container)

        if (currentFragment != null && currentFragment.view?.id == R.id.property_details_fragment)
            loadFragment(PropertyListFragment.newInstance())
        else
            finish()	// use this if it will not exit app for back pressed on list page
    }
}