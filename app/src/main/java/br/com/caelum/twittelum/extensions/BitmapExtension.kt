package br.com.caelum.twittelum.extensions

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream


fun Bitmap.convertePraBase64(): String? {
    val byteArrayOutputStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100,byteArrayOutputStream)
    val bytes = byteArrayOutputStream.toByteArray()
    val fotoNaBase64 = Base64.encodeToString(bytes, 0, bytes.size, Base64.DEFAULT)

    return fotoNaBase64
}