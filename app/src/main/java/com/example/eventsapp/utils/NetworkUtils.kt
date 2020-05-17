package com.example.eventsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

/**
 * Helper Function to check the internet connection
 */
fun isNetworkAvailable(context: Context): Boolean {
    var isConnected = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    //Check if Connectivity manager is not NUll
    connectivityManager?.let {
        //We check the type of the connection is available for Internet
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                isConnected = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    else -> false
                }
            }
        } else {
            //Handling Case for the API level 21 network handle
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected) {
                isConnected = true
            } else {
                return isConnected
            }
        }
    }
    // Return result by default the value will be false
    return isConnected
}