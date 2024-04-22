@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.randomdots

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.randomdots.ui.theme.RandomDotsTheme


class ScoresActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences( "myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        setContent { RandomDotsTheme {ScoresScreen(sharedPref)  } }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoresScreen(sharedPref: SharedPreferences) {
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
            var strScore = sharedPref.all.values.toList()
            var i = 0
            val finalScore = IntArray(30)
            strScore.forEach {
                finalScore[i] = strScore[i] as Int
                i++
            }
            finalScore.sort(0)
            finalScore.reverse()
            Box {
            }
            Column(modifier = Modifier.offset(10.dp, 70.dp))
            {
                Text(text = "Score 1:" + finalScore[0] , style = MaterialTheme.typography.bodyLarge)
            }
            Column(modifier = Modifier.offset(10.dp, 100.dp))
            {
                Text(text = "Score 2:" + finalScore[1], style = MaterialTheme.typography.bodyLarge)
            }
            Column(modifier = Modifier.offset(10.dp, 130.dp))
            {
                Text(text = "Score 3:" + finalScore[2], style = MaterialTheme.typography.bodyLarge)
            }
            Column(modifier = Modifier.offset(10.dp, 160.dp))
            {
                Text(text = "Score 4:" + finalScore[3], style = MaterialTheme.typography.bodyLarge)
            }
            Column(modifier = Modifier.offset(10.dp, 190.dp))
            {
                Text(text = "Score 5:" + finalScore[4], style = MaterialTheme.typography.bodyLarge)
            }
            Column(modifier = Modifier.offset(10.dp, 220.dp))
            {
                Text(text = "Score 6:" + finalScore[5], style = MaterialTheme.typography.bodyLarge)
            }
            Column(modifier = Modifier.offset(10.dp, 250.dp))
            {
                Text(text = "Score 7:" + finalScore[6], style = MaterialTheme.typography.bodyLarge)
            }
            Column(modifier = Modifier.offset(10.dp, 280.dp))
            {
                Text(text = "Score 8:" + finalScore[7], style = MaterialTheme.typography.bodyLarge)
            }
            Column(modifier = Modifier.offset(10.dp, 310.dp))
            {
                Text(text = "Score 9:" + finalScore[8], style = MaterialTheme.typography.bodyLarge)
            }
            Column(modifier = Modifier.offset(10.dp, 340.dp))
            {
                Text(text = "Score 10:" + finalScore[9], style = MaterialTheme.typography.bodyLarge)
            }
//
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
        }
        }
    }

@Preview(showBackground = true)
@Composable
fun scorePreview() {
    RandomDotsTheme {
//                ScoresScreen(highScore = 1)
    }
}

