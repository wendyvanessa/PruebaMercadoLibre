package com.example.pruebamercadolibre.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductItem {
    @SerializedName("title")
    @Expose val title: String?= null

    @SerializedName("price")
    @Expose val price: Double?= null

    @SerializedName("thumbnail")
    @Expose val thumbnail: String?= null

    @SerializedName("installments")
    @Expose val installments: ProductInstallments?= null

    override fun toString(): String {
        return "ProductItem(title='$title', price='$price', thumbnail=$thumbnail,  installments=$installments)\n"
    }
}