package com.example.realestatemobileapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Properties

class PropertyAdapter(var properties: List<Property>) : RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false);

        return ViewHolder(view)
    }

    override fun getItemCount() = properties.size

    override fun onBindViewHolder(propertyHolder: ViewHolder, position: Int) {
        val property = properties[position]
        propertyHolder.bind(property as Property)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener  {

        lateinit var property: Property

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Log.d("PropertyAdapter", property.address + " selected")
        }

        fun bind(property: Property) {
            this.property = property

            val addressView: TextView = view.findViewById(R.id.address)
            val priceView: TextView = view.findViewById(R.id.price)
            val agentNameView: TextView = view.findViewById(R.id.agentName)

            addressView.text = property.address
            priceView.text = property.price.toString()
            agentNameView.text = property.agentName
        }
    }
}




