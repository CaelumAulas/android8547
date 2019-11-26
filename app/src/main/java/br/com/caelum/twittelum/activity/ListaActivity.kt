package br.com.caelum.twittelum.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.adapter.TweetAdapter
import br.com.caelum.twittelum.bancodedados.TwittelumBD
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.repository.TweetRepository
import br.com.caelum.twittelum.viewmodel.TweetViewModel
import br.com.caelum.twittelum.viewmodel.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {
    private val tweetViewModel by lazy { ViewModelProviders.of(this, ViewModelFactory)
        .get(TweetViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        tweetViewModel.lista().observe(this, observer())

        val fab = findViewById<FloatingActionButton>(R.id.fab_novo_tweet)
        fab.setOnClickListener{
            val intent = Intent(this, TweetActivity::class.java)
            startActivity(intent)
            //Snackbar.make(it, "Botão clicado", Snackbar.LENGTH_SHORT).show()
        }

        val listener = AdapterView.OnItemLongClickListener {
            adapter, item, posicao, id ->

            //pega tweet
            val tweet: Tweet = adapter.getItemAtPosition(posicao) as Tweet
            //deletar tweet
            perguntaSeDeletaEsseTweet(tweet)

            false
        }
        lista_tweets.onItemLongClickListener = listener

    }

    private fun perguntaSeDeletaEsseTweet(tweet: Tweet) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Quer mesmo deletar?")
        dialogBuilder.setTitle("Atenção")
        dialogBuilder.setNegativeButton("NÃO", null)
        dialogBuilder.setPositiveButton("SIM") {
            _, _ ->
            tweetViewModel.deleta(tweet)
        }

        dialogBuilder.show()
    }

    private fun observer(): Observer<List<Tweet>> {
        return Observer {
            val adapter = TweetAdapter(it)
            lista_tweets.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("CICLO", "DESTROY")

    }
}