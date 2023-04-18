package com.example.realestatemobileapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class PropertyDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PropertyDetailsFragment()
    }

    private lateinit var mPropertyDetailsViewModel: PropertyDetailsViewModel
    private lateinit var mProperty: Property

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var view = inflater.inflate(R.layout.fragment_details, container, false)

        val context = activity as ViewModelStoreOwner
        mPropertyDetailsViewModel =
            ViewModelProvider(context).get(PropertyDetailsViewModel::class.java)

        mProperty = mPropertyDetailsViewModel.selectedProperty.value!!

        loadImage(mProperty.imageName, view.findViewById(R.id.house_image))

        val addressText = view?.findViewById<TextView>(R.id.address)
        addressText?.text = mProperty.address

        val priceText = view?.findViewById<TextView>(R.id.price)
        priceText?.text = mProperty.price.toString()

        val agentNameText = view?.findViewById<TextView>(R.id.agentName)
        agentNameText?.text = mProperty.agentName

        //val frag = currentFragment as PropertyDetailsFragment
        //frag.checkDone(true)

        val doneButton = view?.findViewById<Button>(R.id.done)

        doneButton?.setOnClickListener { _ ->

            // if (addressText != null && !price.matches(Regex("\\$?[0-9]+"))) && agentNameText != null)
            if (addressText != null && priceText != null && agentNameText != null) {
                mProperty.address = addressText?.text.toString()

                mProperty.price = priceText?.text.toString().toInt()
                priceText?.text.toString().drop(1).toInt()

                mProperty.agentName = agentNameText?.text.toString()

                mPropertyDetailsViewModel.editedProperty.value = mProperty
            }
            else
            {
                ErrorDialogFragment().show(
                    childFragmentManager, ErrorDialogFragment.TAG)
            }
        }

        return view
/*
        fun checkDone(backPressed: Boolean) {
            if (addressText != null && priceText != null && agentNameText != null) {
                ErrorDialogFragment().show(
                    childFragmentManager, ErrorDialogFragment.TAG)
            }
            else
            {
                mProperty.address = addressText?.text.toString()

                mProperty.price = priceText?.text.toString().toInt()
                priceText?.text.toString().drop(1).toInt()

                mProperty.agentName = agentNameText?.text.toString()

                mPropertyDetailsViewModel.editedProperty.value = mProperty
            }
        }
*/
    }

    fun loadImage(imageName: String, imageView: ImageView) {

        val resourceId =
            this.getResources().getIdentifier(imageName, "drawable", this.getContext()?.getPackageName());

        if (resourceId != 0) {
            imageView.setImageResource(resourceId);
        }
    }
}