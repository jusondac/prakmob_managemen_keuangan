package id.ac.unpas.managemen_keuangan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import id.ac.unpas.managemen_keuangan.ui.screens.MainScreen
import id.ac.unpas.managemen_keuangan.ui.theme.ManagemenTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ManagemenTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }
}