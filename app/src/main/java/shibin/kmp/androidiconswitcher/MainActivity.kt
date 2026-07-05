package shibin.kmp.androidiconswitcher

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.firebase.firestore.FirebaseFirestore
import io.github.shibin.iconswitcher.IconSwitcher
import io.github.shibin.iconswitcher.listener.IconSwitcherListener
import io.github.shibin.iconswitcher.model.IconConfig
import io.github.shibin.iconswitcher.model.IconInfo
import shibin.kmp.androidiconswitcher.data.DemoData
import shibin.kmp.androidiconswitcher.ui.theme.AndroidIconSwitcherTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val config = DemoData.iconConfig

        try {

            IconSwitcher.builder(applicationContext).config(config).provider(
                FirestoreProvider(
                    FirebaseFirestore.getInstance()
                )
            ).listener(object : IconSwitcherListener {

                override fun onIconChanged(icon: IconInfo) {
                    Log.d("Sample", "Changed to ${icon.name}")
                }

                override fun onAlreadyApplied(icon: IconInfo) {
                    Log.d("Sample", "Already using ${icon.name}")
                }

                override fun onInvalidIcon(requested: String) {
                    Log.d("Sample", "Invalid icon: $requested")
                }

                override fun onSwitchFailed(requested: String) {
                    Log.d("Sample", "Failed to switch: $requested")
                }

                override fun onError(error: Throwable) {
                    Log.e("Sample", "Error", error)
                }
            }).enableLogging(true).enableCaching(true).build()
            Log.d("ICON", "Initialization successful")
        } catch (e: Exception) {
            Log.e("ICON", "Initialization failed", e)
        }

        setContent {
            val state by IconSwitcher.dashboardState.collectAsStateWithLifecycle()

            AndroidIconSwitcherTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainUI(
                        innerPadding = innerPadding, icons = config.icons, state = state
                    )
                }
            }
        }
    }
}


