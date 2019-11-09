package br.com.caelum.twittelum.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.bancodedados.TweetDao
import br.com.caelum.twittelum.bancodedados.TwittelumBD
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.repository.TweetRepository
import br.com.caelum.twittelum.viewmodel.TweetViewModel
import br.com.caelum.twittelum.viewmodel.ViewModelFactory

class TweetActivity : AppCompatActivity() {
    private val tweetViewModel: TweetViewModel by lazy { ViewModelProviders.of(this, ViewModelFactory)
                                .get(TweetViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun publicaTweet() {
        Log.i("TWEET", "botão clicado")

        val campoTexto = findViewById<EditText>(R.id.tweet_mensagem)
        val texto = campoTexto.text.toString()

        val tweet = Tweet(texto)

        tweetViewModel.salva(tweet)

        Toast.makeText(this, "${tweet}",Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tweet,menu)
        return true //menu esta visivel?
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.salva -> {
                publicaTweet()
                finish()
            }
            android.R.id.home -> finish()
        }


        return false  //para o processamento por aqui?
    }

}
