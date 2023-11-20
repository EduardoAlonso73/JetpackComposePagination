package com.mrntlu.pagination

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mrntlu.pagination.ui.ManuelPagingListScreen

@Composable
fun NavigationComposable(
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = "manuel_paging"
    ) {
        composable("manuel_paging") {
            ManuelPagingListScreen()
        }
    }
}