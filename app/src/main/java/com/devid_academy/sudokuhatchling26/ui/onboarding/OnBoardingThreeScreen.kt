package com.devid_academy.sudokuhatchling26.ui.onboarding

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
fun OnBoardingThreeScreen(
    navController: NavController
) {
    OnBoardingThreeContent(
        onNextClicked = {
            navController.navigate(Screen.WelcomePage.route)
        },
        onBackClicked = {
            navController.navigate(Screen.OnBoardingTwo.route) {
                popUpTo(Screen.OnBoardingTwo.route) {
                    inclusive = true
                }
            }
        }
    )
}

@Composable
fun OnBoardingThreeContent(
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Image(
                painter = painterResource(R.drawable.onboarding3_title),
                contentDescription ="on boarding page 3 title",
            )
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(R.drawable.onboarding3_feature),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(R.drawable.onboarding3_text),
                contentDescription = null,
            )
//            Spacer(modifier = Modifier.height(40.dp))
//            Image(
//                painter = painterResource(R.drawable.onboarding1_pagination),
//                contentDescription = null,
//            )
//            Spacer(modifier = Modifier.weight(1f))
        }
        Image(
            painter = painterResource(R.drawable.onboarding3_pagination),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 300.dp)
        )
        Image(
            painter = painterResource(R.drawable.onboarding3_yellow),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = onNextClicked,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            ) {
                Image(
                    painter = painterResource(R.drawable.onboarding1_button),
                    contentDescription = "next button"
                )
            }

            Button(
                onClick = onBackClicked,
                modifier = Modifier
                    .padding(bottom = 10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            ) {
                Image(
                    painter = painterResource(R.drawable.onboarding2_button_white),
                    contentDescription = "next button"
                )
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun OnBoardingThreePreview() {
    OnBoardingThreeContent(
        onNextClicked = {},
        onBackClicked = {}
    )
}
