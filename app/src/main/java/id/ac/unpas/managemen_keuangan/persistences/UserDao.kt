package id.ac.unpas.managemen_keuangan.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.unpas.managemen_keuangan.models.User

@Dao
interface UserDao {
    @Query("select * from users")
    fun loadAll(): LiveData<List<User>>

    @Query("select * from users")
    suspend fun findAll(): List<User>

    @Query("select * from users where id = :id")
    fun load(id: String): LiveData<User>

    @Query("select * from users where id = :id")
    suspend fun getById(id: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(list: List<User>)

    @Query("delete from users where id = :id")
    suspend fun delete(id: String)

    @Query("select * from users where id = :id")
    suspend fun find(id: String): User?

    @Delete
    suspend fun delete(item: User)
}