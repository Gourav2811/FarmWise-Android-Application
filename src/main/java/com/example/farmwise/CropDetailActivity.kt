package com.example.farmwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmwise.ui.theme.FarmWiseTheme
@OptIn(ExperimentalMaterial3Api::class)

class CropDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cropName = intent.getStringExtra("cropName")
        val cropDescription = intent.getStringExtra("cropDescription")
        val cropImageRes = intent.getIntExtra("cropImageRes", -1)


        setContent {
            FarmWiseTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = cropName ?: "Crop Detail")
                            }
                        )
                    }
                ) { paddingValues ->
                    LazyColumn(
                        contentPadding = paddingValues,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        item {
                            cropImageRes.takeIf { it != -1 }?.let { imageRes ->
                                Image(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = cropName,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(250.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                )
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Text(
                                text = cropName ?: "Apple",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = cropDescription ?: "No description available.",
                                fontSize = 16.sp,
                                lineHeight = 22.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
