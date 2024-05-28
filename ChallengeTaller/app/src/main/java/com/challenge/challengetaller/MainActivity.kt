package com.challenge.challengetaller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.challenge.challengetaller.ui.NetworkStatusScreen
import com.challenge.challengetaller.utils.NetworksUtils
import com.challenge.challengetaller.viewmodel.NetworkViewModel
import com.challenge.challengetaller.viewmodel.NetworkViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkUtils = NetworksUtils(
            applicationContext)

        val viewModelFactory = NetworkViewModelFactory(application, networkUtils)
        val viewModel = ViewModelProvider(owner = this , viewModelFactory)[NetworkViewModel::class.java]

        setContent {
            NetworkStatusScreen(viewModel = viewModel)
        }

        viewModel.checkNetworkStatus()
    }
}