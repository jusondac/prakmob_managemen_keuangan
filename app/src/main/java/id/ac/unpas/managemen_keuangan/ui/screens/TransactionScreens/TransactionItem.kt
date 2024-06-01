package id.ac.unpas.managemen_keuangan.ui.screens.TransactionScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import id.ac.unpas.managemen_keuangan.R
import id.ac.unpas.managemen_keuangan.models.Transaction

@Composable
fun TransactionItem(item: Transaction, onEditClick: (String) -> Unit, onDeleteClick: (String) -> Unit) {
    Row {
        Text(modifier = Modifier.weight(3f), text = item.name)
        Text(modifier = Modifier.weight(3f), text = item.category_id)
        Text(modifier = Modifier.weight(3f), text = item.user_id)
        Text(modifier = Modifier.weight(3f), text = item.date)
        Text(modifier = Modifier.weight(3f), text = item.amount)
        Text(modifier = Modifier.weight(3f), text = item.description)
        Button(modifier = Modifier.weight(1.5f), onClick = { onEditClick(item.id) }) {
            Image(painterResource(id = R.drawable.baseline_edit_24), contentDescription = "Edit")
        }
        Button(modifier = Modifier.weight(1.5f), onClick = { onDeleteClick(item.id) }) {
            Image(painterResource(id = R.drawable.baseline_delete_24), contentDescription = "Delete")
        }
    }
}