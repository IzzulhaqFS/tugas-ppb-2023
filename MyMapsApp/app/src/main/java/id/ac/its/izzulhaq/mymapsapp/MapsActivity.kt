package id.ac.its.izzulhaq.mymapsapp

import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import id.ac.its.izzulhaq.mymapsapp.databinding.ActivityMapsBinding
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var searchMode = LAT_LNG_MODE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.btnGo.setOnClickListener {
            val methodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            methodManager.hideSoftInputFromWindow(it.windowToken, 0)
            if (searchMode == LAT_LNG_MODE) {
                goToLocation()
            }
            else {
                searchLocation()
            }
        }

        binding.btnCoordinate.setOnClickListener {
            changeSearchMode(GEOCODER_MODE)
        }

        binding.btnGeocoder.setOnClickListener {
            changeSearchMode(LAT_LNG_MODE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.normal -> mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            R.id.hybrid -> mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            R.id.satellite -> mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            R.id.terrain -> mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            R.id.none -> mMap.mapType = GoogleMap.MAP_TYPE_NONE
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true
        mMap.uiSettings.isCompassEnabled = true
    }

    private fun goTo(lat: Double, lng: Double, zoom: Float) {
        val newLocation = LatLng(lat, lng)
        mMap.addMarker(MarkerOptions().position(newLocation).title("Marker in $lat:$lng"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, zoom))
    }

    private fun goToLocation() {
        val lat = binding.edtLatitude.text.toString()
        val lng = binding.edtLongitude.text.toString()
        val zoom = binding.edtZoom.text.toString()

        when {
            lat.isEmpty() -> binding.edtLatitude.error = "Latitude harus diisi"
            lng.isEmpty() -> binding.edtLongitude.error = "Longitude harus diisi"
            zoom.isEmpty() -> binding.edtZoom.error = "zoom harus diisi"
            else -> {
                goTo(lat.toDouble(), lng.toDouble(), zoom.toFloat())
            }
        }
    }

    private fun changeSearchMode(searchMode: Int) {
        if (searchMode == GEOCODER_MODE) {
            this.searchMode = LAT_LNG_MODE
            binding.apply {
                edtLatitude.visibility = View.VISIBLE
                edtLongitude.visibility = View.VISIBLE
                edtSearch.visibility = View.INVISIBLE
            }
        }
        else {
            this.searchMode = GEOCODER_MODE
            binding.apply {
                edtLatitude.visibility = View.INVISIBLE
                edtLongitude.visibility = View.INVISIBLE
                edtSearch.visibility = View.VISIBLE
            }
        }
    }

    private fun searchLocation() {
        val locName = binding.edtSearch.text.toString()
        val edZoom = binding.edtZoom.text.toString()
        val geocoder = Geocoder(baseContext)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocationName(locName, 1) { locList ->
                if (locList.size != 0) {
                    val address = locList[0]
                    val lat = address.latitude
                    val lng = address.longitude
                    val zoom = if (edZoom.isNotEmpty()) edZoom.toFloat() else "15".toFloat()

                    goTo(lat, lng, zoom)
                }
                else {
                    Toast.makeText(this, "Lokasi tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else {
            try {
                @Suppress("DEPRECATION")
                val list = geocoder.getFromLocationName(locName, 1)
                if (list!!.size != 0) {
                    val address = list[0]
                    val lat = address.latitude
                    val lng = address.longitude
                    val zoom = if (edZoom.isNotEmpty()) edZoom.toFloat() else "15".toFloat()

                    goTo(lat, lng, zoom)
                }
                else {
                    Toast.makeText(this, "Lokasi tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
            catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private const val LAT_LNG_MODE = 1
        private const val GEOCODER_MODE = 2
    }
}