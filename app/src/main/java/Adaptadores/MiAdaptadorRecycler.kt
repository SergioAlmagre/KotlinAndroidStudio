package Adaptadores


import Auxiliar.InterVentana
import Factorias.FactoriaUsuarios
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinandroidstudio.R
import com.example.kotlinandroidstudio.Usuario
import com.example.kotlinandroidstudio.VentanaInfo
import com.example.kotlinandroidstudio.VentanaLista


class MiAdaptadorRecycler(var usuarios : ArrayList<Usuario>, var  context: Context) : RecyclerView.Adapter<MiAdaptadorRecycler.ViewHolder>() {

    companion object {
        //Esta variable estática nos será muy útil para saber cual está marcado o no.
        var seleccionado: Int = -1
        /*
        PAra marcar o desmarcar un elemento de la lista lo haremos diferente a una listView. En la listView el listener
        está en la activity por lo que podemos controlar desde fuera el valor de seleccionado y pasarlo al adapter, asociamos
        el adapter a la listview y resuelto.
        En las RecyclerView usamos para pintar cada elemento la función bind (ver código más abajo, en la clase ViewHolder).
        Esto se carga una vez, solo una vez, de ahí la eficiencia de las RecyclerView. Si queremos que el click que hagamos
        se vea reflejado debemos recargar la lista, para ello forzamos la recarga con el método: notifyDataSetChanged().
         */
    }

    /**
     * onBindViewHolder() se encarga de coger cada una de las posiciones de la lista de personajes y pasarlas a la clase
     * ViewHolder(clase interna, ver abajo) para que esta pinte todos los valores y active el evento onClick en cada uno.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = usuarios.get(position)
        holder.bind(item, context, position, this)
    }

    /**
     *  Como su nombre indica lo que hará será devolvernos un objeto ViewHolder al cual le pasamos la celda que hemos creado.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        //return ViewHolder(layoutInflater.inflate(R.layout.item_lo,parent,false))
//        return ViewHolder(layoutInflater.inflate(R.layout.item_card,parent,false))
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        val viewHolder = ViewHolder(vista)
        // Configurar el OnClickListener
        viewHolder.itemView.setOnClickListener {
            val intent = Intent(context, VentanaLista::class.java)
            context.startActivity(intent)
        }

        return viewHolder
    }

    /**
     * getItemCount() nos devuelve el tamaño de la lista, que lo necesita el RecyclerView.
     */
    override fun getItemCount(): Int {
        return usuarios.size
    }


