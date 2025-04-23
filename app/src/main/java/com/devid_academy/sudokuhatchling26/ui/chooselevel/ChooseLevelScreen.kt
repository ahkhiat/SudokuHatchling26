package com.devid_academy.sudokuhatchling26.ui.chooselevel

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.CustomRadioButton
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun ChooseLevelScreen() {
    ChooseLevelContent()
}

@Composable
private fun ChooseLevelContent() {
    val context = LocalContext.current

    var selectedLevel by remember { mutableStateOf(0) }

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
        Text(
            text = context.getString(R.string.choose_level_title),
            fontSize = 34.sp,
            fontFamily = SummaryNotesFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 20.dp)
        ) {
            RowLevelChoice(
                context = context,
                R.drawable.level1,
                R.string.level1,
                R.string.level1_legend,
                selected = selectedLevel == 1,
                onClick = { selectedLevel = 1 }
            )
            RowLevelChoice(
                context = context,
                R.drawable.level2,
                R.string.level2,
                R.string.level2_legend,
                selected = selectedLevel == 2,
                onClick = { selectedLevel = 2 }
            )


        }


    }


}
@Composable
private fun RowLevelChoice(
    context: Context,
    image: Int,
    level: Int,
    legend: Int,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .width(350.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )
        Column (

        ){
            Text(
                text = context.getString(level),
                fontSize = 34.sp,
                fontFamily = SummaryNotesFamily,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
            Text(
                text = "(${context.getString(legend)})",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )

        }
        CustomRadioButton(
            selected = selected,
            onClick = onClick,
            size = 36.dp
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun ChooseLevelPreview() {
    ChooseLevelContent()
}
// important de le faire privé (tout sauf le screen)