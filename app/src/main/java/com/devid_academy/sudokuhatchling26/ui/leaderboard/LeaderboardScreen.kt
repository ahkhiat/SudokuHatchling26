package com.devid_academy.sudokuhatchling26.ui.leaderboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.logic.data.dto.LeaderboardItemDTO
import com.devid_academy.sudokuhatchling26.logic.enum.Ranking
import com.devid_academy.sudokuhatchling26.logic.viewmodel.LeaderboardViewModel
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.ScaffoldComposable
import com.devid_academy.sudokuhatchling26.ui.theme.GreyBackground
import com.devid_academy.sudokuhatchling26.ui.theme.RecoletaFamily
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily
import org.koin.androidx.compose.koinViewModel

@Composable
fun LeaderboardScreen(
    navController: NavController,
) {
    val viewModel: LeaderboardViewModel = koinViewModel()

    val leaderboardStateFlow = viewModel.leaderboardStateFlow.collectAsState()

    LeaderboardContent(
        leaderboardList = leaderboardStateFlow.value
    )
}

@Composable
private fun LeaderboardContent(
    leaderboardList: List<LeaderboardItemDTO> = emptyList()
) {
    ScaffoldComposable(
        modifier = Modifier
            .fillMaxSize()
            .background(GreyBackground)
            .padding(16.dp),
        content = { paddingValues ->
            Text(
                text = stringResource(R.string.leaderboard),
                color = Color.Black,
                fontFamily = SummaryNotesFamily,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                items(leaderboardList.size) { index ->
                    val leaderboardItem = leaderboardList[index]
                    LeaderboardItem(
                        rank = index + 1,
                        username = leaderboardItem.username,
                        score = leaderboardItem.score
                    )
                }
            }

        }

    )
}

@Preview(showBackground = true)
@Composable
private fun LeaderboardPreview() {
    val leaderboardList = listOf(
        LeaderboardItemDTO("Thierry", 100),
        LeaderboardItemDTO("John", 90),
        LeaderboardItemDTO("Jane", 80),
        LeaderboardItemDTO("Doe", 70),
        LeaderboardItemDTO("Smith", 60),
        LeaderboardItemDTO("Johnson", 50),
        LeaderboardItemDTO("Williams", 40),
        LeaderboardItemDTO("Brown", 30),
        LeaderboardItemDTO("Jones", 20),
        LeaderboardItemDTO("Garcia", 10)
    )
    LeaderboardContent(
        leaderboardList = leaderboardList
    )
//    LeaderboardItem(
//        username = "Thierry",
//        score = 100
//    )
}

@Composable
fun LeaderboardItem(
    rank: Int,
    username: String,
    score: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                contentAlignment = Alignment.Center,

            ){
                val ranking = Ranking.fromRank(rank)

                if (ranking.imageRes != null) {
                    Image(
                        painter = painterResource(id = ranking.imageRes!!),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = "$rank",
                        modifier = Modifier.padding(top = 10.dp),
                        fontFamily = RecoletaFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .padding(4.dp)
                            .background(Color.LightGray, shape = RoundedCornerShape(25.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$rank",
                            fontFamily = RecoletaFamily,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


            }

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = username,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "$score pts",
                fontWeight = FontWeight.Bold
            )
        }
    }
}
