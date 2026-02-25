import { ref } from 'vue'
import { api } from '@/services/api'

export function useRelatorios() {
  const tipoFiltro = ref('')
  const produtosResumo = ref([])
  const lucros = ref([])
  const loading = ref(false)
  const error = ref('')
  const tiposProduto = ref({
    '1': 'Eletrodoméstico',
    '2': 'Eletrônico',
    '3': 'Móvel',
  })

  async function consultarProdutosPorTipo() {
    if (!tipoFiltro.value) return
    loading.value = true
    error.value = ''
    try {
      produtosResumo.value = await api.get(
        `/api/produtos/tipo/${encodeURIComponent(tipoFiltro.value)}`,
      )
    } catch (e) {
      error.value = e.message ?? 'Erro ao consultar produtos por tipo.'
    } finally {
      loading.value = false
    }
  }

  async function carregarLucros() {
    loading.value = true
    error.value = ''
    try {
      lucros.value = await api.get('/api/produtos/lucro')
    } catch (e) {
      error.value = e.message ?? 'Erro ao carregar lucros.'
    } finally {
      loading.value = false
    }
  }

  return {
    tipoFiltro,
    produtosResumo,
    lucros,
    loading,
    error,
    tiposProduto,
    consultarProdutosPorTipo,
    carregarLucros,
  }
}

