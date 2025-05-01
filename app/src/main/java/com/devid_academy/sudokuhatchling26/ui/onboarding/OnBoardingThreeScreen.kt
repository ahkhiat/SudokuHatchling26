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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devid_academy.sudokuhatchling26.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devid_academy.sudokuhatchling26.ui.bootstrap.Screen
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily


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
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = context.getString(R.string.onboarding3_title),
                fontSize = 28.sp,
                fontFamily = SummaryNotesFamily,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(R.drawable.onboarding3_feature),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = context.getString(R.string.onboarding3_text_line1),
                fontSize = 24.sp,
                fontFamily = SummaryNotesFamily,
                textAlign = TextAlign.Center
            )
            Text(
                text = context.getString(R.string.onboarding3_text_line2),
                fontSize = 24.sp,
                fontFamily = SummaryNotesFamily,
                textAlign = TextAlign.Center
            )
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
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.button_red),
                        contentDescription = "next button",
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = context.getString(R.string.button_next),
                        fontSize = 32.sp,
                        fontFamily = SummaryNotesFamily,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
            Button(
                onClick = onBackClicked,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.button_white),
                        contentDescription = "back button",
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = context.getString(R.string.button_back),
                        fontSize = 32.sp,
                        fontFamily = SummaryNotesFamily,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }
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
