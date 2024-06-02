package id.ac.unpas.managemen_keuangan.repositories

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIf
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.managemen_keuangan.models.Category
import id.ac.unpas.managemen_keuangan.networks.CategoryApi
import id.ac.unpas.managemen_keuangan.persistences.CategoryDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val api: CategoryApi, private val dao: CategoryDao) {

    @WorkerThread
    fun loadItems(onSuccess: () -> Unit,
                  onError: (String) -> Unit) = flow {
        val list: List<Category> = dao.findAll()
        api.findAll()
            .suspendOnSuccess {
                data.whatIfNotNull {
                    dao.upsert(it.data)
                    val localList = dao.findAll()
                    emit(localList)
                    onSuccess()
                }
            }
            .suspendOnError {
                emit(list)
                onError(message())
            }
            .suspendOnException {
                emit(list)
                onError(message())
            }
    }

    suspend fun insert(category: Category,
                       onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        dao.upsert(category)
        api.insert(category)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    if (it.success) {
                        onSuccess()
                    } else {
                        onError(it.message)
                    }
                }
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun update(category: Category,
                       onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        dao.upsert(category)
        api.update(category.id, category)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    if (it.success) {
                        onSuccess()
                    } else {
                        onError(it.message)
                    }
                }
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun delete(id: String,
                       onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        dao.delete(id)
        api.delete(id)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    if (it.success) {
                        onSuccess()
                    } else {
                        onError(it.message)
                    }
                }
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }
    suspend fun find(id: String) = dao.find(id)
}