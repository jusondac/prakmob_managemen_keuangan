package id.ac.unpas.managemen_keuangan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.ac.unpas.managemen_keuangan.ui.screens.TransactionScreens.FormTransactionScreen
import id.ac.unpas.managemen_keuangan.ui.screens.TransactionScreens.ListTransactionScreen
import id.ac.unpas.managemen_keuangan.ui.screens.category.FormCategoryScreen
import id.ac.unpas.managemen_keuangan.ui.screens.category.ListCategoryScreen
import id.ac.unpas.managemen_keuangan.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onExitClick: () -> Unit){
    val navController = rememberNavController()
    val currentRoute = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Manajemen Keuangan") },
                navigationIcon = {
                    if (currentRoute.value != NavScreen.Login.route) {
                        Image(
                            painterResource(id = R.drawable.baseline_home_24),
                            contentDescription = "Menu",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                            modifier = Modifier.clickable {
                                navController.navigate(NavScreen.Home.route)
                            }
                        )
                    }

                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    Image(
                        painterResource(id = R.drawable.baseline_exit_to_app_24),
                        contentDescription = "Exit",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                        modifier = Modifier.clickable {
                            onExitClick()
                        }
                    )
                }
            )
        },
        bottomBar = {
            if (currentRoute.value != NavScreen.Login.route) {
                BottomAppBar (
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painterResource(id = R.drawable.baseline_remove_red_eye_24),
                            contentDescription = "Transaction",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                            modifier = Modifier.clickable {
                                navController.navigate(NavScreen.ListTransaction.route)
                            }.weight(0.5f)
                        )
                        Image(
                            painterResource(id = R.drawable.baseline_remove_red_eye_24),
                            contentDescription = "Category",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                            modifier = Modifier.clickable {
                                navController.navigate(NavScreen.ListCategory.route)
                            }.weight(0.5f)
                        )
                    }
                }
            }

        },
//        floatingActionButton = {
//            if (currentRoute.value == NavScreen.Home.route) {
//                FloatingActionButton(onClick = { navController.navigate(NavScreen.Add.route) }) {
//                    Image(painterResource(id = R.drawable.baseline_add_24), contentDescription = "Add")
//                }
//            }
//        },
//        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "home") {

            composable(NavScreen.Login.route) {
                currentRoute.value = NavScreen.Login.route
                LoginScreen(modifier = Modifier.padding(innerPadding)) {
                    navController.navigate(NavScreen.Home.route)
                }
            }

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