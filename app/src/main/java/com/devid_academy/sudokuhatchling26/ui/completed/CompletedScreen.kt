package com.devid_academy.sudokuhatchling26.ui.completed

import android.graphics.fonts.FontStyle
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.logic.viewmodel.CompletedViewModel
import com.devid_academy.sudokuhatchling26.logic.viewmodel.GameViewModel
import com.devid_academy.sudokuhatchling26.ui.bootstrap.Screen
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
    val username = completedViewModel.usernameStateFlow.collectAsState()
    val wellDoneMessage = stringResource(R.string.well_done, username.value)
    var visible by remember { mutableStateOf(false) }
    val levelNameState = gameViewModel.difficultyIndexStateFlow.collectAsState()
    val scoreEarned = levelNameState.value.scoreValue

    val level = levelNameState.value
    val translatedLevelName = stringResource(id = level.levelName)

    val levelValue = 1
    val levelLabel = stringResource(R.string.level, translatedLevelName, levelValue)

    Log.i("CompletedScreen", "Level label from diffIndexStateFlow from GameVM: ${translatedLevelName}")
    LaunchedEffect(Unit) {
        visible = true
    }

    CompletedContent(
        wellDoneMessage = wellDoneMessage,
        levelLabel = levelLabel,
        scoreEarned = scoreEarned,
        visible = visible,
        onNavigateToChooseLevel = {
            navController.navigate(Screen.ChooseLevel.route) {
                popUpTo(Screen.ChooseLevel.route) { inclusive = true }
            }
        }
    )
}

@Composable
private fun CompletedContent(
    wellDoneMessage: String,
    levelLabel: String,
    scoreEarned: Int,
    visible: Boolean,
    onNavigateToChooseLevel: () -> Unit = {}
) {
    ScaffoldComposable(
        modifier = Modifier
            .fillMaxSize()
            .background(RedBackground),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AnimatedVisibility(
                    visible = visible,
                    enter = scaleIn(
                        animationSpec = tween(
                            durationMillis = 600,
                            easing = FastOutSlowInEasing
                        ),
                        initialScale = 0.5f // Départ à 50 % de la taille
                    ),
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 100.dp)
                            .width(350.dp)
//                            .border(1.dp, Color.Black)

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.felicitations),
                            contentDescription = null,
                        )
                        Text(
                            text = levelLabel,
                            color = Color.White,
                            fontFamily = MontserratAlternatesFamily,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 115.dp)
                        )
                        Text(
                            text = stringResource(R.string.completed),
                            color = Color.White,
                            fontFamily = SummaryNotesFamily,
                            fontSize = 46.sp,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 145.dp)
                        )
                        Text(
                            text = wellDoneMessage,
                            color = Color.Black,
                            fontFamily = SummaryNotesFamily,
                            fontSize = 32.sp,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 230.dp)
                        )
                        Text(
                            text = "Rewards",
                            color = OrangeFont,
                            fontFamily = MontserratAlternatesFamily,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 280.dp)
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 290.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.star),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(80.dp)
                            )
                            Text(
                                text = scoreEarned.toString(),
                                color = OrangeFont,
                                fontFamily = MontserratAlternatesFamily,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier

                            )
                        }
                        CustomButton(
                            modifier = Modifier
                                .width(300.dp)
                                .align(Alignment.BottomCenter),
                            imageBackground = R.drawable.button_yellow,
                            text = R.string.button_continue,
                            textColor = Color.Black,
                            onClick = onNavigateToChooseLevel
                        )
                    }
                }

                AnimatedVisibility(
                    visible = visible,
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
    CompletedContent(
        wellDoneMessage = "Well done Thierry!",
        levelLabel = "Gnagna",
        scoreEarned = 5,
        visible = true
    )
}