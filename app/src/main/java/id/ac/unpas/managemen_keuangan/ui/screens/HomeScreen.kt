package id.ac.unpas.managemen_keuangan.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier, onLihat: () -> Unit) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Aplikasi Manajemen Keuangan", modifier = Modifier.fillMaxWidth())
        Button(onClick = {
            navController.navigate(NavScreen.AddTransaction.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Tambah Transaksi")
        }

        Button(onClick = {
            navController.navigate(NavScreen.AddCategory.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Tambah Category")
        }

        Button(onClick = {
            navController.navigate(NavScreen.Login.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Login")
        }
    }
}