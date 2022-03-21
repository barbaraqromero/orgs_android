package br.com.zup.orgs.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.orgs.R
import br.com.zup.orgs.dao.ProdutoDAO
import br.com.zup.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.zup.orgs.databinding.FormularioImagemBinding
import br.com.zup.orgs.model.Produto
import coil.load
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImagem.setOnClickListener {
            val bindingFormImagem = FormularioImagemBinding.inflate(layoutInflater)
            bindingFormImagem.formularioImagemBotaoCarregar.setOnClickListener {
                val url = bindingFormImagem.formularioImagemUrl.text.toString()
                bindingFormImagem.formularioImagemImageview.load(url)
            }

            AlertDialog.Builder(this)
                .setView(bindingFormImagem.root)
                .setPositiveButton("Confirmar") { dialog, which ->
                    url = bindingFormImagem.formularioImagemUrl.text.toString()
                    binding.activityFormularioProdutoImagem.load(url)

                }
                .setNegativeButton("Cancelar") { _, _ ->

                }

                .show()
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.buttonSalvar
        val dao = ProdutoDAO()

        botaoSalvar.setOnClickListener(View.OnClickListener {
            val produtoNovo = criarProduto()
            dao.adiciona(produtoNovo)
            finish()

        })
    }

    private fun criarProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }
}


