package id.ac.its.izzulhaq.productlistapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.its.izzulhaq.productlistapp.database.DbHelper
import id.ac.its.izzulhaq.productlistapp.models.Product

class ProductListViewModel(private val dbHelper: DbHelper) : ViewModel() {
    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    fun setProductList() {
        val cursor = dbHelper.getAllProduct()
        val list = ArrayList<Product>()

        if (cursor.count > 0) {
            cursor.moveToFirst()
            val name = cursor.getString(cursor.getColumnIndexOrThrow("product_name"))
            val code = cursor.getString(cursor.getColumnIndexOrThrow("product_code"))
            val quantity = cursor.getInt(cursor.getColumnIndexOrThrow("product_quantity"))
            val price = cursor.getInt(cursor.getColumnIndexOrThrow("product_price"))

            list.add(Product(code, name, quantity, price))

            while (cursor.moveToNext()) {
                val product = Product(
                    cursor.getString(cursor.getColumnIndexOrThrow("product_code")),
                    cursor.getString(cursor.getColumnIndexOrThrow("product_name")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("product_quantity")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("product_price"))
                )
                list.add(product)
            }

            _productList.value = list
        }
    }

    fun addProduct(product: Product) {
        dbHelper.addProduct(product)
        setProductList()
    }
}