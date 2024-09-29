package com.example.pruebamercadolibre.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/***
 * Implementación de patrón singleton, para que solo exista una instancia del objeto RetrofitServiceFactoy
 */
object RetrofitServiceFactoy {
    /**
     * Crea una instancia de [WebService].
     * @return Una instancia de [WebService] configurada con la URL base y el convertidor de Gson.
     */
    fun makeWebService(): WebService = Retrofit.Builder()
        .baseUrl("https://api.mercadolibre.com/sites/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(WebService::class.java)
}