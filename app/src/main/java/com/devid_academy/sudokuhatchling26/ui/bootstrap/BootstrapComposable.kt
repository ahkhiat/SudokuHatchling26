package com.devid_academy.sudokuhatchling26.ui.bootstrap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.devid_academy.sudokuhatchling26.logic.enum.AuthentificationStateEnum
import com.devid_academy.sudokuhatchling26.logic.enum.LevelChoiceEnum
import com.devid_academy.sudokuhatchling26.logic.viewmodel.GameViewModel
import com.devid_academy.sudokuhatchling26.logic.viewmodel.RegisterViewModel
import com.devid_academy.sudokuhatchling26.ui.chooselevel.ChooseLevelScreen
import com.devid_academy.sudokuhatchling26.ui.login.LoginScreen
import com.devid_academy.sudokuhatchling26.ui.onboarding.OnBoardingOneScreen
import com.devid_academy.sudokuhatchling26.ui.onboarding.OnBoardingThreeScreen
import com.devid_academy.sudokuhatchling26.ui.onboarding.OnBoardingTwoScreen
import com.devid_academy.sudokuhatchling26.ui.register.RegisterScreen
import com.devid_academy.sudokuhatchling26.ui.splash.SplashScreen
import com.devid_academy.sudokuhatchling26.logic.viewmodel.UserViewModel
import com.devid_academy.sudokuhatchling26.ui.completed.CompletedScreen
import com.devid_academy.sudokuhatchling26.ui.game.GameScreen
import com.devid_academy.sudokuhatchling26.ui.home.HomeScreen
import com.devid_academy.sudokuhatchling26.ui.leaderboard.LeaderboardScreen
import com.devid_academy.sudokuhatchling26.ui.username.UsernameScreen
import com.devid_academy.sudokuhatchling26.ui.welcomepage.WelcomePageScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun BootstrapComposable() {
    val navController = rememberNavController()
    val userViewModel: UserViewModel = koinViewModel()

    val authentificationState = userViewModel.observeUserState().collectAsStateWithLifecycle()

    when (authentificationState.value) {
        AuthentificationStateEnum.Splash -> SplashScreen()
        AuthentificationStateEnum.Authenticated -> AuthenticatedNavHost(navController)
        AuthentificationStateEnum.Unauthenticated -> UnauthenticatedNavHost(navController)
    }
}

@Composable
fun AuthenticatedNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Leaderboard.route) {
            LeaderboardScreen(navController)
        }
        composable(Screen.ChooseLevel.route) {
            ChooseLevelScreen(navController)
        }

        navigation(
            startDestination = Screen.GameScreen.route + "/{difficulty}",
            route = "game_flow"
        ) {
            composable(
                route = Screen.GameScreen.route + "/{difficulty}",
                arguments = listOf(navArgument("difficulty") { type = NavType.StringType })
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("game_flow")
                }
                val difficultyString = backStackEntry.arguments?.getString("difficulty") ?: LevelChoiceEnum.Beginner.name
                val difficulty = LevelChoiceEnum.valueOf(difficultyString)
                val gameViewModel: GameViewModel = koinViewModel(viewModelStoreOwner = parentEntry)

                GameScreen(navController, gameViewModel, difficulty)
            }
            composable(route = Screen.Completed.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("game_flow")
                }
                val gameViewModel: GameViewModel = koinViewModel(viewModelStoreOwner = parentEntry)
                CompletedScreen(navController, gameViewModel)
            }
        }

    }
}

@Composable
fun UnauthenticatedNavHost(navController: NavHostController) {
    val registerViewModel: RegisterViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.OnBoardingOne.route
    ) {
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
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController, registerViewModel)
        }
        composable(Screen.Username.route) {
            UsernameScreen(navController, registerViewModel)
        }
    }
}


sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Register: Screen("register")
    object OnBoardingOne: Screen("on_boarding_one")
    object OnBoardingTwo: Screen("on_boarding_two")
    object OnBoardingThree: Screen("on_boarding_three")
    object WelcomePage: Screen("welcome_page")
    object Home: Screen("home")
    object Leaderboard: Screen("leaderboard")
    object ChooseLevel: Screen("choose_level")
    object GameScreen: Screen("game_screen")
    object Username: Screen("username")
    object Completed: Screen("completed")
}
