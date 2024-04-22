@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.randomdots

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.randomdots.ui.theme.RandomDotsTheme


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val density = resources.displayMetrics.density
        val dpHeight = outMetrics.heightPixels / density
        val dpWidth = outMetrics.widthPixels / density

        setContent {
            RandomDotsTheme { loginScreen() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginScreen() {
    var username by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
//    val highScores = ArrayList<Int>(10)
//
//    var insertScore: Int = 23
//    getScore(insertScore, highScores)
//    insertScore = 24
//    getScore(insertScore,highScores)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Random Dots", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
            Box {
            }
            Column(modifier = Modifier.offset())
            {
//                Text(text = stringResource(mTextField), style = MaterialTheme.typography.bodyLarge)
            }
//            TimerScreen()
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .offset(5.dp, 10.dp)
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        )
        {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("username") })
        }
        Surface(
            modifier = Modifier
                .offset(5.dp, 80.dp)
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        )
        {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

        }
        Surface(
            modifier = Modifier
                .offset(1.dp, 150.dp)
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        )
        {
            val context = LocalContext.current
            Button(onClick = {
                if (password == username) {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "Wrong password", Toast.LENGTH_SHORT).show()
                }
            })
            {
                Text("Login")
            }
        }

    }
}

@Composable
fun getScore(insertScore: Int, highScores: ArrayList<Int>, modifier: Modifier = Modifier) {
//    var highScore: SharedPreferences? = null
    highScores += insertScore
}

@Preview(showBackground = true)
@Composable
fun loginPreview() {
    RandomDotsTheme {
        loginScreen(

        )
    }
}

