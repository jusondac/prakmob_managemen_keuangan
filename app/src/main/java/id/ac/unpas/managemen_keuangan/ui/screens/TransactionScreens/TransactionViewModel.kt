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
import TransactionRepository
import javax.inject.Inject

class TransactionViewModel {
    private val _isDone: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDone: LiveData<Boolean> = _isDone

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _item: MutableLiveData<Transaction> = MutableLiveData()
    val item: LiveData<Transaction> = _item

    private val _todo: MutableLiveData<Boolean> = MutableLiveData(false)
    val todos : LiveData<List<Transaction>> = _todo.switchMap {
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

    suspend fun insert(id: Int,
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
                _todo.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            }
        )
    }

    suspend fun update(id: Int,
                       name: String,
                       category_id: String,
                       user_id: String,
                       date: Long,
                       amount: Double,
                       description: String
                       ) {
        _isLoading.postValue(true)
        TransactionRepository.update(Transaction( id,name, category_id ,user_id, date, amount, description),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            }
        )
    }

    suspend fun delete(id: Int) {
        _isLoading.postValue(true)
        TransactionRepository.delete(id,
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            }
        )
    }

    suspend fun find(id: Int) {
        val transaction = TransactionRepository.find(id)
        transaction?.let {
            _item.postValue(it)
        }
    }
}