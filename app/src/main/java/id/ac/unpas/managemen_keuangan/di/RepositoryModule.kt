package id.ac.unpas.managemen_keuangan.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.managemen_keuangan.networks.CategoryApi
import id.ac.unpas.managemen_keuangan.networks.TransactionApi
import id.ac.unpas.managemen_keuangan.persistences.CategoryDao
import id.ac.unpas.managemen_keuangan.persistences.TransactionDao
import id.ac.unpas.managemen_keuangan.repositories.CategoryRepository
import id.ac.unpas.managemen_keuangan.repositories.TransactionRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideTransactionRepository(transactionDao: TransactionDao, transactionApi: TransactionApi): TransactionRepository {
        return TransactionRepository(transactionApi, transactionDao)
    }

    @Provides
    @ViewModelScoped
    fun provideCategoryRepository(categoryDao: CategoryDao, categoryApi: CategoryApi): CategoryRepository {
        return CategoryRepository(categoryApi, categoryDao)
    }
}