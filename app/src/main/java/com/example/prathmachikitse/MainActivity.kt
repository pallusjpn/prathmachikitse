package com.example.prathmachikitse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EmergencyHomeScreen()
        }
    }
}

data class EmergencyItem(
    val title: String,
    val image: Int
)

@Composable
fun EmergencyHomeScreen() {
    val context = LocalContext.current
    var searchText by remember {
        mutableStateOf("")
    }


    val emergencyList = listOf(

        EmergencyItem("Snake Bite", R.drawable.snake_bite),
        EmergencyItem("Burn", R.drawable.hand_burn),
        EmergencyItem("CPR", R.drawable.cpr),
        EmergencyItem("Fracture", R.drawable.fracture),
        EmergencyItem("Heart Attack", R.drawable.heart_attack),
        EmergencyItem("Electric Shock", R.drawable.electric_shock),

        EmergencyItem("Choking", R.drawable.choking),
        EmergencyItem("Bleeding", R.drawable.bleeding),
        EmergencyItem("Poisoning", R.drawable.poisoning),
        EmergencyItem("Dog Bite", R.drawable.dog_bite),
        EmergencyItem("Drowning", R.drawable.drowning),
        EmergencyItem("Asthma Attack", R.drawable.asthma_attack),
        EmergencyItem("Seizure", R.drawable.seizure),
        EmergencyItem("Heat Stroke", R.drawable.heat_stroke),
        EmergencyItem("Fever", R.drawable.fever),
        EmergencyItem("Eye Injury", R.drawable.eye_injury),
        EmergencyItem("Head Injury", R.drawable.head_injury),
        EmergencyItem("Nose Bleeding", R.drawable.nose_bleeding),
        EmergencyItem("Fainting", R.drawable.fainting),
        EmergencyItem("Sprain", R.drawable.sprain)

    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {

        Text(
            text = "Pratham-Chikitse",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            label = {
                Text("Search Emergency")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(
                emergencyList.filter {
                    it.title.contains(searchText, ignoreCase = true)
                }
            ) { item ->

                            Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable {

                                    val intent = Intent(
                                        context,
                                        EmergencyDetailActivity::class.java
                                    )

                                    intent.putExtra("title", item.title)

                                    context.startActivity(intent)
                                },
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Image(
                                        painter = painterResource(id = item.image),
                                        contentDescription = item.title,
                                        modifier = Modifier
                                            .size(100.dp)
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        text = "🩺",
                                        fontSize = 28.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = item.title,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                }                }
            }
        }
    }