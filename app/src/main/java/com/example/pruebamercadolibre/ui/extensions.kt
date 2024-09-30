package com.example.pruebamercadolibre.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide


/**
 * @length Si en el llamado de la función no se pasa este parametro, utilizara por defecto
 * Toast.LENGTH_SHORT
 */
fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,message,length).show()
}

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


inline fun <reified T : Activity> Context.startActivity(vararg pairs: Pair<String, Any?>) {
    val intent = Intent(this, T::class.java)
    val bundle = bundleOf(*pairs)
    intent.putExtras(bundle)
    startActivity(intent)
}