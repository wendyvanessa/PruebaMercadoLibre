package com.example.pruebamercadolibre.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.enableEdgeToEdge
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebamercadolibre.R
import com.example.pruebamercadolibre.databinding.ActivityMainBinding
import com.example.pruebamercadolibre.ui.observe
import com.example.pruebamercadolibre.ui.toast

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    val adapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        EditorActionListener()
        observers()
        binding.recycler.adapter = adapter
        setContentView(binding.root)
    }

    /**
     * Configuración el listener para manejar las acciones en el campo de búsqueda.
     *
     * Cuando el usuario presiona el botón de búsqueda o la tecla "Enter" se verifica si
     * el texto ingresado no está vacío.
     * Si el texto no está vacío se llama al método getProductsImp para realizar la búsqueda.
     * de lo contrario se muestra un mensaje al usuario.
     *
     */
    fun EditorActionListener() {
        binding.customToolbar.editTxtSearch.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || keyEvent?.keyCode == KeyEvent.KEYCODE_ENTER) {

                val query = textView.text.toString()
                if (query.isNotEmpty()) {
                    adapter.items = emptyList()
                    mainViewModel.getProductsImp(query)
                } else {
                    adapter.items = emptyList()
                    toast(getString(R.string.campo_vacio))
                    binding.emptyListView.root.visibility = View.VISIBLE
                }
                true
            } else {
                false
            }
        }
    }

    fun observers() {
        with(mainViewModel) {
            observe(itemsLiveData) { items ->
                adapter.items = items
                binding.emptyListView.root.visibility =
                    if (items.isNullOrEmpty()) View.VISIBLE else View.GONE
            }
            observe(processingLiveData) { processing ->
                if (processing) {
                    binding.progress.visibility = View.VISIBLE
                    binding.emptyListView.root.visibility = View.GONE
                } else {
                    binding.progress.visibility = View.GONE
                }
            }
            observe(errorLiveData) { error ->
                if (error) {
                    toast(getString(R.string.error_api))
                }
            }
        }
    }
}