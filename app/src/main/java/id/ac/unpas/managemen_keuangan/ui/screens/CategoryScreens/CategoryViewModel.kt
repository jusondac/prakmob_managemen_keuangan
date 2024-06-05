package id.ac.unpas.managemen_keuangan.ui.screens.CategoryScreens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.managemen_keuangan.base.LiveCoroutinesViewModel
import id.ac.unpas.managemen_keuangan.models.Category
import id.ac.unpas.managemen_keuangan.repositories.CategoryRepository
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository) : LiveCoroutinesViewModel() {

    private val _isDone: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDone: LiveData<Boolean> = _isDone

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _item: MutableLiveData<Category> = MutableLiveData()
    val item: LiveData<Category> = _item

    private val _isDeleted: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDeleted: LiveData<Boolean> = _isDeleted

    private val _categories: MutableLiveData<Boolean> = MutableLiveData(false)
    val categories : LiveData<List<Category>> = _categories.switchMap {
        _isLoading.postValue(true)
        launchOnViewModelScope {
            categoryRepository.loadItems(
                onSuccess = {
                    _isLoading.postValue(false)
                },
                onError = {
                    _isLoading.postValue(false)
                    Log.e("CategoryViewModel", it)
                }
            ).asLiveData()
        }
    }

    suspend fun insert(id: String,
                       name: String,
                       description: String) {
        _isLoading.postValue(true)
        categoryRepository.insert(Category(id, name, description),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _categories.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _categories.postValue(true)
            }
        )
    }

    suspend fun update(id: String,
                       name: String,
                       description: String) {
        _isLoading.postValue(true)
        categoryRepository.update(Category(id, name, description),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _categories.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _categories.postValue(true)
            }
        )
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        categoryRepository.delete(id,
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _categories.postValue(true)
                _isDeleted.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _categories.postValue(true)
                _isDeleted.postValue(false)
            }
        )
    }

    suspend fun find(id: String) {
        val category = categoryRepository.find(id)
        category?.let {
            _item.postValue(it)
        }
    }
}