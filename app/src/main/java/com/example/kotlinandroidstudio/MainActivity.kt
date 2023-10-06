package com.example.kotlinandroidstudio

import Factorias.FactoriaUsuarios
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.iterator
import com.bumptech.glide.Glide
import com.example.kotlinandroidstudio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    var curso: Int = 0
    var anno: Int = 0
    var goteo: Boolean = false
    var oxigeno: Boolean = false
    var rutaFoto: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonRegistrar.setOnClickListener {
            if (!binding.chkTerminosMain.isChecked) {
                val builder = AlertDialog.Builder(this)
                with(builder)
                {
                    setTitle("Atención")
                    setMessage("Primero debe aceptar las condiciones")
                    setPositiveButton(
                        "Aceptar",
                        DialogInterface.OnClickListener(function = Auxiliar.Dialogs.positiveButtonClick)
                    )
                    show()
                }
            } else {
                var inte: Intent = Intent(this, VentanaLista::class.java)
                var u = registrarUsuario()

                Auxiliar.Conexion.addUsuario(this,u) // directamente meto el usuario en la base de datos
                Log.e("Sergio","usuario insertaddo ${u}")

                Toast.makeText(
                    this,
                    "Registrado ${binding.editTextNombre.text} , ${binding.editTextApellido.text}  ",
                    Toast.LENGTH_SHORT
                ).show()

//                inte.putExtra("formularioObj", u)
                startActivity(inte)
            }
        }


        binding.buttonSubirFoto.setOnClickListener {
            Glide.with(this).load(FactoriaUsuarios.getImagen()).into(binding.avatarPicMain)
            Toast.makeText(
                this,
                "Foto subida!",
                Toast.LENGTH_SHORT
            ).show()
            Log.e("Sergio","has pulsado el botón subir foto")
        }


        binding.btnVerRegistros.setOnClickListener {
            var inte: Intent = Intent(this, VentanaLista::class.java)
            startActivity(inte)
            finish()
        }
    }


    fun registrarUsuario():Usuario {
            /**
             * 0 - DAM
             * 1 - DAW
             */
            curso = 0
            if (binding.radioButtonDAW.isChecked) {
                curso = 1
            }
            // esto no tiene sentido con solo 2 opciones pero lo dejo preparado para mas en un mismo grupo
            /**
             * 1 - primero
             * 2 - segundo
             */
            anno = 1
            var index: Int = 0

            for (a in binding.radioGroupAnno) {
                index++
                if (a.isEnabled) {
                    anno = index
                }
            }

            // falta implementar cuadro de dialogo para seleccionar ruta
            rutaFoto = FactoriaUsuarios.getImagen()
            var edad = 0

            try{
                edad = binding.editTextEdadMain.text.toString().toInt()
            }catch (io:Exception){
                println(io.message + "fallo en edad")
            }

            var newUsuario = Usuario(
                binding.editTextNombre.text.toString(),
                binding.editTextApellido.text.toString(),
                binding.editTextTextPassword.text.toString(),
                binding.editTextTextDniMain.text.toString(),
                edad,
                curso,
                anno,
                rutaFoto
            )
        return newUsuario
        }

}
