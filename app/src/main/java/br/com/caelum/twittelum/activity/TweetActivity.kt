package br.com.caelum.twittelum.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.bancodedados.TweetDao
import br.com.caelum.twittelum.bancodedados.TwittelumBD
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.repository.TweetRepository
import br.com.caelum.twittelum.viewmodel.TweetViewModel
import br.com.caelum.twittelum.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tweet.*
import java.io.File

class TweetActivity : AppCompatActivity() {
    private val tweetViewModel: TweetViewModel by lazy { ViewModelProviders.of(this, ViewModelFactory)
                                .get(TweetViewModel::class.java) }
    val CODIGO_CAMERA: Int = 123
    var caminhoDaFoto: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODIGO_CAMERA -> {
                if (resultCode == Activity.RESULT_OK) {
                    carregaImagem()
                }
            }
        }
    }

    private fun carregaImagem() {
        val bitmap = BitmapFactory.decodeFile(caminhoDaFoto)
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true)
        tweet_foto.setImageBitmap(scaledBitmap)
        tweet_foto.scaleType = ImageView.ScaleType.FIT_XY
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
            R.id.camera -> {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                caminhoDaFoto = "${getExternalFilesDir(Environment.DIRECTORY_PICTURES)}/${System.currentTimeMillis()}.jpg"
                Log.i("caminho", caminhoDaFoto)
                val arquivo = File(caminhoDaFoto)
                val uri =
                    FileProvider.getUriForFile(this,"br.com.caelum.twittelum.fileprovider", arquivo)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                startActivityForResult(intent, CODIGO_CAMERA)
            }
            android.R.id.home -> finish()
        }


        return false  //para o processamento por aqui?
    }

}
