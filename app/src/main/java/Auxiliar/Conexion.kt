package Auxiliar

import Conexion.AdminSQLIteConexion
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.kotlinandroidstudio.Usuario

object Conexion {
    var nombreBD = "administracion.db3"

    fun cambiarBD(nombreBD:String){
        this.nombreBD = nombreBD
    }

    fun addUsuario(contexto: Context, u: Usuario){
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
            registro.put("nombre",u.nombre)
            registro.put("apellido",u.apellido)
            registro.put("pass",u.pass)
            registro.put("dni", u.dni)
            registro.put("edad", u.edad)
            registro.put("curso",u.curso)
            registro.put("anno",u.anno)
            registro.put("foto",u.foto)
        bd.insert("usuarios", null, registro)
        bd.close()
        Log.e("Sergio","Add Usuario")
    }

    fun delUsuario(contexto: Context, dni: String):Int{
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val cant = bd.delete("usuarios", "dni='${dni}'", null)
        bd.close()
        return cant
    }

    fun modUsuario(contexto:Context, dni:String, u: Usuario):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
            registro.put("nombre",u.nombre)
            registro.put("apellido",u.apellido)
            registro.put("pass",u.pass)
            registro.put("dni", u.dni)
            registro.put("edad", u.edad)
            registro.put("curso",u.curso)
            registro.put("anno",u.anno)
            registro.put("foto",u.foto)
        val cant = bd.update("usuarios", registro, "dni='${dni}'", null)
        bd.close()
        return cant
    }

    fun buscarUsuario(contexto: Context, dni:String): Usuario? {
        var u:Usuario? = null
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery(
            "select * from usuarios where dni='${dni}'",
            null
        )
        if (fila.moveToFirst()) {
            u = Usuario(
                fila.getString(0),
                fila.getString(1),
                fila.getString(2),
                fila.getString(3),
                fila.getInt(4),
                fila.getInt(5),
                fila.getInt(6),
                fila.getString(7),
                )
        }
        bd.close()
        return u
    }

    fun obtenerUsuarios(contexto: Context):ArrayList<Usuario>{
        var usuarios:ArrayList<Usuario> = ArrayList(1)

        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("select * from usuarios", null)
        while (fila.moveToNext()) {
            var u = Usuario(
                fila.getString(0),
                fila.getString(1),
                fila.getString(2),
                fila.getString(3),
                fila.getInt(4),
                fila.getInt(5),
                fila.getInt(6),
                fila.getString(7),
            )
            usuarios.add(u)
        }
        bd.close()
        return usuarios
    }

}