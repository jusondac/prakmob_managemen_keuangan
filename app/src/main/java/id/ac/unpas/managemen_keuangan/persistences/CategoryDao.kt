package id.ac.unpas.managemen_keuangan.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//import id.ac.unpas.agenda.models.Category

@Dao
interface CategoryDao {

    @Query("select * from categories")
    fun loadAll(): LiveData<List<Category>>

    @Query("select * from categories")
    suspend fun findAll(): List<Category>

    @Query("select * from categories where id = :id")
    fun load(id: String): LiveData<Category>

    @Query("select * from categories where id = :id")
    suspend fun getById(id: String): Category?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(list: List<Category>)

    @Query("delete from categories where id = :id")
    suspend fun delete(id: String)

    @Query("select * from categories where id = :id")
    suspend fun find(id: String): Category?

    @Delete
    suspend fun delete(item: Category)
}