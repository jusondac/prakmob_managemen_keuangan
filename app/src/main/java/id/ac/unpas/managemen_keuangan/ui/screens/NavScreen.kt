package id.ac.unpas.managemen_keuangan.ui.screens

sealed class NavScreen (val route: String) {
    object Home : NavScreen("home")
    object AddTransaction : NavScreen("addTransaction")
    object AddCategory : NavScreen("addCategory")
    object EditTransaction : NavScreen("editTransaction") {
        const val routeWithArgument: String = "editTransaction/{id}"
        const val argument0 : String = "id"
    }
    object EditCategory : NavScreen("editCategory") {
        const val routeWithArgument: String = "editCategory/{id}"
        const val argument0 : String = "id"
    }
    object ListTransaction : NavScreen("listTransaction")
    object ListCategory : NavScreen("listCategory")
    object Login : NavScreen("login")
}