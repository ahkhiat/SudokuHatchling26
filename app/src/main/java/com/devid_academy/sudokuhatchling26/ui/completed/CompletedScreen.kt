package com.devid_academy.sudokuhatchling26.ui.completed

import android.graphics.fonts.FontStyle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.logic.viewmodel.CompletedViewModel
import com.devid_academy.sudokuhatchling26.logic.viewmodel.GameViewModel
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.CustomButton
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.MinimalDropdownMenu
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.ScaffoldComposable
import com.devid_academy.sudokuhatchling26.ui.theme.MontserratAlternatesFamily
import com.devid_academy.sudokuhatchling26.ui.theme.OrangeBackground
import com.devid_academy.sudokuhatchling26.ui.theme.OrangeFont
import com.devid_academy.sudokuhatchling26.ui.theme.RecoletaFamily
import com.devid_academy.sudokuhatchling26.ui.theme.RedBackground
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily
import com.devid_academy.sudokuhatchling26.ui.theme.YellowFont
import io.ktor.http.ContentType
import org.koin.androidx.compose.koinViewModel

@Composable
fun CompletedScreen(
    navController: NavController,
    gameViewModel: GameViewModel,
) {
    val completedViewModel: CompletedViewModel = koinViewModel()

    CompletedContent(

    )
}

@Composable
private fun CompletedContent() {
    ScaffoldComposable(
        modifier = Modifier
            .fillMaxSize()
            .background(RedBackground),
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                // Image cadre au centre
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top= 100.dp)
                        .border(
                            width = 10.dp,
                            color = YellowFont
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.felicitations),
                        contentDescription = null,
                        modifier = Modifier
//                            .fillMaxSize()
                    )
                    Text(
                        text = "EggShell level 1",
                        color = Color.White,
                        fontFamily = MontserratAlternatesFamily,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 130.dp)
                    )
                    Text(
                        text = "COMPLETED",
                        color = Color.White,
                        fontFamily = SummaryNotesFamily,
                        fontSize = 46.sp,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 160.dp)
                    )
                    Text(
                        text = "Well done, Jhay!",
                        color = Color.Black,
                        fontFamily = SummaryNotesFamily,
                        fontSize = 40.sp,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 250.dp)
                    )
                    Text(
                        text = "Rewards",
                        color = OrangeFont,
                        fontFamily = MontserratAlternatesFamily,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 320.dp)
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 320.dp)
                            .border(
                                width = 2.dp,
                                color = OrangeFont
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = null,
                            modifier = Modifier
                        )
                        Text(
                            text = "x10",
                            color = OrangeFont,
                            fontFamily = MontserratAlternatesFamily,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier

                        )
                    }
                    CustomButton(
                        context = LocalContext.current,
                        modifier = Modifier
                            .width(250.dp)
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 10.dp),
                        imageBackground = R.drawable.button_yellow,
                        text = R.string.lets_start,
                        onClick = { }
                    )
                }

                AnimatedVisibility(
                    visible = true,
                    enter = slideInVertically(
                        initialOffsetY = { 1000 },
                        animationSpec = tween(1000)
                    ) + fadeIn(animationSpec = tween(1000)),
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.completed_owl),
                        contentDescription = null,
                    )
                }
//                Image(
//                    painter = painterResource(id = R.drawable.completed_owl),
//                    contentDescription = null,
//                    modifier = Modifier.align(Alignment.BottomStart)
//                )
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
private fun CompletedPreview() {
    CompletedContent()
}