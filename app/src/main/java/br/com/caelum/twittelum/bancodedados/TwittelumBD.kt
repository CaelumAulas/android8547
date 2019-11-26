package br.com.caelum.twittelum.bancodedados

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.caelum.twittelum.modelo.Tweet

@Database(entities = [Tweet::class], version = 2)
abstract class TwittelumBD : RoomDatabase() {
    abstract fun getTweetDao(): TweetDao

    companion object {
        private var banco: TwittelumBD? = null
        fun getInstance(contexto: Context) : TwittelumBD {

            return this.banco ?: criaBanco(contexto).also { this.banco = it }
        }

        private fun criaBanco(contexto: Context): TwittelumBD {
            val banco: TwittelumBD =
                Room.databaseBuilder(contexto, TwittelumBD::class.java, "Twittelum")
                    .allowMainThreadQueries()
                    .addMigrations(Migration1Para2)
                    .build()
            return banco
        }
    }
}