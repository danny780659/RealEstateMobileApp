package com.example.realestatemobileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class PropertyDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PropertyDetailsFragment()
    }

    private lateinit var mPropertyDetailsViewModel: PropertyDetailsViewModel
    private lateinit var mProperty: Property

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        var view = inflater.inflate(R.layout.fragment_details, container, false)

        val context = activity as ViewModelStoreOwner
        mPropertyDetailsViewModel = ViewModelProvider(context).get(PropertyDetailsViewModel::class.java)

        mProperty = mPropertyDetailsViewModel.selectedProperty.value!!

        val addressText = view?.findViewById<TextView>(R.id.address)
        addressText?.text = mProperty.address

        val priceText = view?.findViewById<TextView>(R.id.price)
        priceText?.text = mProperty.price.toString()

        val agentNameText = view?.findViewById<TextView>(R.id.agentName)
        agentNameText?.text = mProperty.agentName

       // val imageName = view?.findViewById<TextView>(R.id.imageName)
        //imageName?.image = mProperty.imageName

        val doneButton = view?.findViewById<Button>(R.id.done)

        doneButton?.setOnClickListener { _ ->
            mProperty.address = addressText?.text.toString()
            mProperty.price = priceText?.text.toString()
            mProperty.agentName = agentNameText?.text.toString()

            mPropertyDetailsViewModel.editedProperty.value = mProperty
        }

        return view
    }
}