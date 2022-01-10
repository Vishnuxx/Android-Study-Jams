package com.ideologics.BusTopper.RouteActivity

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ideologics.BusTopper.R
import com.ideologics.room.Inventory.ItemRoomDatabase
import com.ideologics.room.Inventory.Location

class RouteAdapter(data : ArrayList<Location>) : RecyclerView.Adapter<RouteAdapter.ViewHolder>() {

    private lateinit var data : ArrayList<Location>

    init {
        this.data = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_route , parent , false)



        return ViewHolder(v )
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("___________OPPPP" , data.toString())// this.data?.get(position).toString())
        holder.itemTitle.text = data?.get(position)?.name
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle : TextView

        init {
            itemTitle = itemView.findViewById(R.id.title)


        }

    }
}