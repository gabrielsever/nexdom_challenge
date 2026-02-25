<script setup>
import ProdutosSection from '@/components/ProdutosSection.vue'
import MovimentacaoSection from '@/components/MovimentacaoSection.vue'
import RelatoriosSection from '@/components/RelatoriosSection.vue'
</script>

<template>
  <div class="app">
    <header class="app-header">
      <div class="logo">Nexdom Estoque</div>
      <div class="subtitle">Controle de produtos e movimentos</div>
    </header>

    <main class="app-main">
      <ProdutosSection />
      <MovimentacaoSection />
      <RelatoriosSection />
    </main>
  </div>
</template>

