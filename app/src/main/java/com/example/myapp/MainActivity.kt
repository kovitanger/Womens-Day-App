package com.example.myapp

import android.os.Bundle
import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            WomenDayApp()
        }
    }
}

@Composable
fun WomenDayApp() {
    var name by remember { mutableStateOf("") }
    var greeting by remember { mutableStateOf("") }
    var isDarkTheme by remember { mutableStateOf(false) }
    val backgroundColor = if (isDarkTheme) Color(0xFF121212) else Color(0xFFFFC0CB)
    val buttonColor = if (isDarkTheme) Color(0xFFBB86FC) else Color(0xFFE91E63)
    val textColor = if (isDarkTheme) Color.White else Color.Black

    val greetingsList = listOf(
        "С 8 марта, %s! Пусть счастье и любовь наполняют ваш день!",
        "Поздравляем, %s, с праздником весны! Пусть каждый день будет радостным!",
        "С 8 марта, %s! Пусть мечты сбываются, а улыбки не сходят с лица!",
        "С Международным женским днем, %s! Будьте счастливы и любимы!",
        "С праздником, %s! Пусть мир будет полон радости и любви!",
        "С 8 марта, %s! Желаем солнечного настроения и весеннего вдохновения!",
        "С Международным женским днем, %s! Пусть каждый день приносит радость и счастье!",
        "С 8 марта, %s! Пусть каждый момент будет наполнен любовью и нежностью!",
        "Поздравляем, %s, с праздником! Пусть все мечты сбываются!",
        "С 8 марта, %s! Пусть в вашей жизни будет больше теплых моментов и радостных событий!"
    )

    val shownGreetings = remember { mutableStateListOf<String>() }

    fun getNextGreeting(): String {
        if (shownGreetings.size == greetingsList.size) {
            shownGreetings.clear()
        }
        val notShown = greetingsList.filter { it !in shownGreetings }
        val next = notShown.random()
        shownGreetings.add(next)
        return next
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🌷 Поздравление с 8 марта 🌷",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Введите имя") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { greeting = "С 8 марта, $name! Желаем счастья и любви!" },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text("✨ Показать поздравление ✨")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { greeting = getNextGreeting().format(name) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text("🎉 Случайное поздравление 🎉")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { isDarkTheme = !isDarkTheme },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(if (isDarkTheme) "☀️ Переключить тему ☀️" else "🌙 Переключить тему 🌙")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (greeting.isNotEmpty()) {
            Text(
                text = greeting,
                fontSize = 18.sp,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWomenDayApp() {
    WomenDayApp()
}
