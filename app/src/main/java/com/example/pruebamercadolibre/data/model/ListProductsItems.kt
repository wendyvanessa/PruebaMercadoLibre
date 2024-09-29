package com.example.pruebamercadolibre.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListProductsItems {
    @SerializedName("results")
    @Expose val results: List<ProductItem>?= null
}