package br.com.zup.orgs.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import br.com.zup.orgs.databinding.FormularioImagemBinding
import br.com.zup.orgs.extensions.tentarCarregarImagem

class FormularioImagemDialog(private val context: Context) {

    fun mostra(
        urlPadrao: String? = null,
        quandoImagemCarregada: (imagem: String) -> Unit
    ) {
        FormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {
            urlPadrao.let {
                formularioImagemImageview.tentarCarregarImagem(it)
                formularioImagemUrl.setText(it)
            }

            formularioImagemBotaoCarregar.setOnClickListener {
                val url = formularioImagemUrl.text.toString()
                formularioImagemImageview.tentarCarregarImagem(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = formularioImagemUrl.text.toString()
                    quandoImagemCarregada(url)
                }
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .show()
        }
    }
}