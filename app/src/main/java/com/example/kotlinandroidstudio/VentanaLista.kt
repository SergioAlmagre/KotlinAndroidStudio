package com.example.kotlinandroidstudio

import Adaptadores.MiAdaptadorRecycler
import Auxiliar.InterVentana
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinandroidstudio.databinding.ActivityVentanalistaBinding

class VentanaLista : AppCompatActivity() {
    lateinit var miRecyclerView : RecyclerView
    lateinit var binding: ActivityVentanalistaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVentanalistaBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var f = intent.getSerializableExtra("formularioObj") as Usuario


        InterVentana.usuariosIV = Auxiliar.Conexion.obtenerUsuarios(this) // no me queda otra que cargar desde aquí la base de datos

        miRecyclerView = binding.listaUsuarios as RecyclerView
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager = LinearLayoutManager(this)

        var miAdapter = MiAdaptadorRecycler(InterVentana.usuariosIV, this)
        miRecyclerView.adapter = miAdapter



        binding.btnVolver.setOnClickListener{
            finish()
        }

    }
}
