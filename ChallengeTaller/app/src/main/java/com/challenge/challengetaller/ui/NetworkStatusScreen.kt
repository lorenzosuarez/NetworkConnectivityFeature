package com.challenge.challengetaller.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.challenge.challengetaller.data.model.NetworkStatus
import com.challenge.challengetaller.viewmodel.NetworkViewModel

// You are tasked with implementing a network connectivity feature in the mobile application you are developing for CXOs. The feature should allow the app to check the network status and provide real-time information about the current network connection.
// Requirements:
// 1. Implement a function that checks the availability of an internet connection on the device.
// 2. Display the current network status (e.g., connected, disconnected) in a user-friendly format within the app's UI.
// 3. When there is an active internet connection, retrieve additional information about the connection speed (e.g., download/upload speeds) using appropriate APIs or libraries.
// 4. Implement error handling for scenarios where there is no internet connection or if retrieving speed information fails.

@Composable
fun NetworkStatusScreen(viewModel: NetworkViewModel) {
    val networkStatus by viewModel.networkStatus.collectAsState()
    val downloadSpeed  by viewModel.downloadSpeed.collectAsState()

    val uploadSpeed by viewModel.uploadSpeed.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Network Status: ${networkStatus::class.simpleName}")
        when (networkStatus) {
            is NetworkStatus.Connected -> {
                Text(text = "Download Speed: $downloadSpeed Mbps")
                Text(text = "Upload Speed: $uploadSpeed MBps")
            }
            is NetworkStatus.Disconnected, is NetworkStatus.Unknown -> {
                Text(text = "No speed information available!")
            }
        }
        Button(onClick = { viewModel.checkNetworkStatus() }) {
            Text(text = "Check Newwork Status")
        }
    }
}