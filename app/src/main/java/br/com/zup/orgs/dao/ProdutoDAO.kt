package br.com.zup.orgs.dao

import br.com.zup.orgs.model.Produto

class ProdutoDAO {

    companion object{
        private val produtos = mutableListOf<Produto>()
    }

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }
}