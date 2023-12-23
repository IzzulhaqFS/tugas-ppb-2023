package id.ac.its.izzulhaq.productlistapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.its.izzulhaq.productlistapp.R
import id.ac.its.izzulhaq.productlistapp.activities.AddProductActivity
import id.ac.its.izzulhaq.productlistapp.models.Product

class ProductListAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvName: TextView
        private val tvCode: TextView
        private val tvQuantity: TextView
        private val tvPrice: TextView

        init {
            tvName = view.findViewById(R.id.tv_item_product_name)
            tvCode = view.findViewById(R.id.tv_item_product_code)
            tvQuantity = view.findViewById(R.id.tv_item_product_quantity)
            tvPrice = view.findViewById(R.id.tv_item_product_price)
        }

        fun bind(product: Product) {
            tvCode.text = product.productCode
            tvName.text = product.productName
            tvPrice.text = product.productPrice.toString()
            tvQuantity.text = product.productQuantity.toString()

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, AddProductActivity::class.java)
                intent.putExtra(AddProductActivity.EXTRA_PRODUCT, product)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}