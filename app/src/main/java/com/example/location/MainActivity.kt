package com.example.location

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.location.ui.theme.LocationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LocationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    )
                }
            }
        }
    }
}

@Composable

fun LocationDisplay(
    locationUtils: LocationUtils,
    context: Context
){

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if (permissions[Manifest.permission.ACCESS_COARSE_LOCATION]== true
                && permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true){
                //Have access to Location
            }else{
                //Ask for Permission
                val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )||ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,  //we can use main activity here or not it doesn't matter because it was declared in the above segment
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                if (rationalRequired){
                    Toast.makeText(context,
                        "Location permission is required for this feature",Toast.LENGTH_LONG).show()

                }
            }
        })


    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Text("Location not available")

        Button(onClick = {
            if (locationUtils.hasLocationPermission(context)){
                // Permission already granted update the location
            }else{
                //Request location permission
            }
        }) {
            Text("Get Location")
        }
    }
}