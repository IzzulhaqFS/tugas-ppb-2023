package id.ac.its.izzulhaq.productlistapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import id.ac.its.izzulhaq.productlistapp.models.Product

class DbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
            CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            id INTEGER PRIMARY KEY,
            product_code TEXT,
            product_name TEXT,
            product_quantity INTEGER,
            product_price INTEGER
            );
        """.trimIndent()

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addProduct(product: Product) {
        val values = ContentValues()

        values.apply {
            put("product_code", product.productCode)
            put("product_name", product.productName)
            put("product_quantity", product.productQuantity)
            put("product_price", product.productPrice)
        }

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllProduct(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun deleteProduct(product: Product) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "product_code='${product.productCode}'", null)
    }

    fun updateProduct(product: Product) {
        val values = ContentValues()

        values.apply {
            put("product_code", product.productCode)
            put("product_name", product.productName)
            put("product_quantity", product.productQuantity)
            put("product_price", product.productPrice)
        }

        val db = this.writableDatabase
        db.update(TABLE_NAME, values, "product_code='${product.productCode}'", null)
    }

    companion object {
        private const val DATABASE_NAME = "db_product"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "product"
    }
}