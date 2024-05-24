package com.ci.v1_ci_view.ui.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CIPermissionsUtil {
    companion object{
        enum class Permissions(val code:Int){
            CODE_REQUEST_CAMERA(1)
            ,CODE_REQUEST_LOCATION(2)
        }

        fun registerCameraPermission(activity: Activity): Boolean{
            if(ContextCompat.checkSelfPermission(activity,Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA),Permissions.CODE_REQUEST_CAMERA.code)
                return false
            }
            return true
        }
        fun registerLocationPermission(activity: Activity): Boolean{
            if(ContextCompat.checkSelfPermission(activity,Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),Permissions.CODE_REQUEST_LOCATION.code)

                return false
            }
            return true
        }
    }
}