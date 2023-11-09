package id.ac.its.izzulhaq.aplikasientrynilai.data.dao

import androidx.room.*
import id.ac.its.izzulhaq.aplikasientrynilai.models.MataKuliah

@Dao
interface MataKuliahDao {
    @Query("SELECT * FROM mata_kuliah")
    fun getAll(): List<MataKuliah>

    @Query("SELECT * FROM mata_kuliah WHERE nama = :nama")
    fun getByName(nama: String): MataKuliah

    @Insert
    fun insert(mataKuliah: MataKuliah)

    @Update
    fun update(mataKuliah: MataKuliah)

    @Delete
    fun delete(mataKuliah: MataKuliah)
}