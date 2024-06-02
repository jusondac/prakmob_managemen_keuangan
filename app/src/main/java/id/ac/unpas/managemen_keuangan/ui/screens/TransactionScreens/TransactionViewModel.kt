package id.ac.unpas.managemen_keuangan.ui.screens.TransactionScreens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import id.ac.unpas.managemen_keuangan.models.Transaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.managemen_keuangan.base.LiveCoroutinesViewModel
import id.ac.unpas.managemen_keuangan.persistences.TransactionDao
import id.ac.unpas.managemen_keuangan.repositories.TransactionRepository
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val TransactionRepository: TransactionRepository) : LiveCoroutinesViewModel() {
    private val _isDone: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDone: LiveData<Boolean> = _isDone

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _item: MutableLiveData<Transaction> = MutableLiveData()
    val item: LiveData<Transaction> = _item

    private val _transaction: MutableLiveData<Boolean> = MutableLiveData(false)
    val transactions : LiveData<List<Transaction>> = _transaction.switchMap {
        _isLoading.postValue(true)
        launchOnViewModelScope {
            TransactionRepository.loadItems(
                onSuccess = {
                    _isLoading.postValue(false)
                },
                onError = {
                    _isLoading.postValue(false)
                    Log.e("TransactionViewModel", it)
                }
            ).asLiveData()
        }
    }

    suspend fun insert(id: String,
                       name: String,
                       category_id: String,
                       user_id: String,
                       date: Long,
                       amount: Double,
                       description: String) {
        _isLoading.postValue(true)
        TransactionRepository.insert(Transaction( id,name, category_id ,user_id, date, amount, description),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _transaction.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _transaction.postValue(true)
            }
        )
    }

    suspend fun update(id: String,
                       name: String,
                       category_id: String,
                       user_id: String,
                       date: String,
                       amount: String,
                       description: String
                       ) {
        _isLoading.postValue(true)
        TransactionRepository.update(Transaction( id,name, category_id ,user_id, date.toLong(), amount.toDouble(), description),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _transaction.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _transaction.postValue(true)
            }
        )
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        TransactionRepository.delete(id.toString(),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _transaction.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _transaction.postValue(true)
            }
        )
    }

    suspend fun find(id: String) {
        val transaction = TransactionRepository.find(id.toString())
        transaction?.let {
            _item.postValue(it)
        }
    }
}