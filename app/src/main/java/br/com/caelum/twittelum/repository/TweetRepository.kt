package br.com.caelum.twittelum.repository

import br.com.caelum.twittelum.bancodedados.TweetDao
import br.com.caelum.twittelum.modelo.Tweet

class TweetRepository(private val tweetDao: TweetDao) {
    fun salva(tweet: Tweet) = tweetDao.salva(tweet)

    fun lista() = tweetDao.lista()
}