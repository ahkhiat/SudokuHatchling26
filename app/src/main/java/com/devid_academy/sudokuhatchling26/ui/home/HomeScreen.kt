package com.devid_academy.sudokuhatchling26.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.logic.viewmodel.HomeViewModel
import com.devid_academy.sudokuhatchling26.ui.bootstrap.Screen
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.HomeItem
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.MinimalDropdownMenu
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.ScaffoldComposable
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController
) {
    val homeViewModel: HomeViewModel = koinViewModel()
    val username = homeViewModel.usernameStateFlow.collectAsState()
    val helloMessage = stringResource(R.string.hello, username.value)

    Log.i("HOME SCREEN", "username = ${username.value}")

    Log.i("HOME SCREEN", "helloMessage = $helloMessage")
    HomeContent(
        helloMessage = helloMessage,
        onClickPlay = {
            navController.navigate(Screen.ChooseLevel.route)
        },
        onClickLeaderboard = {
            navController.navigate(Screen.Leaderboard.route)
        },
        onLogout = {
            homeViewModel.logoutUser()
        }
    )
}

@Composable
private fun HomeContent(
    helloMessage: String,
    onClickPlay: () -> Unit,
    onClickLeaderboard: () -> Unit,
    onLogout: () -> Unit = {}
) {
    ScaffoldComposable(
        modifier = Modifier
            .fillMaxSize(),
        content = { _ ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chooselevel_yellow),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(1.2F)
                )
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = helloMessage,
                            color = Color.Black,
                            fontFamily = SummaryNotesFamily,
                            fontSize = 30.sp,
                        )
                        MinimalDropdownMenu(
                            modifier = Modifier
                                .padding(top = 10.dp, end = 10.dp),
                            onClick = onLogout
                        )
                    }

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .padding(top =  50.dp)
                        ,
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        item {
                            HomeItem(
                                imageRes = R.drawable.preview_home,
                                label = stringResource(R.string.play),
                                onClick = onClickPlay
                            )
                        }
                        item {
                            HomeItem(
                                imageRes = R.drawable.leaderboard,
                                label = stringResource(R.string.leaderboard),
                                onClick = onClickLeaderboard
                            )
                        }


                    }

                }
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    HomeContent(
        helloMessage = "Hello, Thierry",
        onClickPlay = {},
        onClickLeaderboard = {}
    )
}