package com.jeevasubburaj.alertmydestination

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.app.ShareCompat.IntentBuilder
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException




class MainActivity : AppCompatActivity() {
    val PLACE_PICKER_REQUEST = 1
    val MY_PERMISSIONS_REQUEST_FINE_LOCATION = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {

                try {
                    val builder = PlacePicker.IntentBuilder()
                    startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST)
                }
                catch (e: GooglePlayServicesRepairableException) {
                    e.printStackTrace()
                } catch (e: GooglePlayServicesNotAvailableException) {
                    e.printStackTrace()
                }
            }
            else{
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                val place = PlacePicker.getPlace(data, this)
                val toastMsg = String.format("Place: %s", place.getName())
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show()
            }
        }
    }

}
