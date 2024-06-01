package id.ac.unpas.managemen_keuangan.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.ac.unpas.managemen_keuangan.ui.screens.TransactionScreens.FormTransactionScreen
import id.ac.unpas.managemen_keuangan.ui.screens.TransactionScreens.ListTransactionScreen
import id.ac.unpas.managemen_keuangan.ui.screens.category.FormCategoryScreen
import id.ac.unpas.managemen_keuangan.ui.screens.category.ListCategoryScreen

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val currentRoute = remember {
        mutableStateOf("")
    }

    Scaffold { innerPadding ->
        NavHost(navController = navController, startDestination = "home") {

            composable(NavScreen.Home.route) {
                currentRoute.value = NavScreen.Home.route
                HomeScreen(navController = navController, modifier = Modifier.padding(innerPadding)) {
                    navController.navigate(NavScreen.ListTransaction.route)
                }
            }

            composable(NavScreen.AddTransaction.route) {
                currentRoute.value = NavScreen.AddTransaction.route
                FormTransactionScreen(modifier = Modifier.padding(innerPadding))
            }

            composable(NavScreen.AddCategory.route) {
                currentRoute.value = NavScreen.AddCategory.route
                FormCategoryScreen(modifier = Modifier.padding(innerPadding))
            }

            composable(NavScreen.ListCategory.route) {
                currentRoute.value = NavScreen.ListCategory.route
                ListCategoryScreen(modifier = Modifier.padding(innerPadding)) { id ->
                    navController.navigate("${NavScreen.EditCategory.route}/$id")
                }
            }
            composable(NavScreen.ListTransaction.route) {
                currentRoute.value = NavScreen.ListTransaction.route
                ListTransactionScreen(modifier = Modifier.padding(innerPadding)) { id ->
                    navController.navigate("${NavScreen.EditTransaction.route}/$id")
                }
            }

            composable(NavScreen.EditCategory.routeWithArgument,
                arguments = listOf(navArgument(NavScreen.EditCategory.argument0) { type = NavType.StringType }))
            { backStackEntry ->
                val id = backStackEntry.arguments?.getString(NavScreen.EditCategory.argument0) ?: return@composable

                currentRoute.value = NavScreen.EditCategory.route
                FormCategoryScreen(modifier = Modifier.padding(innerPadding), id = id)
            }

            composable(NavScreen.EditTransaction.routeWithArgument,
                arguments = listOf(navArgument(NavScreen.EditTransaction.argument0) { type = NavType.StringType }))
            { backStackEntry ->
                val id = backStackEntry.arguments?.getString(NavScreen.EditTransaction.argument0) ?: return@composable

                currentRoute.value = NavScreen.EditTransaction.route
                FormTransactionScreen(modifier = Modifier.padding(innerPadding), id = id)
            }
        }

    }
}