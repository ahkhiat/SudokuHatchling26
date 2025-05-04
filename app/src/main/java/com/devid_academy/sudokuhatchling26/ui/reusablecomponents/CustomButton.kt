package com.devid_academy.sudokuhatchling26.ui.reusablecomponents

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily

@Composable
fun CustomButton(
    context: Context,
    modifier: Modifier = Modifier,
    imageBackground: Int,
    text: Int,
    textColor: Color = Color.White,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Image(
                painter = painterResource(imageBackground),
                contentDescription = "sign up button",
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = context.getString(text),
                fontSize = 32.sp,
                fontFamily = SummaryNotesFamily,
                textAlign = TextAlign.Center,
                color = textColor
            )
        }
    }
}
