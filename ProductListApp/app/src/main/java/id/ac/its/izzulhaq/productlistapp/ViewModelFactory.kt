package id.ac.its.izzulhaq.productlistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.its.izzulhaq.productlistapp.database.DbHelper
import id.ac.its.izzulhaq.productlistapp.viewmodels.AddProductViewModel
import id.ac.its.izzulhaq.productlistapp.viewmodels.ProductListViewModel

class ViewModelFactory(private val dbHelper: DbHelper) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AddProductViewModel::class.java) -> {
                AddProductViewModel(dbHelper) as T
            }
            modelClass.isAssignableFrom(ProductListViewModel::class.java) -> {
                ProductListViewModel(dbHelper) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
            }
        }
    }
}