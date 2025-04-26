package com.devid_academy.sudokuhatchling26.ui.reusablecomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devid_academy.sudokuhatchling26.ui.theme.YellowColor
import com.devid_academy.sudokuhatchling26.ui.theme.YellowFont

@Composable
fun InputFormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    visualTransformation: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 20.sp,
            color = YellowFont
        ),
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .wrapContentHeight(
                align = Alignment.CenterVertically
            ),
        visualTransformation = if(visualTransformation) PasswordVisualTransformation()
        else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = YellowColor,
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color.LightGray,
        )
    )
}