package Auxiliar

import android.content.DialogInterface
import android.widget.Toast

object Dialogs {

    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
//        Toast.makeText(applicationContext,
//            "Has pulsado sí", Toast.LENGTH_SHORT).show()
    }
    val negativeButtonClick = { dialog: DialogInterface, which: Int ->
//        Toast.makeText(applicationContext,
//            "Has pulsado no", Toast.LENGTH_SHORT).show()
    }
    val neutralButtonClick = { dialog: DialogInterface, which: Int ->
//        Toast.makeText(applicationContext,
//            "Quizá", Toast.LENGTH_SHORT).show()
    }
}