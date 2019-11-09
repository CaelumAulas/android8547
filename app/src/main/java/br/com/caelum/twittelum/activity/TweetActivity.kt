package br.com.caelum.twittelum.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.bancodedados.CriadorDeBanco
import br.com.caelum.twittelum.bancodedados.TwittelumBD
import br.com.caelum.twittelum.modelo.Tweet

class TweetActivity : AppCompatActivity() {

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

        val criadorDeBanco = CriadorDeBanco()
        val banco = criadorDeBanco.criaBanco(this)
        val tweetDao = banco.getTweetDao()
        tweetDao.salva(tweet)


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
