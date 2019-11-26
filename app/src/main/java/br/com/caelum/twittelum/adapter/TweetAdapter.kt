package br.com.caelum.twittelum.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.extensions.Decodificador
import br.com.caelum.twittelum.modelo.Tweet
import kotlinx.android.synthetic.main.item_tweet.view.*

class TweetAdapter(val tweets: List<Tweet>) : BaseAdapter() {
    override fun getView(posicao: Int, p1: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)

        val tweet = tweets[posicao]

        val viewDoItem = inflater.inflate(R.layout.item_tweet, parent, false)

        viewDoItem.item_tweet_texto.text = tweet.mensagem

        val fotoNaBase64 = tweet.foto

        fotoNaBase64?.let {
            val bitmap: Bitmap = Decodificador.decodificaPraBitmap(fotoNaBase64)
            viewDoItem.item_tweet_foto.setImageBitmap(bitmap)
            viewDoItem.item_tweet_foto.visibility = View.VISIBLE
        }
        return viewDoItem
    }

    override fun getItem(posicao: Int): Any {
        return tweets[posicao]
    }

    override fun getItemId(posicao: Int): Long {
        return tweets[posicao].id.toLong()
    }

    override fun getCount(): Int {
        return tweets.size
    }
}