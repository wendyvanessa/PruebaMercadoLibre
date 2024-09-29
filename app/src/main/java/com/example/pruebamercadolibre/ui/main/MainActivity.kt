package com.example.pruebamercadolibre.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebamercadolibre.databinding.ActivityMainBinding
import com.example.pruebamercadolibre.ui.observe

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        observers()
        mainViewModel.getProductsImp("monitores")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun observers(){
        with(mainViewModel){
            observe(itemsLiveData) { items ->
                Log.v("MyApp", items.joinToString())
            }
        }
    }
}