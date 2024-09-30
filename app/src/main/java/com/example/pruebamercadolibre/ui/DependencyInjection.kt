package com.example.pruebamercadolibre.ui

import android.app.Application
import com.example.pruebamercadolibre.data.Repository.ProductsRepository
import com.example.pruebamercadolibre.ui.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class DependencyInjection: Application() {

    // Para el manejo de las corrutinas
    val ioDispatcher = "ioDispatcher"
    val mainDispatcher = "mainDispatcher"

    /**
     * Creación de la dependencia unica para el repositorio
     */
    val appModule = module {
        single{ProductsRepository()}
        single(named(ioDispatcher)) { Dispatchers.IO }
    }

    /**
     * Se realiza inyección del viewmodel con el repositorio como dependencia.
     */
    val scopeModule = module {
        viewModel { MainViewModel(get(), get(named(ioDispatcher))) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@DependencyInjection)
            modules(appModule, scopeModule)
        }
    }
}