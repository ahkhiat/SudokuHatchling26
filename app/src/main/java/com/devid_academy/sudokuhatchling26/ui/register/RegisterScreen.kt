package com.devid_academy.sudokuhatchling26.ui.register

import android.content.Context
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.logic.viewmodel.RegisterEvent
import com.devid_academy.sudokuhatchling26.logic.viewmodel.RegisterViewModel
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.CustomButton
import com.devid_academy.sudokuhatchling26.ui.reusablecomponents.InputFormTextField
import com.devid_academy.sudokuhatchling26.ui.bootstrap.Screen
import com.devid_academy.sudokuhatchling26.ui.theme.SummaryNotesFamily

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel
) {
    val context = LocalContext.current
    var dialogMessageResId by remember { mutableStateOf<Int?>(null) }

    val emailStateFlow by viewModel.email.collectAsState()
    val passwordStateFlow by viewModel.password.collectAsState()
    val passwordConfirmStateFlow by viewModel.passwordConfirm.collectAsState()

    LaunchedEffect(true) {
        viewModel.registerSharedFlow.collect { event ->
            when (event) {
                is RegisterEvent.NavigateToUserName -> {
                    navController.navigate(Screen.Username.route) {
                        popUpTo(Screen.Register.route) {
                            inclusive = true
                        }
                    }
                }
                is RegisterEvent.ShowDialog -> {
                    dialogMessageResId = event.resId
                }
                else -> {}
            }
        }
    }

    dialogMessageResId?.let { resId ->
        AlertDialog(
            onDismissRequest = { dialogMessageResId = null },
            title = { Text("Information") },
            text = { Text(stringResource(resId)) },
            confirmButton = {
                TextButton(onClick = { dialogMessageResId = null }) {
                    Text("OK")
                }
            }
        )
    }

    RegisterContent(
        context = context,
        emailStateFlow = emailStateFlow,
        passwordStateFlow = passwordStateFlow,
        passwordConfirmStateFlow = passwordConfirmStateFlow,
        onEmailChange = viewModel::onEmailChanged,
        onPasswordChange = viewModel::onPasswordChanged,
        onPasswordConfirmChange = viewModel::onPasswordConfirmChanged,
        onVerifyInputs = viewModel::verifyInputs,
        onNavigateToLogin = {
            navController.navigate(Screen.Login.route)
        }
    )
}

@Composable
fun RegisterContent(
    context: Context,
    emailStateFlow: String,
    passwordStateFlow: String,
    passwordConfirmStateFlow: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    onVerifyInputs: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
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
                value = emailStateFlow,
                onValueChange = { onEmailChange(it) },
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
                value = passwordStateFlow,
                onValueChange = { onPasswordChange(it) },
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
                value = passwordConfirmStateFlow,
                onValueChange = { onPasswordConfirmChange(it) },
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
                .clickable(onClick = onNavigateToLogin)

        )
        CustomButton(
            context = context,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            imageBackground = R.drawable.button_red,
            text = R.string.button_continue,
            onClick = onVerifyInputs
        )
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterContent(
        context = LocalContext.current,
        emailStateFlow = "",
        passwordStateFlow = "",
        passwordConfirmStateFlow = "",
        onEmailChange = {},
        onPasswordChange = {},
        onPasswordConfirmChange = {},
        onVerifyInputs = {},
        onNavigateToLogin = {},
    )
}
