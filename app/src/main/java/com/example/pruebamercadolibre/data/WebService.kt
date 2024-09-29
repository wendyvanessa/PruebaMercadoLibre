package com.example.pruebamercadolibre.data

import com.example.pruebamercadolibre.data.model.ListProductsItems
import com.example.pruebamercadolibre.data.model.ProductItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interfaz que define el servicio web para realizar búsquedas de productos.
 */
interface WebService {

    /**
     * Realiza una búsqueda de productos en la API.
     *
     * @param busqueda La cadena de búsqueda que se utilizará para obtener los productos.
     * @return [Response] contiene una lista de productos ([ListProductsItems]) que coinciden con la búsqueda.
     */
    @GET("MLA/search")
    suspend fun getProducts(
        @Query("q") busqueda:String
    ): Response<ListProductsItems>
}