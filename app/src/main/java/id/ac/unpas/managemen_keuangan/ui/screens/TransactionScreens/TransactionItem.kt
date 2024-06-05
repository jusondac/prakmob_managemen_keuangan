package id.ac.unpas.managemen_keuangan.ui.screens.TransactionScreens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.ac.unpas.managemen_keuangan.R
import id.ac.unpas.managemen_keuangan.models.Transaction

@Composable
fun TransactionItem(item: Transaction, onEditClick: (String) -> Unit, onDeleteClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Magenta, shape = RoundedCornerShape(15.dp))
    ) {
        Row(Modifier.padding(7.dp)) {
            Column(modifier = Modifier.weight(0.3f)) {
                Text(text = "Nama" )
                Text(text = "Kategori"  )
                Text(text = "User_id" )
                Text(text = "Tanggal"  )
                Text(text = "Jumlah" )
                Text(text = "Deskripsi"  )
            }
            Column(modifier = Modifier.weight(0.1f)) {
                Text(text = ":" )
                Text(text = ":"  )
                Text(text = ":" )
                Text(text = ":"  )
                Text(text = ":" )
                Text(text = ":"  )
            }
            Column(modifier = Modifier.weight(0.7f)) {
                Text(text = item.name)
                Text(text = item.category_id)
                Text(text = item.user_id)
                Text(text = item.date.toString())
                Text(text = item.amount.toString())
                Text(text = item.description)
            }

            Row(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.baseline_edit_24),
                    "Edit",
                    modifier = Modifier
                        .clickable {
                            onEditClick(item.id)
                        })
                Icon(
                    painterResource(id = R.drawable.baseline_delete_24),
                    "Delete",
                    modifier = Modifier
                        .clickable {
                            onDeleteClick(item.id)
                        })
            }
        }
    }
}