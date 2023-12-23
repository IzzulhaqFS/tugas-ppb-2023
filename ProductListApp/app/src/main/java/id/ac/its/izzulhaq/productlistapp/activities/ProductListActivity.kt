package id.ac.its.izzulhaq.productlistapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.its.izzulhaq.productlistapp.R
import id.ac.its.izzulhaq.productlistapp.ViewModelFactory
import id.ac.its.izzulhaq.productlistapp.adapters.ProductListAdapter
import id.ac.its.izzulhaq.productlistapp.database.DbHelper
import id.ac.its.izzulhaq.productlistapp.models.Product
import id.ac.its.izzulhaq.productlistapp.viewmodels.ProductListViewModel

class ProductListActivity : AppCompatActivity() {
    private lateinit var rvProduct: RecyclerView
    private lateinit var fabAddProduct: FloatingActionButton
    private lateinit var tvListEmpty: TextView
    private lateinit var viewModel: ProductListViewModel
    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        supportActionBar?.title = TITLE

        dbHelper = DbHelper(this, null)

        viewModel = ViewModelProvider(this, ViewModelFactory(dbHelper))[ProductListViewModel::class.java]
        viewModel.setProductList()

        rvProduct = findViewById(R.id.rv_product)
        fabAddProduct = findViewById(R.id.fab_add_product)
        tvListEmpty = findViewById(R.id.tv_list_empty)

        viewModel.productList.observe(this) {
            showProductList(it)
        }

        fabAddProduct.setOnClickListener {
            addItemDialog()
        }
    }

    private fun showProductList(list: List<Product>) {
        if (list.isEmpty()) {
            tvListEmpty.visibility = View.VISIBLE
        }
        else {
            tvListEmpty.visibility = View.INVISIBLE
        }

        val adapter = ProductListAdapter(list)
        rvProduct.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ProductListActivity)
            setAdapter(adapter)
        }
    }

    private fun addItemDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(TITLE_DIALOG)

        val vAdd = LayoutInflater.from(this).inflate(R.layout.input_dialog, null)

        val edCode = vAdd.findViewById<EditText>(R.id.input_dialog_edt_code)
        val edName = vAdd.findViewById<EditText>(R.id.input_dialog_edt_name)
        val edQuantity = vAdd.findViewById<EditText>(R.id.input_dialog_edt_quantity)
        val edPrice = vAdd.findViewById<EditText>(R.id.input_dialog_edt_price)

        alertDialog.setView(vAdd)
        alertDialog.setPositiveButton("Save") { dialogInterface, _ ->
            val code = edCode.text.toString()
            val name = edName.text.toString()
            val quantity = edQuantity.text.toString().toInt()
            val price = edPrice.text.toString().toInt()

            when {
                code.isEmpty() -> edCode.error = "Tidak boleh kosong"
                name.isEmpty() -> edName.error = "Tidak boleh kosong"
                else -> {
                    viewModel.addProduct(Product(code, name, quantity, price))
                    Toast.makeText(this, "Produk ditambahkan", Toast.LENGTH_SHORT).show()
                    dialogInterface.dismiss()
                }
            }
        }
        alertDialog.setNegativeButton("Batal") { dialogInterface, _ ->
            dialogInterface.cancel()
        }
        alertDialog.show()
    }

    companion object {
        private const val TITLE = "Daftar Produk"
        private const val TITLE_DIALOG = "Tambah Produk"
    }
}