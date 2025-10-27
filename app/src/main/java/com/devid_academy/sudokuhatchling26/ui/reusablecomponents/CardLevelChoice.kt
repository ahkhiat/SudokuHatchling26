package com.devid_academy.sudokuhatchling26.ui.reusablecomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devid_academy.sudokuhatchling26.logic.enum.LevelChoiceEnum
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily

@Composable
fun CardLevelChoice(
    levelChoiceEnum: LevelChoiceEnum,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .width(350.dp)
            .fillMaxWidth()
            .clickable(interactionSource = interactionSource,
                indication = null,onClick = onClick),
        horizontalArrangement = Arrangement.Start,
//        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(levelChoiceEnum.levelImage),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
        )
        Column (
            modifier = Modifier
                .padding(start = 30.dp)
        ){
            Text(
                text = stringResource(levelChoiceEnum.levelName),
                fontSize = 34.sp,
                fontFamily = SummaryNotesFamily,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
            Text(
                text = "(${stringResource(levelChoiceEnum.levelLegend)})",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )

        }
        Spacer(modifier = Modifier.weight(1f))
        CustomRadioButton(
            selected = selected,
            onClick = onClick,

            modifier = Modifier
                .align(Alignment.CenterVertically),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardLevelChoicePreview() {
    CardLevelChoice(
        levelChoiceEnum = LevelChoiceEnum.Expert,
        selected = false,
        onClick = {},
        modifier = Modifier

    )
}