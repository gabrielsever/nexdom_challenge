<script setup>
import { useRelatorios } from '@/composables/useRelatorios'

const {
  tipoFiltro,
  produtosResumo,
  lucros,
  loading,
  error,
  tiposProduto,
  consultarProdutosPorTipo,
  carregarLucros,
} = useRelatorios()
</script>

<template>
  <section class="card">
    <h2>Consultas e Relatórios</h2>

    <div class="consulta-tipo">
      <h3>Produtos por tipo</h3>
      <div class="form-inline">
        <label>
          Tipo
        <select v-model="tipoFiltro">
          <option value="1">Eletrodoméstico</option>
          <option value="2">Eletrônico</option>
          <option value="3">Móvel</option>
        </select>
        </label>
        <button @click="consultarProdutosPorTipo">Consultar</button>
      </div>

      <table class="table">
        <thead>
          <tr>
            <th>Código</th>
            <th>Descrição</th>
            <th>Tipo</th>
            <th>Total Saídas</th>
            <th>Estoque Disponível</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="r in produtosResumo" :key="r.codProduto">
            <td>{{ r.codProduto }}</td>
            <td>{{ r.dscProduto }}</td>
            <td>{{ tiposProduto[r.tipoProduto] }}</td>
            <td>{{ r.qtdSaidaTotal }}</td>
            <td>{{ r.qtdDisponivel }}</td>
          </tr>
          <tr v-if="produtosResumo.length === 0">
            <td colspan="5" class="empty">
              <span v-if="loading">Carregando...</span>
              <span v-else>Nenhum resultado para o tipo informado.</span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="consulta-lucro">
      <h3>Lucro por produto</h3>
      <button class="secondary" @click="carregarLucros">Atualizar</button>

      <table class="table">
        <thead>
          <tr>
            <th>Código</th>
            <th>Descrição</th>
            <th>Total Saídas</th>
            <th>Lucro Total</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="l in lucros" :key="l.codProduto">
            <td>{{ l.codProduto }}</td>
            <td>{{ l.dscProduto }}</td>
            <td>{{ l.qtdSaidaTotal }}</td>
            <td>R$ {{ Number(l.lucroTotal).toFixed(2) }}</td>
          </tr>
          <tr v-if="lucros.length === 0">
            <td colspan="4" class="empty">
              <span v-if="loading">Carregando...</span>
              <span v-else>Nenhum dado de lucro disponível.</span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <p v-if="error" class="error">{{ error }}</p>
  </section>
</template>

