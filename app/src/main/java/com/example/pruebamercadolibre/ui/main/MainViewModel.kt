package com.example.pruebamercadolibre.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebamercadolibre.data.Repository.ProductsRepository
import com.example.pruebamercadolibre.data.model.ProductItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val productsRepository: ProductsRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

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
            withContext(ioDispatcher) {
                productsRepository.getProducts(query)
                    .onSuccess {
                        if (it?.results != null) {
                            _itemsLiveData.postValue(it.results)
                        }
                        _errorLiveData.postValue(false)
                        _processingLiveData.postValue(false)
                    }.onFailure {
                        _errorLiveData.postValue(true)
                        _processingLiveData.postValue(false)
                    }
            }
        }
    }
}