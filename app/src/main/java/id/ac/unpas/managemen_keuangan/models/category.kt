package id.ac.unpas.managemen_keuangan.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val name: String,
    val description: String,
)