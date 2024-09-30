package com.example.pruebamercadolibre.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebamercadolibre.data.Repository.ProductsRepository
import com.example.pruebamercadolibre.data.model.ProductItem
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    val productsRepository = ProductsRepository()

    private val _itemsLiveData = MutableLiveData<List<ProductItem>>()
    val itemsLiveData: LiveData<List<ProductItem>> get() = _itemsLiveData

    private val _processingLiveData = MutableLiveData<Boolean>()
    val processingLiveData: LiveData<Boolean> get() = _processingLiveData

    private val _errorLiveData = MutableLiveData<Boolean>()
    val errorLiveData: LiveData<Boolean> get() = _errorLiveData

    /**
     * Obtiene los datos
     * @getProductsImp llama al repositorio para obtener los datos y actualiza los LiveData.
     * @param [query] La cadena de búsqueda que se utilizará para obtener los productos.
     */
    fun getProductsImp(query: String) {
        viewModelScope.launch {

            _processingLiveData.value = true
            productsRepository.getProducts(query)
                .onSuccess {
                    if ( it?.results != null){
                        _itemsLiveData.value = it.results
                    }
                    _processingLiveData.value = false
                }.onFailure {
                    _errorLiveData.value = true
                    _processingLiveData.value = false
                }
        }
    }
}