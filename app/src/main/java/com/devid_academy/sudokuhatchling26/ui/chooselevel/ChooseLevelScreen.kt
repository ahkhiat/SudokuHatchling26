package com.devid_academy.sudokuhatchling26.ui.chooselevel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.devid_academy.sudokuhatchling26.logic.enum.LevelChoiceEnum
import com.devid_academy.sudokuhatchling26.logic.viewmodel.AuthEvent
import com.devid_academy.sudokuhatchling26.logic.viewmodel.ChooseLevelViewModel
import com.devid_academy.sudokuhatchling26.ui.navigation.Screen
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.CardLevelChoice
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.CustomButton
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.MinimalDropdownMenu
import org.koin.androidx.compose.koinViewModel


@Composable
fun ChooseLevelScreen(
    navController: NavController
) {
    val  viewModel: ChooseLevelViewModel = koinViewModel()
    var selectedLevel by remember { mutableStateOf<LevelChoiceEnum>(LevelChoiceEnum.Beginner) }

    LaunchedEffect(true) {
        viewModel.chooseLevelSharedFlow.collect { event ->
            when(event) {
                is AuthEvent.NavigateToLogin ->
                    navController.navigate(Screen.Login.route)
                else -> {}
            }
        }
    }

    ChooseLevelContent(
        selectedLevel = selectedLevel,
        onSelectLevel = { selectedLevel = it },
        onClick = {
            viewModel.logoutUser()
        },
        onClickLetsPlay = {
            navController.navigate(Screen.GameScreen.route + "/$selectedLevel")
        }
    )
}

@Composable
private fun ChooseLevelContent(
    selectedLevel : LevelChoiceEnum,
    onSelectLevel: (LevelChoiceEnum) -> Unit,
    onClick: () -> Unit,
    onClickLetsPlay: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.chooselevel_yellow),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop,
            )

        MinimalDropdownMenu(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 10.dp, end = 10.dp),
            onClick = onClick
        )
        Text(
            text = context.getString(R.string.choose_level_title),
            fontSize = 34.sp,
            fontFamily = SummaryNotesFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 20.dp)
        ) {
            LevelChoiceEnum.entries.forEach { level ->
                CardLevelChoice(
                    levelChoiceEnum = level,
                    selected = selectedLevel == level,
                    onClick = { onSelectLevel(level) },
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                )

            }
        }
        CustomButton(
            context = context,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            imageBackground = R.drawable.button_red,
            text = R.string.lets_start,
            onClick = {
                onClickLetsPlay()
//                Log.d("CHOOSE LEVEL BUTTON", "Level selected : ${LevelChoiceEnum.entries[selectedLevel]}")
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ChooseLevelPreview() {
    ChooseLevelContent(
        onClick = {},
        onClickLetsPlay = {},
        selectedLevel = LevelChoiceEnum.Beginner,
        onSelectLevel = {}
    )
}
// important de le faire privé (tout sauf le screen)