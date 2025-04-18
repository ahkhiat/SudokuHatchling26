package com.devid_academy.sudokuhatchling26.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devid_academy.sudokuhatchling26.ui.chooselevel.ChooseLevelScreen
import com.devid_academy.sudokuhatchling26.ui.onboarding.OnBoardingOneScreen
import com.devid_academy.sudokuhatchling26.ui.onboarding.OnBoardingThreeScreen
import com.devid_academy.sudokuhatchling26.ui.onboarding.OnBoardingTwoScreen
import com.devid_academy.sudokuhatchling26.ui.splash.SplashScreen
import com.devid_academy.sudokuhatchling26.ui.splash.SplashViewModel
import com.devid_academy.sudokuhatchling26.ui.welcomepage.WelcomePageScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable(Screen.Splash.route) {
            val splashViewModel: SplashViewModel = koinViewModel()
            SplashScreen(navController, splashViewModel)
        }
        composable(Screen.ChooseLevel.route) {
            ChooseLevelScreen()
        }
        composable(Screen.OnBoardingOne.route) {
            OnBoardingOneScreen(navController)
        }
        composable(Screen.OnBoardingTwo.route) {
            OnBoardingTwoScreen(navController)
        }
        composable(Screen.OnBoardingThree.route) {
            OnBoardingThreeScreen(navController)
        }
        composable(Screen.WelcomePage.route) {
            WelcomePageScreen(navController)
        }
    }


}

sealed class Screen(val route: String) {
    object Splash: Screen("splash")
    object Login: Screen("login")
    object Register: Screen("register")
    object OnBoardingOne: Screen("on_boarding_one")
    object OnBoardingTwo: Screen("on_boarding_two")
    object OnBoardingThree: Screen("on_boarding_three")
    object WelcomePage: Screen("welcome_page")
    object ChooseLevel: Screen("choose_level")

}