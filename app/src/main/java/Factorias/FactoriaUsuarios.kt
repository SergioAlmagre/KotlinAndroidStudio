package Factorias

import com.example.kotlinandroidstudio.Usuario
import kotlin.random.Random

object FactoriaUsuarios {
    fun generaUsuario() : Usuario {
        val nombres = listOf<String>("Juan", "María", "Carlos", "Ana", "Luis", "Laura", "David", "Elena")
        val apellidos = listOf(
            "López", "Martínez", "González", "Rodríguez", "Pérez",
            "Fernández", "García", "Hernández", "Díaz", "Moreno",
            "Sánchez", "Torres", "Ramírez", "Romero", "Cruz",
            "Ruiz", "Ortega", "Morales", "Vargas", "Castillo"
        )
        val dniList = listOf(
            "12345678A",
            "23456789B",
            "34567890C",
            "45678901D",
            "56789012E",
            "67890123F",
            "78901234G",
            "89012345H",
            "90123456I",
            "01234567J",
            "12345678K")

        val edad = Random.nextInt(16,56)
        var ceroUno = Random.nextInt(0,1)
        var nombreUsuario = nombres[(nombres.indices).random()]
        var apellido = apellidos[(nombres.indices).random()]
        var dni = dniList[(dniList.indices).random()]
        var u = Usuario(nombreUsuario, apellidos[(apellidos.indices).random()],"1234",dni, edad, ceroUno, ceroUno, getImagen())
        return u
    }

    fun getImagen():String{
        var imagenes = listOf<String>("https://loremflickr.com/g/320/240/paris","https://loremflickr.com/320/240/dog","https://loremflickr.com/320/240","https://loremflickr.com/320/240/brazil,rio", "https://loremflickr.com/320/240/paris,girl/all");
        var imagen = imagenes[(imagenes.indices).random()]
        return imagen
    }

}