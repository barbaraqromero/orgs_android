package br.com.zup.orgs.extensions

import android.widget.ImageView
import br.com.zup.orgs.R
import coil.load

fun ImageView.tentarCarregarImagem(url: String? = null) {
    load(url) {
        fallback(R.drawable.error)
        error(R.drawable.error)
        placeholder(R.drawable.placeholder)
    }

}
