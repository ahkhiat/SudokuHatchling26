package com.devid_academy.sudokuhatchling26.ui.splash

import android.window.SplashScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.ui.theme.YellowColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel
) {
    val isLoadingFromVM by splashViewModel.isLoading.collectAsState()
    val showFirstSplash by splashViewModel.showFirstSplash.collectAsState()
    val showSecondSplash by splashViewModel.showSecondSplash.collectAsState()
    val animateEggs by splashViewModel.animateEggs.collectAsState()

    LaunchedEffect(true) {
        splashViewModel.goToMainOrLogin.collect { direction ->
            direction?.let {
                navController.navigate(it) {
                    popUpTo("splash") {
                        inclusive = true
                    }
                }
            }
        }
    }

    SplashContent(
        isLoading = isLoadingFromVM,
        showFirstSplash = showFirstSplash,
        showSecondSplash = showSecondSplash,
        animateEggs = animateEggs
    )
}

@Composable
fun SplashContent(
    isLoading: Boolean,
    showFirstSplash: Boolean,
    showSecondSplash: Boolean,
    animateEggs: Boolean
) {
    if (showFirstSplash) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(YellowColor),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = animateEggs,
                exit = fadeOut(animationSpec = tween(1000)) + slideOutVertically(
                    animationSpec = tween(1000),
                    targetOffsetY = { 800 }
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.splash_eggs_loading),
                    contentDescription = "oeufs",
                    modifier = Modifier
                        .size(130.dp)
                )
            }
        }
    }

    if (showSecondSplash){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_yellow),
                contentDescription = "hatchling",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                    .padding(top= 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.splash_logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(300.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.splash_title),
                    contentDescription = "logo title",
                    modifier = Modifier
                        .padding(top= 30.dp)
                        .size(243.dp, 135.dp)

                )
                Image(
                    painter = painterResource(id = R.drawable.splash_eggs_loading),
                    contentDescription = "eggs",
                    modifier = Modifier
                        .padding(top= 100.dp)
                        .size(150.dp)
                )
            }


        }
    }
}




@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    SplashContent(true, false, true, false)
}
