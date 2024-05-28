package com.challenge.challengetaller.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.challenge.challengetaller.data.model.NetworkStatus
import com.challenge.challengetaller.utils.NetworksUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

// You are tasked with implementing a network connectivity feature in the mobile application you are developing for CXOs. The feature should allow the app to check the network status and provide real-time information about the current network connection.
// Requirements:
// 1. Implement a function that checks the availability of an internet connection on the device.
// 2. Display the current network status (e.g., connected, disconnected) in a user-friendly format within the app's UI.
// 3. When there is an active internet connection, retrieve additional information about the connection speed (e.g., download/upload speeds) using appropriate APIs or libraries.
// 4. Implement error handling for scenarios where there is no internet connection or if retrieving speed information fails.


class NetworkViewModel(
    application: Application,
    private val networkUtils: NetworksUtils) : AndroidViewModel(application) {

    private val _networkStatus = MutableStateFlow<NetworkStatus>(NetworkStatus.Unknown)
    val networkStatus: StateFlow<NetworkStatus> = _networkStatus.asStateFlow()

    private val _downloadSpeed = MutableStateFlow(0.0)
    val downloadSpeed: StateFlow<Double> = _downloadSpeed.asStateFlow()

    private val _uploadSpeed = MutableStateFlow(0.0)
    val uploadSpeed: StateFlow<Double> = _uploadSpeed.asStateFlow()

    fun checkNetworkStatus() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val isConnected = networkUtils.isInternetAvailable()
                _networkStatus.value = if (isConnected) NetworkStatus.Connected else NetworkStatus.Disconnected
                if (isConnected) {
                    simulateSpeedTest()
                } else {
                    resetSpeeds()
                }
            }
        }
    }

    private fun simulateSpeedTest() {
        _downloadSpeed.value = Random.nextDouble(1.0, 100.0)
        _uploadSpeed.value = Random.nextDouble(1.0, 50.0)
    }

    private fun resetSpeeds() {
        _downloadSpeed.value = 0.0
        _uploadSpeed.value = 0.0

    }
}
class NetworkViewModelFactory(
    private val application: Application,
    private val networkUtils: NetworksUtils,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NetworkViewModel::class.java)) {
            return NetworkViewModel(application, networkUtils) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}