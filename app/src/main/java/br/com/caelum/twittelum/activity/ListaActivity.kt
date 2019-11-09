package br.com.caelum.twittelum.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.bancodedados.TwittelumBD
import br.com.caelum.twittelum.modelo.Tweet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val fab = findViewById<FloatingActionButton>(R.id.fab_novo_tweet)
        fab.setOnClickListener{
            val intent = Intent(this, TweetActivity::class.java)
            startActivity(intent)
            //Snackbar.make(it, "Botão clicado", Snackbar.LENGTH_SHORT).show()
        }

    }


    override fun onResume() {
        super.onResume()

        val twittelumBD = TwittelumBD.getInstance(this)
        val tweetDao = twittelumBD.getTweetDao()
        val tweets: List<Tweet> = tweetDao.lista()

        val adapter = ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, tweets)
        lista_tweets.adapter = adapter
    }
}