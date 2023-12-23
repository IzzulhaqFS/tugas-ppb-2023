package id.ac.its.izzulhaq.productlistapp.activities

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import id.ac.its.izzulhaq.productlistapp.R
import id.ac.its.izzulhaq.productlistapp.ViewModelFactory
import id.ac.its.izzulhaq.productlistapp.database.DbHelper
import id.ac.its.izzulhaq.productlistapp.models.Product
import id.ac.its.izzulhaq.productlistapp.viewmodels.AddProductViewModel

class AddProductActivity : AppCompatActivity() {
    private lateinit var edtProductCode: EditText
    private lateinit var edtProductName: EditText
    private lateinit var edtProductQuantity: EditText
    private lateinit var edtProductPrice: EditText
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button
    private lateinit var viewModel: AddProductViewModel
    private lateinit var dbHelper: DbHelper

    private var product: Product? = null
    private var isUpdateForm = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        supportActionBar?.title = TITLE

        dbHelper = DbHelper(this, null)

        viewModel = ViewModelProvider(this, ViewModelFactory(dbHelper))[AddProductViewModel::class.java]

        edtProductCode = findViewById(R.id.edt_product_code)
        edtProductName = findViewById(R.id.edt_product_name)
        edtProductQuantity = findViewById(R.id.edt_product_quantity)
        edtProductPrice = findViewById(R.id.edt_product_price)
        btnSave = findViewById(R.id.btn_save)
        btnDelete = findViewById(R.id.btn_delete)

        product = when {
            SDK_INT >= 33 -> intent.getParcelableExtra(EXTRA_PRODUCT, Product::class.java)
            SDK_INT < 33 -> @Suppress("DEPRECATION") intent.getParcelableExtra(EXTRA_PRODUCT)
            else -> null
        }

        if (product != null) {
            isUpdateForm = true
            showProductData(product!!)
        }

        btnSave.setOnClickListener {
            saveRecord()
        }

        btnDelete.setOnClickListener {
            deleteRecord()
        }
    }

    private fun saveRecord() {
        val code = edtProductCode.text.toString()
        val name = edtProductName.text.toString()
        val quantity = edtProductQuantity.text.toString().toInt()
        val price = edtProductPrice.text.toString().toInt()

        when {
            code.isEmpty() -> {
                edtProductCode.error = "Kode Produk harus diisi"
            }
            name.isEmpty() -> {
                edtProductName.error = "Nama Produk harus diisi"
            }
            else -> {
                val product = Product(code, name, quantity, price)
                if (isUpdateForm) viewModel.updateProduct(product) else viewModel.addProduct(product)
            }
        }
    }

    private fun deleteRecord() {
        if (!isUpdateForm) {
            Toast.makeText(this, "Aksi tidak dapat dilakukan", Toast.LENGTH_SHORT).show()
        }
        else {
            product?.let { viewModel.deleteProduct(it) }
            Toast.makeText(this, "Produk dihapus", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showProductData(product: Product) {
        edtProductCode.setText(product.productCode)
        edtProductName.setText(product.productName)
        edtProductQuantity.setText(product.productQuantity.toString())
        edtProductPrice.setText(product.productPrice.toString())
    }

    companion object {
        private const val TITLE = "Form Tambah Produk"
        const val EXTRA_PRODUCT = "extra_product"
    }
}