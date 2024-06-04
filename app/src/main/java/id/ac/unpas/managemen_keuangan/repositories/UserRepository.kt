package id.ac.unpas.managemen_keuangan.repositories

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.managemen_keuangan.models.User
import id.ac.unpas.managemen_keuangan.networks.UserApi
import id.ac.unpas.managemen_keuangan.persistences.UserDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: UserApi, private val dao: UserDao) {

    @WorkerThread
    fun loadItems(onSuccess: () -> Unit,
                  onError: (String) -> Unit) = flow {
        val list: List<User> = dao.findAll()
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

    suspend fun insert(user: User,
                       onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        dao.upsert(user)
        api.insert(user)
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

    suspend fun update(user: User,
                       onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        dao.upsert(user)
        api.update(user.id, user)
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