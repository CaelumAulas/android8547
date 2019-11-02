package br.com.caelum.twittelum.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.caelum.twittelum.R

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
        Toast.makeText(this, texto,Toast.LENGTH_LONG).show()
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
