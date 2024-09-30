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
            val response = service.getProducts(query)
            if (response.isSuccessful) {
                Result.success(response.body())
            }else{
                Log.e("Error", "Error al obtener productos: ${response.code()}")
                Result.failure(Exception("Error al obtener productos: ${response.code()}"))
            }
        }catch (e:Exception){
            Log.e("Error", "Error al obtener productos: ${e.message}")
            Result.failure(e)
        }
    }
}