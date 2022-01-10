package com.ideologics.BusTopper

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.ideologics.BusTopper.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback , GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var binding: ActivityMapsBinding

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.getUiSettings().setZoomControlsEnabled(true)
        map.setOnMarkerClickListener(this)

        val myPlace = LatLng(40.73, -73.99)  // this is New York
        map.addMarker(MarkerOptions().position(myPlace).title("My Favorite City"))
        map.moveCamera(CameraUpdateFactory.newLatLng(myPlace))

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 12.0f))


        setUpMap()

    }

    override fun onMarkerClick(p0: Marker): Boolean {
        return false
    }


    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
    }
}