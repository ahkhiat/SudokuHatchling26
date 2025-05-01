package com.devid_academy.sudokuhatchling26.ui.reusablecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.devid_academy.sudokuhatchling26.ui.theme.YellowColor

@Composable
fun CustomRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    emptyColor: Color = Color.White,
    selectedColor: Color = YellowColor,

    ) {
    Box(
        modifier = modifier
            .size(36.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(color = emptyColor, shape = CircleShape)
        )
        if (selected) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(emptyColor, shape = CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(36.dp * 0.7f)
                        .background(selectedColor, shape = CircleShape)
                )
            }
        }
    }
}

@Preview
@Composable
private fun CustomRadioButtonPreview() {
    CustomRadioButton(
        selected = true,
        onClick = {},
    )
}
