package com.challenge.challengetaller.data.model

sealed class NetworkStatus {
    data object Connected : NetworkStatus()
    data object Disconnected : NetworkStatus()
    data object Unknown : NetworkStatus()
}