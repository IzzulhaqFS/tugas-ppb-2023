package id.ac.its.izzulhaq.aplikasientrynilai.data.dao

import androidx.room.*
import id.ac.its.izzulhaq.aplikasientrynilai.models.Mahasiswa
import id.ac.its.izzulhaq.aplikasientrynilai.models.Nilai

@Dao
interface NilaiDao {
    @Query("SELECT * FROM nilai")
    fun getAll(): List<Nilai>

    @Query("SELECT * FROM nilai WHERE idMahasiswa = :idMahasiswa")
    fun getByMahasiswa(idMahasiswa: Int): List<Nilai>

    @Query("SELECT * FROM nilai WHERE idMataKuliah = :idMataKuliah")
    fun getByMataKuliah(idMataKuliah: Int): List<Nilai>

    @Query("SELECT * FROM nilai WHERE idMahasiswa = :idMahasiswa AND idMataKuliah = :idMataKuliah")
    fun getByMahasiswaDanMataKuliah(idMahasiswa: Int, idMataKuliah: Int): Nilai

    @Insert
    fun insert(nilai: Nilai)

    @Update
    fun update(nilai: Nilai)

    @Delete
    fun delete(nilai: Nilai)
}