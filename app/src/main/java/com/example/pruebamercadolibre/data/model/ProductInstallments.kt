package com.example.pruebamercadolibre.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Modelo de datos referente a forma de pago en cuotas.
 */

class ProductInstallments {
    @SerializedName("quantity")
    @Expose val quantity: Int?= null

    @SerializedName("amount")
    @Expose val amount: Double?= null

    @SerializedName("rate")
    @Expose val rate: Double?= null

    override fun toString(): String {
        return "ProductItem(quantity='$quantity', amount='$amount', rate='$rate')\n"
    }
}