package id.ac.unpas.managemen_keuangan.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.managemen_keuangan.models.Category
import id.ac.unpas.managemen_keuangan.models.Transaction

@Database(entities = [
    Category::class,
    Transaction::class
], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        const val DATABASE_NAME = "agenda-database"
    }
}