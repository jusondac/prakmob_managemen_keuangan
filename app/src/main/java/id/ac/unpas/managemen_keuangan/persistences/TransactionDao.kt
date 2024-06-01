package id.ac.unpas.managemen_keuangan.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//import id.ac.unpas.agenda.models.Transaction

@Dao
interface TransactionDao {

    @Query("select * from transactions")
    fun loadAll(): LiveData<List<Transaction>>

    @Query("select * from transactions")
    suspend fun findAll(): List<Transaction>

    @Query("select * from transactions where id = :id")
    fun load(id: String): LiveData<Transaction>

    @Query("select * from transactions where id = :id")
    suspend fun getById(id: String): Transaction?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: Transaction)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(list: List<Transaction>)

    @Query("delete from transactions where id = :id")
    suspend fun delete(id: String)

    @Query("select * from transactions where id = :id")
    suspend fun find(id: String): Transaction?

    @Delete
    suspend fun delete(item: Transaction)
}