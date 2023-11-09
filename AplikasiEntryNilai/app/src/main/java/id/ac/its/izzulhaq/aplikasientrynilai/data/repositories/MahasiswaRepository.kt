package id.ac.its.izzulhaq.aplikasientrynilai.data.repositories

import android.app.Application
import id.ac.its.izzulhaq.aplikasientrynilai.data.dao.MahasiswaDao
import id.ac.its.izzulhaq.aplikasientrynilai.data.database.NilaiDatabase
import id.ac.its.izzulhaq.aplikasientrynilai.models.Mahasiswa

class MahasiswaRepository(application: Application) {
    private val mahasiswaDao: MahasiswaDao

    init {
        val db = NilaiDatabase.getInstance(application)
        mahasiswaDao = db.mahasiswaDao()
    }

    fun getAllMahasiswa(): List<Mahasiswa> = mahasiswaDao.getAll()

    fun getMahasiswaByNRP(nrp: String): Mahasiswa = mahasiswaDao.findByNRP(nrp)

    fun insert(mahasiswa: Mahasiswa) = mahasiswaDao.insert(mahasiswa)

    fun update(mahasiswa: Mahasiswa) = mahasiswaDao.update(mahasiswa)

    fun delete(mahasiswa: Mahasiswa) = mahasiswaDao.delete(mahasiswa)
}