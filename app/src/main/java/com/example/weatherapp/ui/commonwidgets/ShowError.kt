package com.example.weatherapp.ui.commonwidgets

import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ShowError(error: String) {
    Snackbar {
        Text(text = error)
    }
}