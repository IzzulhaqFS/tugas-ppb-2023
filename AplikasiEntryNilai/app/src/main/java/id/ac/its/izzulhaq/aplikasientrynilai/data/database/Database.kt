package id.ac.its.izzulhaq.aplikasientrynilai.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.its.izzulhaq.aplikasientrynilai.data.dao.MahasiswaDao
import id.ac.its.izzulhaq.aplikasientrynilai.data.dao.MataKuliahDao
import id.ac.its.izzulhaq.aplikasientrynilai.data.dao.NilaiDao
import id.ac.its.izzulhaq.aplikasientrynilai.models.Mahasiswa
import id.ac.its.izzulhaq.aplikasientrynilai.models.MataKuliah
import id.ac.its.izzulhaq.aplikasientrynilai.models.Nilai
import kotlin.concurrent.Volatile

@Database(entities = [Mahasiswa::class, MataKuliah::class, Nilai::class], version = 1)
abstract class NilaiDatabase : RoomDatabase() {
    abstract fun mahasiswaDao(): MahasiswaDao
    abstract fun mataKuliahDao(): MataKuliahDao
    abstract fun nilaiDao(): NilaiDao

    companion object {
        @Volatile
        private var INSTANCE: NilaiDatabase? = null
        fun getInstance(context: Context): NilaiDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, NilaiDatabase::class.java, "nilai_database")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}