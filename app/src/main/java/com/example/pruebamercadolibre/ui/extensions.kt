package com.example.pruebamercadolibre.ui

import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide

/**
 * Creación de función de extención sobre ImageView que se llamará "loadUrl"
 */
fun ImageView.loadUrl(url: String){
    Glide.with(this).load(url).into(this)
}

/**
 * @observe es para crear los observadores de los liveData.
 */
fun <T> LifecycleOwner.observe(livedata: LiveData<T>, observer: (T) -> Unit){
    livedata.observe(this, Observer(observer))
}