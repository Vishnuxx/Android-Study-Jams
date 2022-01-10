package com.ideologics.BusTopper.RouteActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ideologics.BusTopper.DriverActivity.DriverDashBoardActivity
import com.ideologics.BusTopper.DriverCodeActivity
import com.ideologics.BusTopper.R
import com.ideologics.BusTopper.StudentActivity.StudentDashboardActivity
import com.ideologics.BusTopper.Utils
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

    private lateinit var fAuth : FirebaseAuth
    private lateinit var fDbRef : DatabaseReference
    private lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routes)

        context = this

        init()
        setupRecyclerView()
        initLogic()


    }

    fun init() {
        fab = findViewById(R.id.floatingActionButton)
        dao = ItemRoomDatabase.getDatabase(this.applicationContext).itemDao()
        fAuth = FirebaseAuth.getInstance()
        fDbRef = FirebaseDatabase.getInstance().getReference("/Users/")
    }

    fun initLogic() {
        fab.setOnClickListener {
            setUpEditAlertDialog()
        }

        val back : ImageView= findViewById(R.id.back)
        back.setOnClickListener{
            when(Utils.getString(context , "userType")){
                "Student" -> {
                    startActivity(Intent(context , StudentDashboardActivity::class.java))

                }

                "Driver" -> {
                    startActivity(Intent(context , DriverDashBoardActivity::class.java))
                }
        }
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

        when(Utils.getString(this , "userType")){
            "Student" -> {

                while( index < 10 ){

                    boardingPoints.add(Location(0,"location"+ index , "","", index < 4 ))
                    index++
                }
            }

            "Driver" -> {
                GlobalScope.launch {
                    while( index < dao.getCount() ){
                        boardingPoints.add(dao.getBoardingPoint(index))
                        index++
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }



        val recyclerView : RecyclerView = findViewById(R.id.recyclerview)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = RouteAdapter(boardingPoints)
        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()
    }



}

