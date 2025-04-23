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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.devid_academy.sudokuhatchling26.ui.theme.YellowColor

@Composable
fun CustomRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    emptyColor: Color = Color.White,
    selectedColor: Color = YellowColor
) {
    val totalSize = if (selected) size * 1.2f else size

    Box(
        modifier = modifier
            .size(totalSize)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .background(color = emptyColor, shape = CircleShape)
        )

        if (selected) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .size(size * 1.1f)
                        .background(emptyColor, shape = CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(size * 1.2f)
                        .background(YellowColor, shape = CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(size)
                        .background(emptyColor, shape = CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(size * 0.6f)
                        .background(selectedColor, shape = CircleShape)
                )
            }
        }
    }
}
