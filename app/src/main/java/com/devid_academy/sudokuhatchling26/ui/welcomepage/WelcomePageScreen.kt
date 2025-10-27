package com.devid_academy.sudokuhatchling26.ui.welcomepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.devid_academy.sudokuhatchling26.ui.bootstrap.Screen
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily


@Composable
fun WelcomePageScreen(
    navController: NavController
) {
    WelcomePageContent(
        onStartClicked = {
            navController.navigate(Screen.Register.route)
        }
    )
}


@Composable
fun WelcomePageContent(
    onStartClicked: () -> Unit
) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = context.getString(R.string.welcome_title),
            fontSize = 34.sp,
            fontFamily = SummaryNotesFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
        )
        Image(
            painter = painterResource(R.drawable.splash_title),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp)
        )
        Image(
            painter = painterResource(R.drawable.welcome_yellow),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .offset(y = 20.dp)
        )
        Image(
            painter = painterResource(R.drawable.welcome_grid),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
        )

        Image(
            painter = painterResource(R.drawable.welcome_owl),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(280.dp)
                .padding(bottom = 220.dp)
        )
        Text(
            text = context.getString(R.string.welcome_text_line1),
            fontSize = 24.sp,
            fontFamily = SummaryNotesFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 180.dp)
        )
//        Text(
//            text = context.getString(R.string.welcome_text_line2),
//            fontSize = 24.sp,
//            fontFamily = SummaryNotesFamily,
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(bottom = 150.dp)
//        )
        Button(
            onClick = onStartClicked,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
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
                    text = context.getString(R.string.button_start),
                    fontSize = 32.sp,
                    fontFamily = SummaryNotesFamily,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
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