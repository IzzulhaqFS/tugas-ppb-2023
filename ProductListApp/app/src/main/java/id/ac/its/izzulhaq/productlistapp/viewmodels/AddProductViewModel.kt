package id.ac.its.izzulhaq.productlistapp.viewmodels

import androidx.lifecycle.ViewModel
import id.ac.its.izzulhaq.productlistapp.database.DbHelper
import id.ac.its.izzulhaq.productlistapp.models.Product

class AddProductViewModel(private val dbHelper: DbHelper) : ViewModel() {
    fun addProduct(product: Product) {
        dbHelper.addProduct(product)
    }

    fun updateProduct(product: Product) {
        dbHelper.updateProduct(product)
    }

    fun deleteProduct(product: Product) {
        dbHelper.deleteProduct(product)
    }
}