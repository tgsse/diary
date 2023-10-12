package com.ix.diary.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ix.diary.ui.screens.Screen

@Composable
fun NavigationHost(
    navController: NavHostController,
) {
    NavHost(
        navController,
        startDestination = Routes.Main.route,
        route = Routes.Root.route,
    ) {
        composable(Routes.Main.route) {
            Screen()
        }
    }
}
