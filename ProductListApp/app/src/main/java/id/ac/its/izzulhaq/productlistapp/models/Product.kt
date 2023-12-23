package id.ac.its.izzulhaq.productlistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val productCode: String,
    val productName: String,
    val productQuantity: Int = 0,
    val productPrice: Int = 0
) : Parcelable
