package id.ac.unpas.managemen_keuangan.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class Transaction(
    //@PrimaryKey(autoGenerate = true)
    val id: String,
    val name: String,
    val category_id: String,
    val user_id: String,
    val date: Long,
    val amount: Double,
    val description: String,
)
