package id.ac.unpas.managemen_keuangan.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.junit.runner.Description

@Entity(tableName = "transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val amount: Double,
    val categoryId: String,
    val userId: String,
    val date: Long,
    val description: String,
)
