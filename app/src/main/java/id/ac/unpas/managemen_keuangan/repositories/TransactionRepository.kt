package id.ac.unpas.managemen_keuangan.repositories

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIf
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.managemen_keuangan.models.Transaction
import id.ac.unpas.managemen_keuangan.networks.TransactionApi
import id.ac.unpas.managemen_keuangan.persistences.TransactionDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val api: TransactionApi, private val dao: TransactionDao) {

    @WorkerThread
    fun loadItems(onSuccess: () -> Unit,
                  onError: (String) -> Unit) = flow {
        val list: List<Transaction> = dao.findAll()
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

    suspend fun insert(transaction: Transaction,
                       onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        dao.upsert(transaction)
        api.insert(transaction)
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

    suspend fun update(transaction: Transaction,
                       onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        dao.upsert(transaction)
        api.update(transaction.id, transaction)
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
}