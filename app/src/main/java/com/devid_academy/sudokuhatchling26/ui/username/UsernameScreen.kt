package com.devid_academy.sudokuhatchling26.ui.username

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.logic.viewmodel.RegisterEvent
import com.devid_academy.sudokuhatchling26.logic.viewmodel.RegisterViewModel
import com.devid_academy.sudokuhatchling26.ui.bootstrap.Screen
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.CustomButton
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.InputFormTextField
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.ScaffoldComposable
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily
import com.devid_academy.sudokuhatchling26.ui.theme.VeryLightGrey
import com.devid_academy.sudokuhatchling26.ui.theme.YellowColor
import com.devid_academy.sudokuhatchling26.ui.theme.YellowFont
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun UsernameScreen(
    navController: NavController,
    viewModel: RegisterViewModel
) {

    UsernameContent()
}

@Composable
private fun UsernameContent() {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.entername_yellow),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
        )
        Image(
            painter = painterResource(R.drawable.entername_waves),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ){

            Image(
                painter = painterResource(R.drawable.splash_title),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top= 100.dp)
            )
            Image(
                painter = painterResource(R.drawable.entername_owl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(R.string.entername_tell_your_name),
                fontFamily = SummaryNotesFamily,
                fontSize = 30.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top= 100.dp)
            )
            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    singleLine = true,
                    visualTransformation = UppercaseTransformation(),
                    textStyle = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 40.sp,
                        color = YellowFont,
                        letterSpacing = 20.sp,
                    ),
                    leadingIcon = {
                        Spacer(modifier = Modifier.width(10.dp))
                    },
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .height(150.dp)
                        .width(300.dp)
                        .padding(top= 10.dp)
                        .wrapContentHeight(
                            align = Alignment.CenterVertically
                        )
                        .background(Color.White, CircleShape)
                    ,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = YellowFont,
                        unfocusedBorderColor = YellowFont,
                        cursorColor = Color.LightGray,
                    ),
                )
                Text(
                    text = "_____",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 50.sp,
                    color = VeryLightGrey,
                    letterSpacing = 15.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            CustomButton(
                context = context,
                modifier = Modifier
                    .padding(bottom = 30.dp),

                imageBackground = R.drawable.button_red,
                text = R.string.button_continue,
                onClick = { }
            )



        }





    }
}

class UppercaseTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val upperText = text.text.uppercase()
        return TransformedText(AnnotatedString(upperText), OffsetMapping.Identity)
    }
}

@Preview(showBackground = true)
@Composable
private fun UsernamePreview() {
    UsernameContent()
}