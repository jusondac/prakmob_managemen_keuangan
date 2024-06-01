package id.ac.unpas.managemen_keuangan.ui.screens.TransactionScreens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import id.ac.unpas.managemen_keuangan.models.Transaction

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
            todoRepository.loadItems(
                onSuccess = {
                    _isLoading.postValue(false)
                },
                onError = {
                    _isLoading.postValue(false)
                    Log.e("TodoViewModel", it)
                }
            ).asLiveData()
        }
    }

    suspend fun insert(id: String,
                       title: String,
                       description: String,
                       dueDate: String) {
        _isLoading.postValue(true)
        todoRepository.insert(Todo(id, title, description, dueDate),
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

    suspend fun update(id: String,
                       title: String,
                       description: String,
                       dueDate: String) {
        _isLoading.postValue(true)
        todoRepository.update(Todo(id, title, description, dueDate),
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

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        todoRepository.delete(id,
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

    suspend fun find(id: String) {
        val todo = todoRepository.find(id)
        todo?.let {
            _item.postValue(it)
        }
    }
}