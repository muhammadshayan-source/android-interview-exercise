package com.sadapay.live_coding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sadapay.live_coding.ui.theme.LivecodingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LivecodingTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // TODO: Implement your screen here
                }
            }
        }
    }
}
