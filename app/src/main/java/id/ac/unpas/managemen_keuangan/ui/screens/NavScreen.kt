package id.ac.unpas.managemen_keuangan.ui.screens

sealed class NavScreen (val route: String) {
    object Home : NavScreen("home")
    object AddTransaction : NavScreen("addTransaction")
    object AddCategory : NavScreen("addCategory")
    object AddUser : NavScreen("addUser")
    object EditTransaction : NavScreen("editTransaction") {
        const val routeWithArgument: String = "editTransaction/{id}"
        const val argument0 : String = "id"
    }
    object EditCategory : NavScreen("editCategory") {
        const val routeWithArgument: String = "editCategory/{id}"
        const val argument0 : String = "id"
    }

    object EditUser : NavScreen("editUser") {
        const val routeWithArgument: String = "editUser/{id}"
        const val argument0 : String = "id"
    }
    object ListTransaction : NavScreen("listTransaction")
    object ListCategory : NavScreen("listCategory")
    object ListUser : NavScreen("listUser")
    object Login : NavScreen("login")
}