package id.ac.unpas.managemen_keuangan.ui.screens.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import id.ac.unpas.managemen_keuangan.R
import id.ac.unpas.managemen_keuangan.models.Category

@Composable
fun CategoryItem(item: Category, onEditClick: (String) -> Unit, onDeleteClick: (String) -> Unit) {
    Row {
        Text(modifier = Modifier.weight(3f), text = item.name)
        Text(modifier = Modifier.weight(3f), text = item.description)
        Icon(painterResource(id = R.drawable.baseline_edit_24), "Edit", modifier = Modifier.weight(1.5f).clickable {
            onEditClick(item.id)
        })
        Icon(painterResource(id = R.drawable.baseline_delete_24), "Delete", modifier = Modifier.weight(1.5f).clickable {
            onDeleteClick(item.id)
        })
    }
}