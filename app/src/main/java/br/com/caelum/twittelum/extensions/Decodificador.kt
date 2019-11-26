package br.com.caelum.twittelum.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

class Decodificador {

    companion object {
        fun decodificaPraBitmap(fotoNaBase64: String?): Bitmap {
            val bytes = Base64.decode(fotoNaBase64, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            return bitmap
        }
    }
}