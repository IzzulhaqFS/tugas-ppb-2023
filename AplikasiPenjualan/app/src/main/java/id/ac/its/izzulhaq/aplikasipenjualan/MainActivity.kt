package id.ac.its.izzulhaq.aplikasipenjualan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var edtNamaPembeli: EditText
    private lateinit var edtNamaBarang: EditText
    private lateinit var edtJumlahBarang: EditText
    private lateinit var edtHarga: EditText
    private lateinit var edtBayar: EditText
    private lateinit var tvHasilProses: TextView
    private lateinit var btnProses: Button
    private lateinit var btnHapus: Button
    private lateinit var btnKeluar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = TITLE

        edtNamaPembeli = findViewById(R.id.edt_nama_pembeli)
        edtNamaBarang = findViewById(R.id.edt_nama_barang)
        edtJumlahBarang = findViewById(R.id.edt_jumlah_barang)
        edtHarga = findViewById(R.id.edt_harga)
        edtBayar = findViewById(R.id.edt_bayar)
        tvHasilProses = findViewById(R.id.tv_hasil_proses)
        btnProses = findViewById(R.id.btn_proses)
        btnHapus = findViewById(R.id.btn_hapus)
        btnKeluar = findViewById(R.id.btn_keluar)

        btnProses.setOnClickListener {
            proses()
        }

        btnHapus.setOnClickListener {
            hapus()
        }
    }

    private fun proses() {
        val namaPembeli = edtNamaPembeli.text.toString()
        val namaBarang = edtNamaBarang.text.toString()
        val jumlahBarang = edtJumlahBarang.text.toString()
        val harga = edtHarga.text.toString()
        val bayar = edtBayar.text.toString()

        when {
            namaPembeli.isEmpty() -> {
                edtNamaPembeli.error = "Nama Pembeli tidak boleh kosong"
            }
            namaBarang.isEmpty() -> {
                edtNamaBarang.error = "Nama Barang tidak boleh kosong"
            }
            jumlahBarang.isEmpty() -> {
                edtJumlahBarang.error = "Jumlah Barang tidak boleh kosong"
            }
            harga.isEmpty() -> {
                edtHarga.error = "Harga tidak boleh kosong"
            }
            bayar.isEmpty() -> {
                edtBayar.error = "Namanya beli ya harus bayar"
            }
            else -> {
                val totalBelanja = jumlahBarang.toInt() * harga.toInt()
                val kembalian = bayar.toInt() - totalBelanja

                val keterangan = when {
                    kembalian < 0 -> {
                        "Uang bayar kurang"
                    }
                    kembalian == 0 -> {
                        "Uang bayar pas"
                    }
                    else -> {
                        "Menunggu kembalian"
                    }
                }

                val hasil = """
                    Nama Pembeli : $namaPembeli
                    Nama Barang : $namaBarang
                    Jumlah Barang : $jumlahBarang
                    Harga Barang : Rp $harga
                    Uang Bayar : Rp $bayar
                    Total Belanja : Rp $totalBelanja
                    Uang Kembalian : Rp $kembalian
                    Bonus : Harddisk 1TB
                    Keterangan : $keterangan
                """.trimIndent()

                tvHasilProses.text = hasil
            }
        }
    }

    private fun hapus() {
        edtNamaPembeli.text.clear()
        edtNamaBarang.text.clear()
        edtJumlahBarang.text.clear()
        edtHarga.text.clear()
        edtBayar.text.clear()
        tvHasilProses.text = getString(R.string.hasil_placeholder)
    }

    companion object {
        private const val TITLE = "Aplikasi Penjualan"
    }
}