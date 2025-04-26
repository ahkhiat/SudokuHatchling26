package com.devid_academy.sudokuhatchling26.ui.register

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.logic.viewmodel.RegisterViewModel
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.CustomButton
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.InputFormTextField
import com.devid_academy.sudokuhatchling26.ui.navigation.Screen
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    navController: NavController
) {
    val viewModel: RegisterViewModel = koinViewModel()

    RegisterContent(
        onRegister = { email, password, passwordConfirm ->
            viewModel.registerUser(
                email = email,
                password = password,
                passwordConfirm = passwordConfirm
            )
        },
        onNavigate = {
            navController.navigate(Screen.Login.route)
        }
    )
}

@Composable
fun RegisterContent(
    onRegister: (
        email: String,
        password: String,
        passwordConfirm: String) -> Unit,
    onNavigate: () -> Unit
) {

    val context = LocalContext.current

    var emailForm by remember { mutableStateOf("") }
    var passwordForm by remember { mutableStateOf("") }
    var passwordConfirmForm by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.entername_yellow),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .offset(y = -80.dp)
        )
        Image(
            painter = painterResource(R.drawable.entername_waves),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
        Image(
            painter = painterResource(R.drawable.splash_title),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
        Image(
            painter = painterResource(R.drawable.entername_owl),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(280.dp)
                .padding(top = 150.dp)
        )
        Column(
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.TopCenter)
                .padding(top = 230.dp)
        ) {
            Text(
                text = context.getString(R.string.register_et_email_label),
                fontSize = 24.sp,
                fontFamily = SummaryNotesFamily,
                textAlign = TextAlign.Center
            )
            InputFormTextField(
                value = emailForm,
                onValueChange = { emailForm = it },
                label = context.getString(R.string.register_et_email),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = context.getString(R.string.register_et_password_label),
                fontSize = 24.sp,
                fontFamily = SummaryNotesFamily,
                textAlign = TextAlign.Center
            )
            InputFormTextField(
                value = passwordForm,
                onValueChange = { passwordForm = it },
                label = context.getString(R.string.register_et_password),
                visualTransformation = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = context.getString(R.string.register_et_passwordconfirm_label),
                fontSize = 24.sp,
                fontFamily = SummaryNotesFamily,
                textAlign = TextAlign.Center
            )
            InputFormTextField(
                value = passwordConfirmForm,
                onValueChange = { passwordConfirmForm = it },
                label = context.getString(R.string.register_et_passwordconfirm),
                visualTransformation = true
            )
        }
        Text(
            text = context.getString(R.string.already_registered),
            fontSize = 24.sp,
            fontFamily = SummaryNotesFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 150.dp)
                .clickable {
                    onNavigate()
                }
        )
        CustomButton(
            context = context,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            imageBackground = R.drawable.button_red,
            text = R.string.button_register,
            onClick = {
                onRegister(
                    emailForm,
                    passwordForm,
                    passwordConfirmForm
                )
                Log.i("REGISTER BUTTON", "Email : '$emailForm', Password : '$passwordForm', PasswordConfirm : '$passwordConfirmForm'")
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterContent(
        onRegister = {_,_,_ -> },
        onNavigate = {}
    )
}
