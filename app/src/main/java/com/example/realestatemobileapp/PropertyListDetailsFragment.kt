package com.example.realestatemobileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PropertyListDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PropertyListDetailsFragment()
    }

    val propertyArray: ArrayList<Property> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        propertyArray.add(Property("1", "33 Cottonwood Crescent, Calamvale, Qld 4116", 773855, "Sean Johansson"))
        propertyArray.add(Property("2", "9 Westgate Place, The Gap, Qld 4061", 944603, "Gianni Carbone"))
        propertyArray.add(Property("3", "55 Kempster Street, Sandgate, Qld 4017", 849000, "Kristy Kelly"))
        propertyArray.add(Property("4", "6/70 Freeman Road, Durack, Qld 4077", 595218, "Sarah Bailey"))
        propertyArray.add(Property("5", "12 Arrol Street, Camp Hill, Qld 4152", 1977972, "Roger Carr"))
        propertyArray.add(Property("6", "37 Kahli Place, Carseldine, Qld 4034", 970692, "Libby Patrick"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val recyclerView = inflater.inflate(R.layout.list_page_fragment, container, false) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = PropertyAdapter(propertyArray)
        return recyclerView
    }

}
