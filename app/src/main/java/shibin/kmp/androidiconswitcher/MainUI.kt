package shibin.kmp.androidiconswitcher

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.shibin.iconswitcher.model.DashboardState
import io.github.shibin.iconswitcher.model.IconInfo

@Composable
fun MainUI(
    innerPadding: PaddingValues,
    icons: List<IconInfo>,
    state: DashboardState
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            DashboardHeader()
        }

        item {
            CurrentIconCard(state)
        }

        item {
            Text(
                text = "Available Icons",
            )
        }

        item {
            AvailableIconsGrid(icons = icons)
        }

        item {
            FirestoreCard(state)
        }
    }
}


@Composable
fun DashboardHeader() {

    Column {

        Text(
            text = "Android Icon Switcher SDK",
        )

        Text(
            text = "Live launcher icon switching powered by Firestore",
        )
    }
}

@Composable
fun CurrentIconCard(state: DashboardState) {

    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Current Active Icon", fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            state.currentIcon?.let { icon ->
                Image(
                    painter = painterResource(icon.previewIcon),
                    contentDescription = icon.name
                )
                Spacer(Modifier.height(12.dp))
                Text(icon.name)
                Text(icon.alias)
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun FirestoreCard(state: DashboardState) {

    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Firestore", fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Text("Current Value")
            Text(state.currentIcon?.name ?: "")
            Spacer(Modifier.height(16.dp))
            Text("Status")
            Text("🟢 Connected")
        }
    }
}

@Composable
fun AvailableIconsGrid(
    icons: List<IconInfo>
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(110.dp),
        modifier = Modifier.height(340.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(icons) { icon ->

            ElevatedCard {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(icon.previewIcon),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(icon.name)
                }
            }
        }
    }
}
