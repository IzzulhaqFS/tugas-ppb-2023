package id.ac.its.izzulhaq.productlistapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import id.ac.its.izzulhaq.productlistapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var btnProductList: Button
    private lateinit var btnAddProduct: Button
    private lateinit var btnSearchProduct: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        btnProductList = findViewById(R.id.btn_product_list)
        btnAddProduct = findViewById(R.id.btn_add_product)
        btnSearchProduct = findViewById(R.id.btn_search_product)

        btnProductList.setOnClickListener {
            goToProductList()
        }

        btnAddProduct.setOnClickListener {
            goToNewProductForm()
        }

        btnSearchProduct.setOnClickListener {
            goToSearch()
        }
    }

    private fun goToProductList() {
        val intent = Intent(this@MainActivity, ProductListActivity::class.java)
        startActivity(intent)
    }

    private fun goToNewProductForm() {
        val intent = Intent(this@MainActivity, AddProductActivity::class.java)
        startActivity(intent)
    }

    private fun goToSearch() {

    }
}