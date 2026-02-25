<script setup>
import { useMovimentacao } from '@/composables/useMovimentacao'

const {
  produtos,
  movimentos,
  movimentoForm,
  loading,
  error,
  registrarMovimento,
} = useMovimentacao()
</script>

<template>
  <section class="card">
    <h2>Movimentação de Estoque</h2>

    <form class="form-inline" @submit.prevent="registrarMovimento">
      <label>
        Produto
        <select v-model="movimentoForm.produto" required>
          <option value="" disabled>Selecione...</option>
          <option v-for="p in produtos" :key="p.codProduto" :value="p.codProduto">
            {{ p.codProduto }} - {{ p.dscProduto }}
          </option>
        </select>
      </label>
      <label>
        Tipo
        <select v-model="movimentoForm.tipoMovto">
          <option value="E">Entrada</option>
          <option value="S">Saída</option>
        </select>
      </label>
      <label>
        Quantidade
        <input v-model="movimentoForm.qtdMovto" type="number" min="1" required />
      </label>
      <button type="submit">Registrar</button>
    </form>

    <h3>Histórico de Movimentações</h3>
    <table class="table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Produto</th>
          <th>Tipo</th>
          <th>Quantidade</th>
          <th>Valor Total</th>
          <th>Data</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="m in movimentos" :key="m.id">
          <td>{{ m.id }}</td>
          <td>{{ m.produto }}</td>
          <td>{{ m.tipoMovto === 'E' ? 'Entrada' : 'Saída' }}</td>
          <td>{{ m.qtdMovto }}</td>
          <td>R$ {{ Number(m.vlrTotal).toFixed(2) }}</td>
          <td>{{ new Date(m.dtMovto).toLocaleDateString() }}</td>
        </tr>
        <tr v-if="movimentos.length === 0">
          <td colspan="6" class="empty">
            <span v-if="loading">Carregando...</span>
            <span v-else>Nenhuma movimentação registrada.</span>
          </td>
        </tr>
      </tbody>
    </table>

    <p v-if="error" class="error">{{ error }}</p>
  </section>
</template>

