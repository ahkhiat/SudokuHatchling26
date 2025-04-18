package com.devid_academy.sudokuhatchling26.ui.welcomepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devid_academy.sudokuhatchling26.R
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.devid_academy.sudokuhatchling26.ui.navigation.Screen


@Composable
fun WelcomePageScreen(
    navController: NavController
) {
    WelcomePageContent(
        onStartClicked = {
            navController.navigate(Screen.OnBoardingTwo.route)
        }
    )
}

@Composable
fun WelcomePageContent(
    onStartClicked: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Image(
                painter = painterResource(R.drawable.welcome_title),
                contentDescription ="on boarding page 1 title",
            )
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(R.drawable.welcome_grid),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }

        Image(
            painter = painterResource(R.drawable.welcome_yellow),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
        Image(
            painter = painterResource(R.drawable.welcome_owl),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 250.dp)
        )
        Image(
            painter = painterResource(R.drawable.welcome_text),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 170.dp)
        )

        Button(
            onClick = onStartClicked ,
            modifier = Modifier
                .padding(bottom = 40.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        ) {
            Image(
                painter = painterResource(R.drawable.welcome_button),
                contentDescription = "next button"
            )
        }



    }

}

@Preview(showBackground = true)
@Composable
fun WelcomePagePreview() {
    WelcomePageContent(
        onStartClicked = {}
    )
}