package com.devid_academy.sudokuhatchling26.ui.onboarding

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
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
import com.devid_academy.sudokuhatchling26.ui.theme.YellowColor


@Composable
fun OnBoardingOneScreen(
    navController: NavController
) {
    OnBoardingOneContent(
        onNextClicked = {
            navController.navigate(Screen.OnBoardingTwo.route)
        }
    )
}

@Composable
fun OnBoardingOneContent(
    onNextClicked: () -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        snapshotFlow { scrollState.maxValue }
            .collect {
                scrollState.scrollTo(it / 2)
            }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = context.getString(R.string.onboarding1_title),
                fontSize = 28.sp,
                fontFamily = SummaryNotesFamily
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .background(YellowColor)
                    .padding(vertical = 20.dp)
                    .horizontalScroll(scrollState)
            ) {
                RowElement(
                    context = context,
                    image = R.drawable.level1,
                    level = R.string.level1,
                    legend = R.string.level1_legend
                )
                RowElement(
                    context = context,
                    image = R.drawable.level2,
                    level = R.string.level2,
                    legend = R.string.level2_legend
                )
                RowElement(
                    context = context,
                    image = R.drawable.level3,
                    level = R.string.level3,
                    legend = R.string.level3_legend
                )
                RowElement(
                    context = context,
                    image = R.drawable.level4,
                    level = R.string.level4,
                    legend = R.string.level4_legend
                )
                RowElement(
                    context = context,
                    image = R.drawable.level5,
                    level = R.string.level5,
                    legend = R.string.level5_legend
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = context.getString(R.string.onboarding1_text_line1),
                fontSize = 24.sp,
                fontFamily = SummaryNotesFamily,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
            )
//            Text(
//                text = context.getString(R.string.onboarding1_text_line2),
//                fontSize = 24.sp,
//                fontFamily = SummaryNotesFamily,
//                textAlign = TextAlign.Center
//            )
//            Text(
//                text = context.getString(R.string.onboarding1_text_line3),
//                fontSize = 24.sp,
//                fontFamily = SummaryNotesFamily,
//                textAlign = TextAlign.Center
//            )
        }
            Image(
                painter = painterResource(R.drawable.onboarding1_pagination),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 300.dp)
            )
            Image(
                painter = painterResource(R.drawable.onboarding1_yellow),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                )
        Button(
            onClick = onNextClicked,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter)
            ,
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

    }

}


@Composable
fun RowElement(
    context: Context,
    image: Int,
    level: Int,
    legend: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 3.dp)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            )
        Text(
            text = context.getString(level),
            fontSize = 28.sp,
            fontFamily = SummaryNotesFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 5.dp)
        )
        Text(
            text = "(${context.getString(legend)})",
            fontSize = 16.sp,
            textAlign = TextAlign.Center

        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingOnePreview() {
    OnBoardingOneContent(
        onNextClicked = {}
    )
}