package id.ac.unpas.managemen_keuangan.ui.screens.TransactionScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
        Text(modifier = Modifier.weight(3f), text = item.date.toString())
        Text(modifier = Modifier.weight(3f), text = item.amount.toString())
        Text(modifier = Modifier.weight(3f), text = item.description)
        Icon(painterResource(id = R.drawable.baseline_edit_24), "Edit", modifier = Modifier.weight(1.5f).clickable {
            onEditClick(item.id)
        })
        Icon(painterResource(id = R.drawable.baseline_delete_24), "Delete", modifier = Modifier.weight(1.5f).clickable {
            onDeleteClick(item.id)
        })
    }
}