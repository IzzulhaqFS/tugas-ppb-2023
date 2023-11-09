package id.ac.its.izzulhaq.aplikasientrynilai.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mahasiswa")
data class Mahasiswa(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "nrp")
    val nrp: String? = null,

    @ColumnInfo(name = "nama")
    val nama: String? = null
)
