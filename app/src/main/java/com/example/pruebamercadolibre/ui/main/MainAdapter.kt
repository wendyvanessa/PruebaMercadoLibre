package com.example.pruebamercadolibre.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebamercadolibre.data.model.ProductItem
import com.example.pruebamercadolibre.databinding.ViewItemBinding
import com.example.pruebamercadolibre.ui.loadUrl

import kotlin.properties.Delegates

class MainAdapter (
    items:List<ProductItem> = emptyList()):
    RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    // Se actualizara la vista cada vez que la lista de items se mencione
    var items:List<ProductItem> by Delegates.observable(items) { _, _, _ ->
        notifyDataSetChanged()
    }

    //Crear una vista cuando el recyclerview se lo pida
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //Inflar la vista
        val binding = ViewItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding.root)
    }

    //Actualización de una vista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        //holder.itemView.setOnClickListener { listener(item) }
    }

    // Obtenemos el número de elementos que tiene el adapter
    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ViewItemBinding.bind(view)

        fun bind(productItem: ProductItem){
            productItem.title?.let { binding.titleProduct.text = it }
            productItem.price?.let { binding.price.text = "$ $it" }
            binding.imageProduct.loadUrl(productItem.thumbnail.toString())
            binding.installments.text = productItem.installments?.let {
                "${it.quantity} cuotas de $ ${it.amount} con ${it.rate}% de interés"
            }
        }
    }
}
