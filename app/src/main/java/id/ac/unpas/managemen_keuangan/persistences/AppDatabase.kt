package id.ac.unpas.managemen_keuangan.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.managemen_keuangan.models.Category
import id.ac.unpas.managemen_keuangan.models.Transaction
import id.ac.unpas.managemen_keuangan.models.User

@Database(entities = [
    Category::class,
    Transaction::class,
    User::class
], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "agenda-database"
    }
}