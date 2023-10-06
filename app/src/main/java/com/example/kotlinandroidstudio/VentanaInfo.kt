package com.example.kotlinandroidstudio

import Auxiliar.InterVentana
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.kotlinandroidstudio.databinding.ActivityInfoBinding
import com.example.kotlinandroidstudio.databinding.ActivityVentanalistaBinding

class VentanaInfo : AppCompatActivity() {

    lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var u  = intent.getSerializableExtra("obj") as Usuario


//            binding.avatarPicInfo = u.foto
        binding.editTextNombreInfo.setText(u.nombre)
        binding.editTextApellidoInfo.setText(u.apellido)
        binding.editTextEdadInfo.setText(u.edad.toString())
        binding.editTextTextDniInfo.setText(u.dni)

// Falta lógica para estos botones
        binding.radioButtonDAMInfo.isChecked = true
        binding.radioButtonPrimeroInfo.isChecked = true


        binding.btnVolverActInfo.setOnClickListener {
            finish()
        }

        binding.btnEditarUsuario.setOnClickListener {

        }

        binding.btnBorrarUsuario.setOnClickListener {
            if(Auxiliar.Conexion.delUsuario(this,u.dni.toString()) > 0){
                Toast.makeText(
                    this,
                    "Borrado con exito ",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                val builder = AlertDialog.Builder(this)
                with(builder)
                {
                    setTitle("Atención")
                    setMessage("Algo fué mal")
                    setPositiveButton(
                        "Aceptar",
                        DialogInterface.OnClickListener(function = Auxiliar.Dialogs.positiveButtonClick)
                    )
                    show()
                }
            }



        }


    }
}