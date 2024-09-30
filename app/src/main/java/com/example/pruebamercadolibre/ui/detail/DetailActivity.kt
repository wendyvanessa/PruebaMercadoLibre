package com.example.pruebamercadolibre.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.pruebamercadolibre.R
import com.example.pruebamercadolibre.databinding.ActivityDetailBinding
import com.example.pruebamercadolibre.ui.loadUrl

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding


    companion object{
        val THUMBNAIL = "thumbnail"
        const val TITLE = "title"
        const val PRICE = "price"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDetailBinding.inflate(layoutInflater)
        loadData()
        setContentView(binding.root)

    }

    fun loadData(){
        binding.toolbar.title = intent.getStringExtra(TITLE)
        binding.titleProduct.text = intent.getStringExtra(TITLE)
        binding.price.text = intent.getStringExtra(PRICE).toString()
        binding.imageProduct.loadUrl(intent.getStringExtra(THUMBNAIL).toString())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.primary_color)
        }
    }


}