package com.example.pruebamercadolibre.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductInstallments {
    @SerializedName("quantity")
    @Expose val quantity: Int?= null

    @SerializedName("amount")
    @Expose val amount: Double?= null

    override fun toString(): String {
        return "ProductItem(quantity='$quantity', amount='$amount')\n"
    }
}