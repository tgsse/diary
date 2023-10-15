package com.ix.diary.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ix.ui.screens.auth.AuthenticationScreen
import com.ix.ui.screens.diary.DiaryScreen
import com.ix.ui.screens.entry.EntryScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
) {
    NavHost(
        navController,
        startDestination = Routes.Authentication.route,
        route = Routes.Root.route,
    ) {
        authRoute(navController)
        diaryRoute(navController)
        writeRoute(navController)
    }
}

fun NavGraphBuilder.authRoute(navController: NavHostController) {
    composable(route = Routes.Authentication.route) {
        AuthenticationScreen(
            navigateToDiary = { navController.navigate(Routes.Diary.route) },
        )
    }
}

fun NavGraphBuilder.diaryRoute(navController: NavHostController) {
    composable(route = Routes.Diary.route) {
        DiaryScreen(
            navigateToEntry = {
                navController.navigate(Routes.Entry.route)
            },
        )
    }
}

fun NavGraphBuilder.writeRoute(navController: NavHostController) {
    composable(
        route = Routes.Entry.route,
        arguments = listOf(
            navArgument(name = "id") {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            },
        ),
    ) {
        EntryScreen(navigateBack = { navController.popBackStack() })
    }
}
