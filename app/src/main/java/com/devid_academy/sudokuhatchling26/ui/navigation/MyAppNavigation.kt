package com.devid_academy.sudokuhatchling26.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devid_academy.sudokuhatchling26.logic.enum.LevelChoiceEnum
import com.devid_academy.sudokuhatchling26.ui.chooselevel.ChooseLevelScreen
import com.devid_academy.sudokuhatchling26.ui.login.LoginScreen
import com.devid_academy.sudokuhatchling26.logic.viewmodel.LoginViewModel
import com.devid_academy.sudokuhatchling26.ui.onboarding.OnBoardingOneScreen
import com.devid_academy.sudokuhatchling26.ui.onboarding.OnBoardingThreeScreen
import com.devid_academy.sudokuhatchling26.ui.onboarding.OnBoardingTwoScreen
import com.devid_academy.sudokuhatchling26.ui.register.RegisterScreen
import com.devid_academy.sudokuhatchling26.logic.viewmodel.RegisterViewModel
import com.devid_academy.sudokuhatchling26.ui.splash.SplashScreen
import com.devid_academy.sudokuhatchling26.logic.viewmodel.SplashViewModel
import com.devid_academy.sudokuhatchling26.ui.game.GameScreen
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
            ChooseLevelScreen(navController)
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
//        composable(
//            route = Screen.GameScreen.route + "/{difficultyIndex}",
//            arguments = listOf(navArgument("difficultyIndex") { type = NavType.IntType })
//        ) {
//            val difficultyIndex = it.arguments?.getInt("difficultyIndex") ?: 0
//            GameScreen(navController, difficultyIndex)
//        }
        composable(
            route = Screen.GameScreen.route + "/{difficulty}",
            arguments = listOf(navArgument("difficulty") { type = NavType.StringType })
        ) {
            val difficultyString = it.arguments?.getString("difficulty") ?: LevelChoiceEnum.Beginner.name
            val difficulty = LevelChoiceEnum.valueOf(difficultyString)
            GameScreen(navController, difficulty)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
    }
    /*
    composable(
        route = Screen.Details.route + "/{eventId}",
        arguments = listOf(navArgument("eventId") { type = NavType.IntType })
    ) {
        val eventId = it.arguments?.getInt("eventId") ?: 0
        val detailsViewModel: DetailsViewModel = hiltViewModel()
        DetailsScreen(navController, detailsViewModel, eventId)
    }
    */


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
    object GameScreen: Screen("game_screen")

}