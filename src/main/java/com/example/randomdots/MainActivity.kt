@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.randomdots

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomdots.ui.theme.RandomDotsTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        setContent {
            RandomDotsTheme() {
                RandomDotsApp(sharedPref)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomDotsApp(sharedPref: SharedPreferences) {
    var roundScore = 0
    var currentStep by remember { mutableStateOf(1) }
    var xOffset = 1
    var yOffset = 1
    var variable_two = 30L
    var variable_one = variable_two
    var scoreVar_two = 0
    var scoreVar_one = scoreVar_two
    val mContext = LocalContext.current
    var mDisplayMenu by remember { mutableStateOf(false) }
    val highScores = ArrayList<Int>(10)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Random Dots", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { mDisplayMenu = !mDisplayMenu }) {
                        Icon(Icons.Default.Menu, "")
                    }
                    MaterialTheme(
                        colorScheme = MaterialTheme.colorScheme.copy(surface = Color.Transparent),
                        shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(20))
                    )
                    {
                        val context = LocalContext.current
                        DropdownMenu(
                            expanded = mDisplayMenu,
                            onDismissRequest = { mDisplayMenu = false })
                        {
                            DropdownMenuItem(
                                { Text(text = "High Scores") },
                                onClick = {
                                    val intent = Intent(context, ScoresActivity::class.java)
                                    context.startActivity(intent)
                                })
                            DropdownMenuItem(
                                { Text(text = "Logout") },
                                onClick = {
                                    Toast.makeText(mContext, "Logout", Toast.LENGTH_SHORT).show()
                                })
                        }
                    }
                },
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
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        )
        {
            when (currentStep) {
                1 -> {
                    PlayArea(
                        onImageClick = { currentStep = 2 },
                        textLabelResourceId = "Tap blue circle to start game",
                        Modifier.offset(1.dp, 35.dp)
                    )
                    Score(scoreVal = scoreVar_one)
                }

                2 -> {
                    xOffset = (2..290).random()
                    yOffset = (2..590).random()
                    PlayArea(
                        onImageClick = { currentStep = 3 },
                        textLabelResourceId = "",
                        Modifier.offset(xOffset.dp, yOffset.dp)
                    )
                    timerScreen(variable_one, scoreVar_one, sharedPref)
//                    Score(scoreVal = scoreVar_one)
                }

                3 -> {
                    xOffset = (2..290).random()
                    yOffset = (2..590).random()
                    PlayArea(
                        onImageClick = { currentStep = 2 },
                        textLabelResourceId = "",
                        Modifier.offset(xOffset.dp, yOffset.dp)
                    )
                    timerScreen(variable_one, scoreVar_one, sharedPref)
//                    Score(scoreVal = scoreVar_one)
                }
            }
            variable_one = --variable_two
            scoreVar_one = ++scoreVar_two
        }
    }
}

@Composable
fun PlayArea(
    onImageClick: () -> Unit,
    textLabelResourceId: String,
    modifier: Modifier = Modifier
) {
    val dot = painterResource(R.drawable.bluecircle)
    Box()
    {
        Column(modifier = modifier)
        {
            Button(
                onClick = onImageClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background)
            )
            {
                Image(painter = dot, contentDescription = null)
            }
            Text(
                text = textLabelResourceId,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun timerScreen(
    timerStopValue: Long,
    scoreVal: Int,
    sharedPref: SharedPreferences,
    modifier: Modifier = Modifier

) {
    var timeLeft: Long by remember { mutableStateOf(timerStopValue) }

    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > 0) {
            delay(100L)
            timeLeft--
        }
    }
    Column(
        modifier = Modifier
    )
    {
        Text(
            modifier = Modifier.offset(250.dp, 5.dp),
            text = "Timer: ${timeLeft}",
            style = TextStyle(
                textAlign = TextAlign.Right,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
    val context = LocalContext.current
    if (timeLeft == 0L) {
//        insertScores += scoreVal
        var editor = sharedPref.edit()
        editor.apply {
            putInt("Score$scoreVal", scoreVal)
            apply()
        }
        Toast.makeText(context, "Game Over! Round score:" + scoreVal, Toast.LENGTH_LONG).show()
        Button(
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RectangleShape,
            modifier = Modifier.fillMaxSize(),
//            modifier = Modifier.absolutePadding(100.dp, 210.dp, 100.dp, 210.dp)
        ) {
            Text("Start New Game", color = Color.Black)
        }

    }
    Column(
        modifier = Modifier
    )
    {
        Text(
            modifier = Modifier.offset(5.dp, 5.dp),
            text = "Score: ${scoreVal}",
            style = TextStyle(
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
}

@Composable
fun Score(scoreVal: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
    )
    {
        Text(
            modifier = Modifier.offset(5.dp, 5.dp),
            text = "Score: ${scoreVal}",
            style = TextStyle(
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RandomDotsPreview() {
    RandomDotsTheme {
        val sharedPref: SharedPreferences? = null
        if (sharedPref != null) {
            RandomDotsApp(sharedPref)
        }
    }
}