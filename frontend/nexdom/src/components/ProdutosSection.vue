<script setup>
import { useProdutos } from '@/composables/useProdutos'

const {
  produtos,
  produtoForm,
  loading,
  error,
  tiposProduto,
  salvarProduto,
  editarProduto,
  excluirProduto,
  limparProdutoForm,
} = useProdutos()
</script>

<template>
  <section class="card">
    <h2>Cadastro de Produtos</h2>

    <form class="form-grid" @submit.prevent="salvarProduto">
      <label>
        Código
        <input v-model="produtoForm.codProduto" placeholder="Ex: TV001" required />
      </label>
      <label>
        Descrição
        <input v-model="produtoForm.dscProduto" placeholder="Ex: TV 50 polegadas" required />
      </label>
      <label>
        Tipo 
        <select v-model="produtoForm.tipoProduto" required>
          <option value="1">Eletrodoméstico</option>
          <option value="2">Eletrônico</option>
          <option value="3">Móvel</option>
        </select>
      </label>
      <label>
        Valor Fornecedor
        <input v-model="produtoForm.vlrFornecedor" type="number" step="0.01" min="0" required />
      </label>
      <label>
        Valor Venda
        <input v-model="produtoForm.vlrVenda" type="number" step="0.01" min="0" required />
      </label>

      <div class="form-actions">
        <button type="submit">Salvar produto</button>
        <button type="button" class="secondary" @click="limparProdutoForm">Limpar</button>
      </div>
    </form>

    <h3>Lista de Produtos</h3>
    <table class="table">
      <thead>
        <tr>
          <th>Código</th>
          <th>Descrição</th>
          <th>Tipo</th>
          <th>Fornecedor</th>
          <th>Venda</th>
          <th>Estoque</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="p in produtos" :key="p.codProduto">
          <td>{{ p.codProduto }}</td>
          <td>{{ p.dscProduto }}</td>
          <td>{{ tiposProduto[p.tipoProduto] }}</td>
          <td>R$ {{ Number(p.vlrFornecedor).toFixed(2) }}</td>
          <td>R$ {{ Number(p.vlrVenda).toFixed(2) }}</td>
          <td>{{ p.qtdEstoque }}</td>
          <td>
            <button class="link" @click="editarProduto(p)">Editar</button>
            <button class="link danger" @click="excluirProduto(p.codProduto)">Excluir</button>
          </td>
        </tr>
        <tr v-if="produtos.length === 0">
          <td colspan="7" class="empty">
            <span v-if="loading">Carregando...</span>
            <span v-else>Nenhum produto cadastrado.</span>
          </td>
        </tr>
      </tbody>
    </table>

    <p v-if="error" class="error">{{ error }}</p>
  </section>
</template>

