package com.devid_academy.sudokuhatchling26.ui.game

import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.GridLayout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.geometry.Offset
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.logic.enum.LevelChoiceEnum
import com.devid_academy.sudokuhatchling26.logic.viewmodel.GameViewModel
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.CardLevelChoice
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.CustomButton
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.MinimalDropdownMenu
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.ScaffoldComposable
import com.devid_academy.sudokuhatchling26.ui.theme.GreyBackground
import com.devid_academy.sudokuhatchling26.ui.theme.OrangeBackground
import com.devid_academy.sudokuhatchling26.ui.theme.PerfectPenmanshipFamily
import com.devid_academy.sudokuhatchling26.ui.theme.RecoletaFamily
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily
import io.github.jan.supabase.realtime.Column
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

@Composable
fun GameScreen(
    navController: NavController,
    difficultyLevelName: LevelChoiceEnum
) {
    val viewModel: GameViewModel = koinViewModel()
    val grid = viewModel.gridStateFlow.collectAsState()
    val editableCells = viewModel.editableCells

    LaunchedEffect(difficultyLevelName) {
        viewModel.setDifficultyIndex(difficultyLevelName)
        viewModel.getGrid()
    }
    GameContent(
        difficultyLevelName = difficultyLevelName,
        grid = grid.value,
        onExitClick =  navController::popBackStack,
        onSetNumber = viewModel::updateCell,
        editableCells = editableCells,
        onVerifyGrid = viewModel::verifyGrid,
        onSolve = viewModel::solvePuzzle
    )
}

@Composable
private fun GameContent(
    difficultyLevelName: LevelChoiceEnum,
    grid : List<List<Int>> = emptyList(),
    onMenuClick: () -> Unit = {},
    onExitClick: () -> Unit,
    onSetNumber: (Int, Int) -> Unit,
    editableCells: List<Boolean> = emptyList(),
    onVerifyGrid: () -> Unit,
    onSolve: () -> Unit
) {
    var pickedNumber by remember { mutableStateOf(0) }
    var pickedCase by remember { mutableStateOf(-1) }

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
                            .clickable(onClick = onExitClick)
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
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.game_button_hint),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clickable(onClick = onSolve)
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
                    if (grid.isNotEmpty()) {
                        Box(){
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(9),
                                modifier = Modifier.padding(top = 50.dp)
                            ) {
                                itemsIndexed(
                                    items = grid.flatten(),
                                ) { index, item ->
                                    GridCase(
                                        value = item,
                                        isEditable = editableCells.getOrNull(index) == true,
                                        isSelected = pickedCase == index,
                                        onClick = {
                                            pickedCase = index
                                        }
                                    )
                                }
                            }



                        }
                    }
                    else {
                        Column(
                            modifier = Modifier
                                .size(324.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                stringResource(R.string.no_grid_found),
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                    SudokuKeyboard(
                        onNumberClick = { number ->
                            pickedNumber = number
                            onSetNumber(pickedCase, pickedNumber)
                            Log.i("CLICK", "PickedCase : $pickedCase, Number clicked : $pickedNumber, ")
                        },
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
                    onClick = onVerifyGrid

                )
            }
        }
    )
}

@Composable
fun GridCase(
    value : Int?,
    isEditable: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        isSelected -> OrangeBackground
        isEditable -> Color.White
        else -> Color.LightGray
    }
    val textColor = when {
        isEditable -> Color.Blue
        else -> Color.Black
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(36.dp)
            .background(backgroundColor)
            .border(BorderStroke(0.5.dp, Color.Gray))
            .clickable {
                onClick()
            }
    ) {
        if(value != null && value != 0 ) {
            Text(
                text = value.toString(),
                fontSize = 20.sp,
                fontFamily = RecoletaFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = textColor
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
fun NumberButton(
    number: Int, onClick: () -> Unit
) {
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
        onExitClick = {},
        onSetNumber = { _, _ -> },
        onVerifyGrid = {},
        onSolve = {}
    )
}