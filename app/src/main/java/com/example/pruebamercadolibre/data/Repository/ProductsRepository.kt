package com.example.pruebamercadolibre.data.Repository

import android.util.Log
import com.example.pruebamercadolibre.data.RetrofitServiceFactoy
import com.example.pruebamercadolibre.data.WebService
import com.example.pruebamercadolibre.data.model.ListProductsItems

/**
 * ProductsRepository implementa el consumo de endpoints de la API
 */
class ProductsRepository {

    val service: WebService = RetrofitServiceFactoy.makeWebService()

    /**
     * Realiza una búsqueda de productos en la API.
     * maneja los errores
     */
    suspend fun getProducts(query: String) : Result<ListProductsItems?> {
        return try{
            Result.success(service.getProducts(query).body())
        }catch (e:Exception){
            Log.e("Error", "Error al obtener productos: ${e.message}")
            e.printStackTrace()
            Result.failure(e)
        }
    }
}