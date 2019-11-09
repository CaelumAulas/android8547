package br.com.caelum.twittelum.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelum.TwittelumApplication
import br.com.caelum.twittelum.bancodedados.TwittelumBD
import br.com.caelum.twittelum.repository.TweetRepository

object ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val twittelumBD = TwittelumBD.getInstance(TwittelumApplication.getInstance())
        val tweetDao = twittelumBD.getTweetDao()
        val tweetRepository = TweetRepository(tweetDao)
        val tweetViewModel = TweetViewModel(tweetRepository)

        return tweetViewModel as T
    }
}