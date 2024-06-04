package id.ac.unpas.managemen_keuangan.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ac.unpas.managemen_keuangan.persistences.AppDatabase
import id.ac.unpas.managemen_keuangan.persistences.TransactionDao
import id.ac.unpas.managemen_keuangan.persistences.CategoryDao
import id.ac.unpas.managemen_keuangan.persistences.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) : AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(appDatabase: AppDatabase) : TransactionDao {
        return appDatabase.transactionDao()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(appDatabase: AppDatabase) : CategoryDao {
        return appDatabase.categoryDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase) : UserDao {
        return appDatabase.userDao()
    }
}