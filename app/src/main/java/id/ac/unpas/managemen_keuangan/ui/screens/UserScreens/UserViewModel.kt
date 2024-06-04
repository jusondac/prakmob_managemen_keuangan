package id.ac.unpas.managemen_keuangan.ui.screens.UserScreens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.managemen_keuangan.base.LiveCoroutinesViewModel
import id.ac.unpas.managemen_keuangan.models.User
import id.ac.unpas.managemen_keuangan.repositories.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val UserRepository: UserRepository) : LiveCoroutinesViewModel() {

    private val _isDone: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDone: LiveData<Boolean> = _isDone

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _item: MutableLiveData<User> = MutableLiveData()
    val item: LiveData<User> = _item

    private val _user: MutableLiveData<Boolean> = MutableLiveData(false)
    val users: LiveData<List<User>> = _user.switchMap {
        _isLoading.postValue(true)
        launchOnViewModelScope {
            UserRepository.loadItems(
                onSuccess = {
                    _isLoading.postValue(false)
                },
                onError = {
                    _isLoading.postValue(false)
                    Log.e("UserViewModel", it)
                }
            ).asLiveData()
        }
    }

    suspend fun insert(id: String,
                       name: String,
                       email: String,
                       password: String,
                       ) {
        _isLoading.postValue(true)
        UserRepository.insert(
            User(id,name, email,password),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _user.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _user.postValue(true)
            }
        )
    }

    suspend fun update(id: String,
                       name: String,
                       email: String,
                       password: String,
    ) {
        _isLoading.postValue(true)
        UserRepository.update(
            User( id,name, email,password),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _user.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _user.postValue(true)
            }
        )
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        UserRepository.delete(id.toString(),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _user.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _user.postValue(true)
            }
        )
    }

    suspend fun find(id: String) {
        val user = UserRepository.find(id)
        user?.let {
            _item.postValue(it)
        }
    }
}