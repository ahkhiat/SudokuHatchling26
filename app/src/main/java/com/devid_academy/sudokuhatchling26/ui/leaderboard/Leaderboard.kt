package com.devid_academy.sudokuhatchling26.ui.leaderboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.ScaffoldComposable

@Composable
fun LeaderboardScreen(
    navController: NavController
) {
    LeaderboardContent()
}

@Composable
private fun LeaderboardContent() {
    ScaffoldComposable(
        modifier = Modifier,
        content = {

        }

    )
}

@Preview(showBackground = true)
@Composable
private fun LeaderboardPreview() {
    LeaderboardContent()
}