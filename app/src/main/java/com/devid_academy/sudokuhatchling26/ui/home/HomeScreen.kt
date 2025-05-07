package com.devid_academy.sudokuhatchling26.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.ScaffoldComposable

@Composable
fun HomeScreen() {
    HomeContent()
}

@Composable
private fun HomeContent() {
    ScaffoldComposable(
        modifier = Modifier
            .fillMaxSize(),
        content = {
            Box(
                modifier = Modifier
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chooselevel_yellow),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    HomeContent()
}