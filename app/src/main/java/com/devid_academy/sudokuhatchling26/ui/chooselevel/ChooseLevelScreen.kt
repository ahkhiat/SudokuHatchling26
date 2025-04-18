package com.devid_academy.sudokuhatchling26.ui.chooselevel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.ui.navigation.Screen

@Composable
fun ChooseLevelScreen() {
    ChooseLevelContent()
}

@Composable
fun ChooseLevelContent() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.chooselevel_yellow),
            contentDescription = "choose level background",
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.chooselevel_title),
                contentDescription = "choose level title",
                modifier = Modifier
                    .padding(top = 80.dp)
                    .size(width = 300.dp, height = 50.dp)

            )
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = R.drawable.chooselevel_level1),
                contentDescription = "difficulty choice",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(120.dp))
            Image(
                painter = painterResource(id = R.drawable.chooselevel_button),
                contentDescription = "play button",
                modifier = Modifier
                    .size(width = 300.dp, height = 80.dp)
            )
        }


    }




}

@Preview(showBackground = true)
@Composable
fun ChooseLevelPreview() {
    ChooseLevelContent()
}