package id.ac.its.izzulhaq.aplikasientrynilai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var edtNRP: EditText
    private lateinit var edtMataKuliah: EditText
    private lateinit var edtTugas: EditText
    private lateinit var edtKuis: EditText
    private lateinit var edtETS: EditText
    private lateinit var edtEAS: EditText
    private lateinit var tvRataRata: TextView
    private lateinit var tvNilai: TextView
    private lateinit var btnSimpan: Button
    private lateinit var btnHapus: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNRP = findViewById(R.id.edt_nrp)
        edtMataKuliah = findViewById(R.id.edt_mata_kuliah)
        edtTugas = findViewById(R.id.edt_nilai_tugas)
        edtKuis = findViewById(R.id.edt_nilai_kuis)
        edtETS = findViewById(R.id.edt_nilai_ets)
        edtEAS = findViewById(R.id.edt_nilai_eas)

        tvRataRata = findViewById(R.id.tv_rata_rata)
        tvNilai = findViewById(R.id.tv_nilai_huruf)

        btnSimpan = findViewById(R.id.btn_simpan)
        btnHapus = findViewById(R.id.btn_hapus)

        btnSimpan.setOnClickListener {
            simpan()
        }

        btnHapus.setOnClickListener {
            hapus()
        }
    }

    private fun simpan() {
        val nrp = edtNRP.text.toString().trim()
        val mataKuliah = edtMataKuliah.text.toString().trim()
        val nilaiTugas = edtTugas.text.toString()
        val nilaiKuis = edtKuis.text.toString()
        val nilaiETS = edtETS.text.toString()
        val nilaiEAS = edtEAS.text.toString()

        val decimalFormat = DecimalFormat("#.##")

        when {
            nrp.isEmpty() -> {
                edtNRP.error = "NRP tidak boleh kosong"
            }
            mataKuliah.isEmpty() -> {
                edtMataKuliah.error = "Mata Kuliah tidak boleh kosong"
            }
            nilaiTugas.isEmpty() -> {
                edtTugas.error = "Nilai Tugas harus diisi"
            }
            nilaiKuis.isEmpty() -> {
                edtKuis.error = "Nilai Kuis harus diisi"
            }
            nilaiETS.isEmpty() -> {
                edtETS.error = "Nilai ETS harus diisi"
            }
            nilaiEAS.isEmpty() -> {
                edtEAS.error = "Nilai EAS harus diisi"
            }
            else -> {
                val rataRata = (nilaiTugas.toDouble() + nilaiKuis.toDouble() + nilaiETS.toDouble() + nilaiEAS.toDouble()) / 4
                val nilai = when {
                    rataRata > 85 -> "A"
                    rataRata > 75 -> "AB"
                    rataRata > 65 -> "B"
                    rataRata > 60 -> "BC"
                    rataRata > 55 -> "C"
                    rataRata > 40 -> "D"
                    rataRata <= 40 -> "E"
                    else -> "Nilai tidak valid"
                }

                tvRataRata.text = decimalFormat.format(rataRata).toString()
                tvNilai.text = nilai

                Toast.makeText(this, "Nilai telah disimpan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hapus() {
        edtNRP.text.clear()
        edtMataKuliah.text.clear()
        edtTugas.text.clear()
        edtKuis.text.clear()
        edtETS.text.clear()
        edtEAS.text.clear()
        tvNilai.text = getString(R.string.nilai_huruf_hint)
        tvRataRata.text = getString(R.string.nilai_hint)

        Toast.makeText(this, "Nilai telah dihapus", Toast.LENGTH_SHORT).show()
    }
}