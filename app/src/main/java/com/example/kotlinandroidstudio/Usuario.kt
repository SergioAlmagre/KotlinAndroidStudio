package com.example.kotlinandroidstudio

import java.io.Serializable

data class Usuario(
    var nombre: String,
    var apellido: String,
    var pass: String,
    var dni: String,
    var edad: Int,
    var curso: Int,
    var anno: Int,
    var foto: String
):Serializable

