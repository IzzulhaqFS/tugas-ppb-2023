package id.ac.its.izzulhaq.aplikasientrynilai.models

import androidx.room.*

@Entity(tableName = "nilai", primaryKeys = ["idMahasiswa", "idMataKuliah"])
data class Nilai(
    val idMahasiswa: Int,
    val idMataKuliah: Int,

    @ColumnInfo(name = "nilai_angka")
    val nilaiAngka: Double = 0.0,

    @ColumnInfo(name = "nilai_huruf")
    val nilaiHuruf: String? = null
)

//data class MataKuliahBerdasarMahasiswa(
//    @Embedded
//    val mahasiswa: Mahasiswa,
//
//    @Relation(
//        parentColumn = "idMahasiswa",
//        entityColumn = "idMataKuliah",
//        associateBy = Junction(NilaiMataKuliahMahasiswa::class)
//    )
//    val daftarMataKuliah: List<MataKuliah>
//)
//
//data class MahasiswaBerdasarMataKuliah(
//    @Embedded
//    val mataKuliah: MataKuliah,
//
//    @Relation(
//        parentColumn = "idMataKuliah",
//        entityColumn = "idMahasiswa",
//        associateBy = Junction(NilaiMataKuliahMahasiswa::class)
//    )
//    val daftarMahasiswa: List<Mahasiswa>
//)