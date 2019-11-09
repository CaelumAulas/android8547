package br.com.caelum.twittelum.bancodedados

import android.content.Context
import androidx.room.Room

class CriadorDeBanco {
    fun criaBanco(contexto: Context): TwittelumBD {
        val banco: TwittelumBD = Room.databaseBuilder(contexto, TwittelumBD::class.java, "Twittelum").build()
        return banco
    }
}