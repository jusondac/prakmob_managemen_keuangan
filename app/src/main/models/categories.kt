package id.ac.unpas.managemen_keuangan.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.junit.runner.Description

@Entity(tableName = "categories")
data class Categories(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
)