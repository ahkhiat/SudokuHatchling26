package com.devid_academy.sudokuhatchling26.ui.game

import android.provider.Settings.Global.getString
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.logic.enum.LevelChoiceEnum
import com.devid_academy.sudokuhatchling26.logic.viewmodel.GameViewModel
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.CardLevelChoice
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.CustomButton
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.MinimalDropdownMenu
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.ScaffoldComposable
import com.devid_academy.sudokuhatchling26.ui.theme.GreyBackground
import com.devid_academy.sudokuhatchling26.ui.theme.PerfectPenmanshipFamily
import com.devid_academy.sudokuhatchling26.ui.theme.RecoletaFamily
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

@Composable
fun GameScreen(
    navController: NavController,
    difficultyLevelName: LevelChoiceEnum
) {
    val viewModel: GameViewModel = koinViewModel()
    val grid = viewModel.gridStateFlow.collectAsState()

    LaunchedEffect(difficultyLevelName) {
        viewModel.setDifficultyIndex(difficultyLevelName)
        viewModel.getGrid()
    }

    GameContent(
        difficultyLevelName = difficultyLevelName,
        grid = grid.value,
        onExitClick = {
            navController.popBackStack()
        }
    )
}

@Composable
private fun GameContent(
    difficultyLevelName: LevelChoiceEnum,
    grid : List<List<Int>> = emptyList(),
    onMenuClick: () -> Unit = {},
    onExitClick: () -> Unit
) {
    ScaffoldComposable(
        modifier = Modifier
            .fillMaxSize()
            .background(GreyBackground),
        content = { paddingValues ->

            val context = LocalContext.current

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically

                    ){
                    Image(
                        painter = painterResource(id = R.drawable.game_button_close),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                onExitClick()
                            }
                    )
                    Text(
                        text = "${stringResource(difficultyLevelName.levelName)} Level - ",
                        fontSize = 18.sp,
                        fontFamily = SummaryNotesFamily,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start= 10.dp)
                    )
                    Text(
                        text = "1 to 10",
                        fontSize = 16.sp,
                        fontFamily = RecoletaFamily,
                        fontWeight = FontWeight.Bold,
//                        fontFamily = SummaryNotesFamily,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.game_button_hint),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 10.dp)

                    )
                    MinimalDropdownMenu(
                        modifier = Modifier,
                        onClick = onMenuClick
                    )
                }

                Text(
                    text = context.getString(R.string.game_title_line1),
                    fontSize = 24.sp,
                    fontFamily = SummaryNotesFamily,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 70.dp)
                )
                Text(
                    text = context.getString(R.string.game_title_line2),
                    fontSize = 24.sp,
                    fontFamily = SummaryNotesFamily,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp)
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // contenu
                    if (grid.isNotEmpty()) {
                        FinalGrid(
                            numbers = grid,
                            modifier = Modifier.padding(top = 50.dp),
                            onClick = {}
                        )
                    } else {
                        Text("Chargement de la grille...", modifier = Modifier.padding(16.dp))
                    }
//                    FinalGrid(
//                        modifier = Modifier
//                            .padding(top = 50.dp),
//
//                    )

                    SudokuKeyboard(
                        onNumberClick = { number ->
                            Log.d("NUMBER CLICKED", number.toString())
                        }
                        ,
                        onDeleteClick = {
                            Log.d("DELETE CLICKED", "Delete clicked")
                        },
                        modifier = Modifier
                            .padding(top= 15.dp)
                    )
                }
                CustomButton(
                    context = context,
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                    imageBackground = R.drawable.button_red,
                    text = R.string.solve,
                    onClick = {
                        Log.d("CHOOSE LEVEL BUTTON", "Level selected : Quelquechose")
                    }
                )
            }
        }
    )
}

@Composable
fun FinalGrid(
    numbers: List<List<Int?>?> = emptyList(),
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        for (i in 0 .. 2) {
            Row() {
                for (j in 0 .. 2) {
                    GridSquareOfNine(
                        numbers = numbers[i * 3 + j],
                        onClick = onClick
                    )
                }
            }
        }
    }
}



@Composable
fun GridSquareOfNine(
    numbers: List<Int?>? = emptyList(),
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .border(BorderStroke(1.dp, Color.Black))
    ) {
        for (i in 0 .. 2) {
            Row() {
                for (j in 0 .. 2) {
                    GridCase(
                        value = numbers?.get(i * 3 + j),
                        isEditable = numbers?.get(i * 3 + j) == 0,
                        onClick = onClick
                    )
                }
            }
        }
    }
}

@Composable
fun GridCase(
    value : Int?,
    isEditable: Boolean,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(36.dp)
            .background(if (isEditable) Color.White else Color.LightGray)
            .border(BorderStroke(0.5.dp, Color.Gray) )
    ) {
        if(value != null && value != 0 ) {
            Text(
                text = value.toString(),
                fontSize = 20.sp,
                fontFamily = RecoletaFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }

    }
}

@Composable
fun SudokuKeyboard(
    modifier: Modifier = Modifier,
    onNumberClick: (Int) -> Unit,
    onDeleteClick: () -> Unit,
) {
    val numbersFirstRow = (1..5).toList()
    val numbersSecondRow = (6..9).toList()

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            numbersFirstRow.forEach { number ->
                NumberButton(number = number, onClick = { onNumberClick(number) })
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            numbersSecondRow.forEach { number ->
                NumberButton(
                    number = number,
                    onClick = { onNumberClick(number) }
                )
            }
            Button(
                onClick = onDeleteClick,
                modifier = Modifier
                    .size(55.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text(
                    text = "⌫",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun NumberButton(number: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(55.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        contentPadding = PaddingValues(0.dp),
        shape = CircleShape
    ) {
        Text(
            text = number.toString(),
            fontSize = 20.sp,
            fontFamily = RecoletaFamily,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}




@Preview(showBackground = true)
@Composable
private fun GameContentPreview() {
    GameContent(
        difficultyLevelName = LevelChoiceEnum.Beginner,
        grid = listOf(
            listOf(1, 2, 3, 0, 5, 6, 0, 8, 0),
            listOf(4, 0, 0, 7, 8, 0, 0, 2, 3),
            listOf(0, 5, 0, 7, 8, 9, 1, 0, 3),
            listOf(0, 0, 6, 7, 8, 9, 0, 0, 0),
            listOf(4, 5, 0, 7, 0, 0, 0, 2, 3),
            listOf(0, 0, 6, 0, 0, 9, 1, 0, 0),
            listOf(0, 5, 0, 0, 8, 9, 1, 2, 3),
            listOf(4, 5, 0, 0, 8, 0, 0, 2, 0),
            listOf(0, 5, 6, 7, 0, 9, 1, 2, 3),

        ),
        onExitClick = {}
    )
}