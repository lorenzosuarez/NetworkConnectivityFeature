package com.challenge.challengetaller.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


// You are tasked with implementing a network connectivity feature in the mobile application you are developing for CXOs. The feature should allow the app to check the network status and provide real-time information about the current network connection.
// Requirements:
// 1. Implement a function that checks the availability of an internet connection on the device.
// 2. Display the current network status (e.g., connected, disconnected) in a user-friendly format within the app's UI.
// 3. When there is an active internet connection, retrieve additional information about the connection speed (e.g., download/upload speeds) using appropriate APIs or libraries.
// 4. Implement error handling for scenarios where there is no internet connection or if retrieving speed information fails.




class NetworksUtils(private val context: Context) {
    fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        )
    }
}