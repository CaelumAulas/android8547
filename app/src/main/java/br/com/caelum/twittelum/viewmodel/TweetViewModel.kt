package br.com.caelum.twittelum.viewmodel

import androidx.lifecycle.ViewModel
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.repository.TweetRepository

class TweetViewModel(private val tweetRepository: TweetRepository) : ViewModel() {
    fun salva(tweet: Tweet) = tweetRepository.salva(tweet)

    fun lista() = tweetRepository.lista()

    fun deleta(tweet: Tweet) = tweetRepository.deleta(tweet)
}