package br.com.caelum.twittelum.bancodedados

import androidx.room.Dao
import androidx.room.Insert
import br.com.caelum.twittelum.modelo.Tweet

@Dao
interface TweetDao {
    @Insert
    fun salva(tweet: Tweet)
}