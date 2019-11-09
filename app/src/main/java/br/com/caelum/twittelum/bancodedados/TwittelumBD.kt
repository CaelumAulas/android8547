package br.com.caelum.twittelum.bancodedados

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.caelum.twittelum.modelo.Tweet

@Database(entities = [Tweet::class], version = 1)
abstract class TwittelumBD : RoomDatabase() {
    abstract fun getTweetDao(): TweetDao
}