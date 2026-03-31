package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceScreen()
            }
        }
    }
}

data class ArtWork(
    val imageResourceId: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtSpaceScreen() {
    var currentIndex by remember { mutableStateOf(0) }

    val artWorks = listOf(
        ArtWork(R.drawable.stars, "Yıldızlı Gece", "Vincent van Gogh", "1889"),
        ArtWork(R.drawable.monalisa, "Mona Lisa", "Leonardo da Vinci", "1503"),
        ArtWork(R.drawable.vincentvangogh, "Otoportre", "Vincent van Gogh", "1889")
    )

    val currentArt = artWorks[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
       horizontalAlignment = Alignment.CenterHorizontally //Daha sonrasında eklendiğini belirt.
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            contentAlignment = Alignment.Center //Daha sonrasında eklendiğini belirt.
        ) {
            Surface(
                shadowElevation = 15.dp,
                border = BorderStroke(2.dp, Color.LightGray),
                modifier = Modifier.padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = currentArt.imageResourceId),
                    contentDescription = currentArt.title,
                    modifier = Modifier.padding(20.dp)
                )
            }

            Row(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1f).fillMaxHeight().clickable {
                    currentIndex = when (currentIndex) {
                        0 -> artWorks.size - 1
                        else -> currentIndex - 1
                    }
                })

                Box(modifier = Modifier.weight(1f).fillMaxHeight().clickable {
                    currentIndex = when (currentIndex) {
                        artWorks.size - 1 -> 0
                        else -> currentIndex + 1
                    }
                })
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth() //Daha sonrasında eklendiğini belirt.
                .padding(horizontal = 20.dp),
            color = Color(0xFFECEFF1),
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = currentArt.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "${currentArt.artist} (${currentArt.year})",
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}