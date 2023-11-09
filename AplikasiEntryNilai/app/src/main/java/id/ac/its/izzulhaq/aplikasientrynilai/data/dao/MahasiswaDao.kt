package id.ac.its.izzulhaq.aplikasientrynilai.data.dao

import androidx.room.*
import id.ac.its.izzulhaq.aplikasientrynilai.models.Mahasiswa

@Dao
interface MahasiswaDao {
    @Query("SELECT * FROM mahasiswa")
    fun getAll(): List<Mahasiswa>

    @Query("SELECT * FROM mahasiswa WHERE nrp = :nrp")
    fun findByNRP(nrp: String): Mahasiswa

    @Insert
    fun insert(mahasiswa: Mahasiswa)

    @Update
    fun update(mahasiswa: Mahasiswa)

    @Delete
    fun delete(mahasiswa: Mahasiswa)
}