    //--------------------------------- Clase interna ViewHolder -----------------------------------
    /**
     * La clase ViewHolder. No es necesaria hacerla dentro del adapter, pero como van tan ligadas
     * se puede declarar aquí.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Esto solo se asocia la primera vez que se llama a la clase, en el método onCreate de la clase que contiene a esta.
        //Por eso no hace falta que hagamos lo que hacíamos en el método getView de los adaptadores para las listsViews.
        //val nombrePersonaje = view.findViewById(R.id.txtNombre) as TextView
        //val tipoPersonaje = view.findViewById(R.id.txtTipo) as TextView
        //val avatar = view.findViewById(R.id.imgImagen) as ImageView

        //Como en el ejemplo general de las listas (ProbandoListas) vemos que se puede inflar cada elemento con una card o con un layout.
        val nombreUsuario = view.findViewById(R.id.txtNombreItem) as TextView
        val apellidoUsuario = view.findViewById(R.id.txtApellidoItem) as TextView
        val avatar = view.findViewById(R.id.pic) as ImageView
        val btnInfo = view.findViewById<Button>(R.id.btnInfo) as Button
        val btnIniciarSecion = view.findViewById<Button>(R.id.btnIniciarSesion) as Button

        /**
         * Éste método se llama desde el método onBindViewHolder de la clase contenedora. Como no vuelve a crear un objeto
         * sino que usa el ya creado en onCreateViewHolder, las asociaciones findViewById no vuelven a hacerse y es más eficiente.
         */
        @SuppressLint("ResourceAsColor")
        fun bind(
            usu: Usuario,
            context: Context,
            pos: Int,
            miAdaptadorRecycler: MiAdaptadorRecycler
        ) {
            nombreUsuario.text = usu.nombre
            apellidoUsuario.text = usu.apellido
            Glide.with(context).load(usu.foto).into(avatar)


//            if (usu.nombre.equals("Juan")) {
//                Para Gandalf, como ejemplo, le he puesto una imagen que se llama igual en el drawable.
//                se podría hacer igual para los otros personajes.
//                val uri = "@drawable/" + usu.foto
//                val imageResource: Int =
//                    context.getResources().getIdentifier(uri, null, context.getPackageName())
//                var res: Drawable = context.resources.getDrawable(imageResource)
//                avatar.setImageDrawable(res)
//            } else {
//
//
//            }


            //Para marcar o desmarcar al seleccionado usamos el siguiente código.
//            if (pos == MiAdaptadorRecycler.seleccionado) {
//                with(nombreUsuario) {
//                    this.setTextColor(resources.getColor(androidx.appcompat.R.color.abc_btn_colored_text_material))
//                }
//                tipoPersonaje.setTextColor(androidx.appcompat.R.color.abc_btn_colored_text_material)
//            } else {
//                with(nombreUsuario) {
//                    this.setTextColor(resources.getColor(androidx.appcompat.R.color.abc_btn_colored_text_material))
//                }
//                tipoPersonaje.setTextColor(androidx.appcompat.R.color.abc_btn_colored_text_material)
//            }
//
//            itemView.setOnLongClickListener(View.OnLongClickListener() {
//                Log.e("Fernando","long click")
//            }

            //Se levanta una escucha para cada item. Si pulsamos el seleccionado pondremos la selección a -1, en otro caso será el nuevo sleccionado.
            itemView.setOnClickListener {
                InterVentana.usuarioIV = InterVentana.usuariosIV[pos] // valor dado por indice de pos en itemView desde ArrayList en Interventana

                if (InterVentana.usuarioIV != null){
                    Toast.makeText(
                        context,
                        "Seleccionado " + InterVentana.usuarioIV!!.nombre,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            btnInfo.setOnClickListener {
                Toast.makeText(
                    context,
                    "Seleccionado " + usu.nombre,
                    Toast.LENGTH_SHORT
                ).show()

//                InterVentana.usuarioIV = usu  // valor dado por Bind de usu pero hay lios con los null que resolver
                val inte = Intent(context, VentanaInfo::class.java)
                inte.putExtra("obj",usu)
                context.startActivity(inte)
            }


            avatar.setOnLongClickListener {
                Log.e("Sergio","Seleccionado con long click")

                val builder = AlertDialog.Builder(context)

                with(builder)
                {
                    setTitle("Cambio de avatar")
                    setMessage("¿Deseas cambiar tu avatar?")
                    //Otra forma es definir directamente aquí lo que se hace cuando se pulse.
                    setPositiveButton("Si", DialogInterface.OnClickListener(
                        function = { dialog: DialogInterface, which: Int -> Toast.makeText(context, "Imagen cambiada", Toast.LENGTH_SHORT).show()
                            Glide.with(context).load(FactoriaUsuarios.getImagen()).into(avatar)
                            //Con la siguiente instrucción forzamos a recargar el viewHolder porque han cambiado los datos. Así pintará al seleccionado.
                            miAdaptadorRecycler.notifyDataSetChanged()
                            Log.e("Sergio","pulsado si")
                    }))
                    setNegativeButton("No", ({ dialog: DialogInterface, which: Int ->
                        Toast.makeText(context,
                            "Operacion cancelada", Toast.LENGTH_SHORT).show()
                    }))

                    show()
                }
                true
            }

        }
    }
}
