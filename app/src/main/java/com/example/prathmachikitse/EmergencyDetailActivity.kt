package com.example.prathmachikitse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.speech.tts.TextToSpeech
import androidx.compose.material3.Button
import androidx.compose.runtime.remember
import java.util.Locale
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
class EmergencyDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val emergencyName = intent.getStringExtra("title") ?: ""

        setContent {
            EmergencyDetailScreen(emergencyName)
        }
    }
}

@Composable
fun EmergencyDetailScreen(title: String) {
    val context = LocalContext.current
    var isKannada by remember {
        mutableStateOf(false)
    }
    var isSpeaking by remember {
        mutableStateOf(false)
    }
    val steps = when (title) {
        "Snake Bite" -> if (isKannada) {
            listOf(
                "ರೋಗಿಯನ್ನು ಶಾಂತವಾಗಿರಿಸಿ",
                "ಗಾಯಗೊಂಡ ಭಾಗವನ್ನು ಅಚಲವಾಗಿರಿಸಿ",
                "ತಕ್ಷಣ ಆಂಬುಲೆನ್ಸ್ ಕರೆ ಮಾಡಿ",
                "ತಕ್ಷಣ ಆಸ್ಪತ್ರೆಗೆ ಕರೆದೊಯ್ಯಿರಿ"
            )
        } else {
            listOf(
                "Keep patient calm",
                "Keep affected area still",
                "Call ambulance immediately",
                "Take patient to hospital quickly"
            )
        }

        "Burn" -> if (isKannada) {
            listOf(
                "ಬೆಂದ ಭಾಗಕ್ಕೆ ತಣ್ಣೀರು ಹಾಕಿ",
                "ಐಸ್ ಬಳಸಬೇಡಿ",
                "ಸ್ವಚ್ಛವಾದ ಬಟ್ಟೆಯಿಂದ ಮುಚ್ಚಿ",
                "ವೈದ್ಯಕೀಯ ಸಹಾಯ ಪಡೆಯಿರಿ"
            )
        } else {
            listOf(
                "Cool burn with water",
                "Do not apply ice",
                "Cover with clean cloth",
                "Seek medical help"
            )
        }

        "CPR" -> if (isKannada) {
            listOf(
                "ಉಸಿರಾಟ ಪರಿಶೀಲಿಸಿ",
                "ತುರ್ತು ಸೇವೆಗೆ ಕರೆ ಮಾಡಿ",
                "ಛಾತಿ ಒತ್ತುವಿಕೆ ಪ್ರಾರಂಭಿಸಿ",
                "ಸಹಾಯ ಬರುವವರೆಗೆ ಮುಂದುವರಿಸಿ"
            )
        } else {
            listOf(
                "Check breathing",
                "Call emergency services",
                "Start chest compressions",
                "Continue until help arrives"
            )
        }

        "Choking" -> if (isKannada) {
            listOf(
                "ರೋಗಿ ಮಾತನಾಡಬಹುದೇ ಪರಿಶೀಲಿಸಿ",
                "5 ಬಾರಿ ಬೆನ್ನಿಗೆ ತಟ್ಟಿ",
                "ಹೊಟ್ಟೆ ಒತ್ತುವಿಕೆ ನೀಡಿ",
                "ತುರ್ತು ಸಹಾಯಕ್ಕೆ ಕರೆ ಮಾಡಿ"
            )
        } else {
            listOf(
                "Ask if patient can speak",
                "Give 5 back blows",
                "Give abdominal thrusts",
                "Call emergency help"
            )
        }

        "Bleeding" -> if (isKannada) {
            listOf(
                "ಗಾಯದ ಮೇಲೆ ಒತ್ತಡ ನೀಡಿ",
                "ಸ್ವಚ್ಛವಾದ ಬಟ್ಟೆ ಬಳಸಿ",
                "ಗಾಯಗೊಂಡ ಭಾಗವನ್ನು ಮೇಲಕ್ಕೆತ್ತಿ",
                "ವೈದ್ಯಕೀಯ ಸಹಾಯ ಪಡೆಯಿರಿ"
            )
        } else {
            listOf(
                "Apply direct pressure",
                "Use clean cloth",
                "Raise injured part",
                "Seek medical help"
            )
        }

        "Poisoning" -> if (isKannada) {
            listOf(
                "ಬಲವಂತವಾಗಿ ವಾಂತಿ ಮಾಡಿಸಬೇಡಿ",
                "ವಿಷ ಸಹಾಯವಾಣಿ ಕರೆ ಮಾಡಿ",
                "ವಿಷದ ಡಬ್ಬಿಯನ್ನು ಸುರಕ್ಷಿತವಾಗಿಡಿ",
                "ತಕ್ಷಣ ಆಸ್ಪತ್ರೆಗೆ ಕರೆದೊಯ್ಯಿರಿ"
            )
        } else {
            listOf(
                "Do not force vomiting",
                "Call poison helpline",
                "Keep poison container safely",
                "Take patient to hospital"
            )
        }

        "Dog Bite" -> if (isKannada) {
            listOf(
                "ಗಾಯವನ್ನು ಸಾಬೂನಿನಿಂದ ತೊಳೆಯಿರಿ",
                "ಅಂಟಿಸೆಪ್ಟಿಕ್ ಬಳಸಿ",
                "ಸ್ವಚ್ಛವಾದ ಬಟ್ಟೆಯಿಂದ ಮುಚ್ಚಿ",
                "ತಕ್ಷಣ ಆಸ್ಪತ್ರೆಗೆ ಹೋಗಿ"
            )
        } else {
            listOf(
                "Wash wound with soap",
                "Apply antiseptic",
                "Cover with clean cloth",
                "Visit hospital quickly"
            )
        }

        "Drowning" -> if (isKannada) {
            listOf(
                "ವ್ಯಕ್ತಿಯನ್ನು ನೀರಿನಿಂದ ಹೊರತೆಗೆಡಿ",
                "ಉಸಿರಾಟ ಪರಿಶೀಲಿಸಿ",
                "ಅಗತ್ಯವಿದ್ದರೆ CPR ಪ್ರಾರಂಭಿಸಿ",
                "ಆಂಬುಲೆನ್ಸ್ ಕರೆ ಮಾಡಿ"
            )
        } else {
            listOf(
                "Remove person from water",
                "Check breathing",
                "Start CPR if needed",
                "Call ambulance"
            )
        }

        "Asthma Attack" -> if (isKannada) {
            listOf(
                "ರೋಗಿಯನ್ನು ನೇರವಾಗಿ ಕುಳ್ಳಿರಿಸಿ",
                "ಇನ್ಹೇಲರ್ ಬಳಸಿ",
                "ರೋಗಿಯನ್ನು ಶಾಂತವಾಗಿರಿಸಿ",
                "ವೈದ್ಯಕೀಯ ಸಹಾಯ ಪಡೆಯಿರಿ"
            )
        } else {
            listOf(
                "Help patient sit upright",
                "Use inhaler",
                "Keep patient calm",
                "Seek medical help"
            )
        }

        "Seizure" -> if (isKannada) {
            listOf(
                "ಅಪಾಯಕಾರಿ ವಸ್ತುಗಳನ್ನು ದೂರವಿಡಿ",
                "ರೋಗಿಯನ್ನು ಬಲವಾಗಿ ಹಿಡಿಯಬೇಡಿ",
                "ರೋಗಿಯನ್ನು ಬದಿಗೆ ತಿರುಗಿಸಿ",
                "ಸೀಜರ್ ಮುಂದುವರಿದರೆ ವೈದ್ಯರನ್ನು ಕರೆ ಮಾಡಿ"
            )
        } else {
            listOf(
                "Keep dangerous objects away",
                "Do not hold patient tightly",
                "Turn patient sideways",
                "Call doctor if seizure continues"
            )
        }

        "Heat Stroke" -> if (isKannada) {
            listOf(
                "ತಂಪಾದ ಸ್ಥಳಕ್ಕೆ ಕರೆದೊಯ್ಯಿರಿ",
                "ನೀರನ್ನು ನಿಧಾನವಾಗಿ ಕೊಡಿ",
                "ಒದ್ದೆಯಾದ ಬಟ್ಟೆ ಬಳಸಿ",
                "ವೈದ್ಯಕೀಯ ಸಹಾಯ ಪಡೆಯಿರಿ"
            )
        } else {
            listOf(
                "Move patient to cool place",
                "Give water slowly",
                "Use wet cloth",
                "Seek medical help"
            )
        }

        "Fever" -> if (isKannada) {
            listOf(
                "ಹೆಚ್ಚು ದ್ರವಪದಾರ್ಥ ನೀಡಿ",
                "ವಿಶ್ರಾಂತಿ ಕೊಡಿಸಿ",
                "ತಾಪಮಾನ ಪರಿಶೀಲಿಸಿ",
                "ತೀವ್ರವಾಗಿದ್ದರೆ ವೈದ್ಯರನ್ನು ಭೇಟಿ ಮಾಡಿ"
            )
        } else {
            listOf(
                "Give fluids",
                "Allow rest",
                "Monitor temperature",
                "Visit doctor if severe"
            )
        }

        "Eye Injury" -> if (isKannada) {
            listOf(
                "ಕಣ್ಣನ್ನು ಒರೆಸಬೇಡಿ",
                "ಸ್ವಚ್ಛ ನೀರಿನಿಂದ ತೊಳೆಯಿರಿ",
                "ಕಣ್ಣನ್ನು ಸಡಿಲವಾಗಿ ಮುಚ್ಚಿ",
                "ಆಸ್ಪತ್ರೆಗೆ ಭೇಟಿ ನೀಡಿ"
            )
        } else {
            listOf(
                "Do not rub eye",
                "Wash gently with clean water",
                "Cover eye lightly",
                "Visit hospital"
            )
        }

        "Head Injury" -> if (isKannada) {
            listOf(
                "ರೋಗಿಯನ್ನು ಚಲಿಸದಂತೆ ಇರಿಸಿ",
                "ತಣ್ಣನೆಯ ಬಟ್ಟೆ ಬಳಸಿ",
                "ಅಚೇತನತೆ ಪರಿಶೀಲಿಸಿ",
                "ತುರ್ತು ಸಹಾಯ ಪಡೆಯಿರಿ"
            )
        } else {
            listOf(
                "Keep patient still",
                "Apply cold pack",
                "Watch for unconsciousness",
                "Seek emergency help"
            )
        }

        "Nose Bleeding" -> if (isKannada) {
            listOf(
                "ತಲೆಯನ್ನು ಸ್ವಲ್ಪ ಮುಂದೆ ತಗ್ಗಿಸಿ",
                "ಮೂಗನ್ನು ಸೌಮ್ಯವಾಗಿ ಒತ್ತಿ",
                "ತಣ್ಣನೆಯ ಬಟ್ಟೆ ಬಳಸಿ",
                "ತಲೆಯನ್ನು ಹಿಂದೆ ತಗ್ಗಿಸಬೇಡಿ"
            )
        } else {
            listOf(
                "Lean slightly forward",
                "Pinch nose gently",
                "Apply cold cloth",
                "Do not tilt head backward"
            )
        }

        "Fainting" -> if (isKannada) {
            listOf(
                "ರೋಗಿಯನ್ನು ನೆಲದಲ್ಲಿ ಮಲಗಿಸಿ",
                "ಕಾಲುಗಳನ್ನು ಸ್ವಲ್ಪ ಮೇಲಕ್ಕೆತ್ತಿ",
                "ಬಿಗಿಯಾದ ಬಟ್ಟೆ ಸಡಿಲಿಸಿ",
                "ತಾಜಾ ಗಾಳಿ ನೀಡಿ"
            )
        } else {
            listOf(
                "Lay patient flat",
                "Raise legs slightly",
                "Loosen tight clothes",
                "Give fresh air"
            )
        }

        "Sprain" -> if (isKannada) {
            listOf(
                "ಗಾಯಗೊಂಡ ಭಾಗಕ್ಕೆ ವಿಶ್ರಾಂತಿ ನೀಡಿ",
                "ಐಸ್ ಪ್ಯಾಕ್ ಬಳಸಿ",
                "ಪಟ್ಟಿಯಿಂದ ಕಟ್ಟಿ",
                "ಭಾಗವನ್ನು ಮೇಲಕ್ಕೆತ್ತಿ"
            )
        } else {
            listOf(
                "Rest injured area",
                "Apply ice pack",
                "Compress with bandage",
                "Keep limb elevated"
            )
        }

        else -> listOf(
            "Stay calm",
            "Call ambulance",
            "Give first aid carefully"
        )
    }

    val textToSpeech = remember {
        TextToSpeech(context, null)
    }

    textToSpeech.language =
        if (isKannada)
            Locale("kn", "IN")
        else
            Locale.US

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text =
                if (isKannada) {
                    when(title) {
                        "Snake Bite" -> "ಹಾವು ಕಚ್ಚು"
                        "Burn" -> "ಬೆಂಕಿ ಗಾಯ"
                        "CPR" -> "ಸಿಪಿಆರ್"
                        "Choking" -> "ಉಸಿರುಗಟ್ಟಿಕೆ"
                        "Bleeding" -> "ರಕ್ತಸ್ರಾವ"
                        "Poisoning" -> "ವಿಷ ಸೇವನೆ"
                        "Dog Bite" -> "ನಾಯಿ ಕಚ್ಚು"
                        "Drowning" -> "ಮುಳುಗು"
                        "Asthma Attack" -> "ಆಸ್ತಮಾ ದಾಳಿ"
                        "Seizure" -> "ಆಕಸ್ಮಿಕ ಕುಂಪಣೆ"
                        "Heat Stroke" -> "ಬಿಸಿಗಾಳಿ ಅಸ್ವಸ್ಥತೆ"
                        "Fever" -> "ಜ್ವರ"
                        "Eye Injury" -> "ಕಣ್ಣಿನ ಗಾಯ"
                        "Head Injury" -> "ತಲೆಯ ಗಾಯ"
                        "Nose Bleeding" -> "ಮೂಗಿನ ರಕ್ತಸ್ರಾವ"
                        "Fainting" -> "ಬೆವರುವುದು"
                        "Sprain" -> "ಮುರಿ"
                        "Heart Attack" -> "ಹೃದಯಾಘಾತ"
                        "Fracture" -> "ಎಲುಬು ಮುರಿತ"
                        "Electric Shock" -> "ವಿದ್ಯುತ್ ಶಾಕ್"
                        else -> title
                    }
                } else {
                    title
                },

            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = {
                isKannada = !isKannada
            }
        ) {
            Text(
                if (isKannada)
                    "English"
                else
                    "ಕನ್ನಡ"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(
                id = when (title) {
                    "Snake Bite" -> R.drawable.snake_bite
                    "Burn" -> R.drawable.hand_burn
                    "CPR" -> R.drawable.cpr
                    "Fracture" -> R.drawable.fracture
                    "Heart Attack" -> R.drawable.heart_attack
                    "Electric Shock" -> R.drawable.electric_shock
                    else -> R.drawable.snake_bite
                }
            ),
            contentDescription = "Snake Bite Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Text(
            text =
                if (isKannada)
                    "ತುರ್ತು ಪ್ರಥಮ ಚಿಕಿತ್ಸೆ ಕ್ರಮಗಳು"
                else
                    "Emergency First Aid Steps",

            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {

                if (isSpeaking) {

                    textToSpeech.stop()
                    isSpeaking = false

                } else {

                    val fullText = steps.joinToString(". ")

                    textToSpeech.speak(
                        fullText,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        null
                    )

                    isSpeaking = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text =
                    if (isSpeaking)
                        if (isKannada)
                            "ಧ್ವನಿ ನಿಲ್ಲಿಸಿ"
                        else
                            "Stop Audio"
                    else
                        if (isKannada)
                            "ಧ್ವನಿ ಚಾಲನೆ"
                        else
                            "Play Audio"
            )
        }

        steps.forEachIndexed { index, step ->

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "${index + 1}. $step",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:108")
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (isKannada)
                    "ತುರ್ತು 108 ಕರೆ"
                else
                    "Call Emergency 108"
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text =
                if (isKannada)
                    "ಹತ್ತಿರದ ಆಸ್ಪತ್ರೆಗಳು"
                else
                    "Nearby Emergency Centers",

            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text =
                if (isKannada)
                    "🏥 ಜಿಲ್ಲಾ ಆಸ್ಪತ್ರೆ - 2 ಕಿಮೀ"
                else
                    "🏥 District Hospital - 2 km",

            fontSize = 18.sp
        )

        Text(
            text =
                if (isKannada)
                    "🏥 KLE ಆಸ್ಪತ್ರೆ - 4 ಕಿಮೀ"
                else
                    "🏥 KLE Hospital - 4 km",

            fontSize = 18.sp
        )

        Text(
            text =
                if (isKannada)
                    "🏥 ಪ್ರಾಥಮಿಕ ಆರೋಗ್ಯ ಕೇಂದ್ರ - 1 ಕಿಮೀ"
                else
                    "🏥 Primary Health Center - 1 km",

            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=hospital")
                )
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (isKannada)
                    "ನಕ್ಷೆಯಲ್ಲಿ ಆಸ್ಪತ್ರೆ ಹುಡುಕಿ"
                else
                    "Open Hospitals in Map"
            )
        }



        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text =
                if (isKannada)
                    "ಮಾಡಬೇಕು"
                else
                    "DO'S",

            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            if (isKannada)
                "✔ ರೋಗಿಯನ್ನು ಶಾಂತವಾಗಿರಿಸಿ"
            else
                "✔ Keep patient calm"
        )

        Text(
            if (isKannada)
                "✔ ತಕ್ಷಣ ವೈದ್ಯಕೀಯ ಸಹಾಯ ಪಡೆಯಿರಿ"
            else
                "✔ Get medical help quickly"
        )

        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text =
                if (isKannada)
                    "ಮಾಡಬಾರದು"
                else
                    "DON'TS",

            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text =
                if (isKannada)
                    "✘ ಗಾಬರಿಯಾಗಬೇಡಿ"
                else
                    "✘ Do not panic",

            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(10.dp))


        Text(
            text =
                if (isKannada)
                    "✘ ತಪ್ಪು ಮನೆಮದ್ದು ಬಳಸಬೇಡಿ"
                else
                    "✘ Do not use wrong home remedies",
            fontSize = 18.sp
        )
    }
}
