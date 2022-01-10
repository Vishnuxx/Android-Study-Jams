package com.ideologics.BusTopper.RouteActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ideologics.BusTopper.R
import com.ideologics.room.Inventory.ItemRoomDatabase
import com.ideologics.room.Inventory.Location
import com.ideologics.room.Inventory.LocationDao
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoutesActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adapter : RouteAdapter // RecyclerView.Adapter<RouteAdapter.ViewHolder>? = null

    private lateinit var boardingPoints : ArrayList<Location>

    private lateinit var dao : LocationDao

    private lateinit var fab : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routes)

        init()
        setupRecyclerView()
        initLogic()


    }

    fun init() {
        fab = findViewById(R.id.floatingActionButton)
        dao = ItemRoomDatabase.getDatabase(this.applicationContext).itemDao()
    }

    fun initLogic() {
        fab.setOnClickListener {
            setUpEditAlertDialog()
        }

    }

    @DelicateCoroutinesApi
    fun addBoardingPoint(name : String, location : String, time : String) {
        GlobalScope.launch {
            val loc = Location(
                0,
                name,
                location,
                time,
                false
            )
            dao.insert(loc)
            boardingPoints.add(loc)
        }

    }

    fun setUpEditAlertDialog() {

        val view = layoutInflater.inflate(R.layout.add_location_alert,null , false)
        val  button = view.findViewById<Button>(R.id.submit)
        val mname = view.findViewById<EditText>(R.id.name_alert)
        val mlocation = view.findViewById<EditText>(R.id.location_alert)
        val mtime = view.findViewById<EditText>(R.id.time_alert)


        val builder = AlertDialog.Builder(this).create()
        builder.setView(view)

        builder.setCanceledOnTouchOutside(true)
        builder.show()


        button.setOnClickListener {
            if(mname.text.isEmpty() && mlocation.text.isEmpty() && mtime.text.isEmpty()){
                Toast.makeText(this , "please fill in all fields" , Toast.LENGTH_SHORT).show()
            }else{
                addBoardingPoint(mname.text.toString() , mlocation.text.toString() , mtime.toString())
                Toast.makeText(this , "Boarding point Added" , Toast.LENGTH_SHORT).show()
                adapter.notifyDataSetChanged()
                builder.dismiss()
            }

        }


    }

    fun setupRecyclerView() {

        boardingPoints = ArrayList<Location>()

        var index = 0
        val dao = ItemRoomDatabase.getDatabase(this).itemDao()
        GlobalScope.launch {
            Log.d("apppppppplee______" , dao.getCount().toString())
            while( index < dao.getCount() ){
                boardingPoints.add(dao.getBoardingPoint(index))
                index++
            }
            adapter.notifyDataSetChanged()
        }


        val recyclerView : RecyclerView = findViewById(R.id.recyclerview)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = RouteAdapter(boardingPoints)
        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()
    }


}

