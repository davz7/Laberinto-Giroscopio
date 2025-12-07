package mx.edu.laberinto_giroscopio

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.activity.compose.rememberLauncherForActivityResult
import mx.edu.laberinto_giroscopio.ui.nav.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RequestVibrationPermission()
            AppNavigation()
        }
    }
}

@Composable
fun RequestVibrationPermission() {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
        }
    )

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.VIBRATE)
    }
}
