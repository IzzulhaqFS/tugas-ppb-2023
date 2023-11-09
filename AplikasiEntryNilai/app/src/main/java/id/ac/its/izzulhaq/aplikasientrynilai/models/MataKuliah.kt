package id.ac.its.izzulhaq.aplikasientrynilai.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mata_kuliah")
data class MataKuliah(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "nama")
    val nama: String? = null
)
