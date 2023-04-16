package com.example.realestatemobileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PropertyListFragment : Fragment() {

    companion object {
        fun newInstance() = PropertyListFragment()
    }

    val mPropertyArray: ArrayList<Property> = ArrayList()
    private lateinit var mPropertyDetailsViewModel: PropertyDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPropertyArray.add(Property("1", "33 Cottonwood Crescent, Calamvale, Qld 4116", "$773,855", "Sean Johansson", "house1"))
        mPropertyArray.add(Property("2", "9 Westgate Place, The Gap, Qld 4061", "$944,603", "Gianni Carbone", "house2"))
        mPropertyArray.add(Property("3", "55 Kempster Street, Sandgate, Qld 4017", "$849,000", "Kristy Kelly", "house3"))
        mPropertyArray.add(Property("4", "6/70 Freeman Road, Durack, Qld 4077", "$595,218", "Sarah Bailey","house4"))
        mPropertyArray.add(Property("5", "12 Arrol Street, Camp Hill, Qld 4152", "$1,977,972", "Roger Carr", "house5"))
        mPropertyArray.add(Property("6", "37 Kahli Place, Carseldine, Qld 4034", "$970,692", "Libby Patrick", "house6"))

        //val resourceId = this.resources.getIdentifier("house1", "drawable", this.getPackageName());
        //findViewById<ImageView>(R.id.house_image).setImageResource(resourceId)

        val context = activity as ViewModelStoreOwner
        mPropertyDetailsViewModel = ViewModelProvider(context).get(PropertyDetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val recyclerView = inflater.inflate(R.layout.list_page_fragment, container, false) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        var editedProperty = mPropertyDetailsViewModel.editedProperty.value

        if (editedProperty != null) {
            for (i in 0 until (mPropertyArray.size - 1)) {
                if (mPropertyArray[i].id == editedProperty.id) {
                    mPropertyArray[i] = editedProperty
                    break
                }
            }
        }

        recyclerView.adapter = PropertyAdapter(mPropertyArray)
        return recyclerView
    }

}
