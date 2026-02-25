import { ref, reactive, onMounted } from 'vue'
import { api } from '@/services/api'

export function useMovimentacao() {
  const produtos = ref([])
  const movimentos = ref([])
  const movimentoForm = reactive({
    produto: '',
    tipoMovto: 'E',
    qtdMovto: 0,
  })
  const loading = ref(false)
  const error = ref('')

  async function carregarProdutos() {
    produtos.value = await api.get('/api/produtos')
  }

  async function carregarMovimentos() {
    movimentos.value = await api.get('/api/movimentos')
  }

  async function registrarMovimento() {
    loading.value = true
    error.value = ''
    try {
      const payload = {
        produto: movimentoForm.produto,
        tipoMovto: movimentoForm.tipoMovto,
        qtdMovto: Number(movimentoForm.qtdMovto),
      }
      await api.post('/api/movimentos', payload)
      await carregarProdutos()
      await carregarMovimentos()
      limparMovimentoForm()
    } catch (e) {
      error.value = e.message ?? 'Erro ao registrar movimento.'
    } finally {
      loading.value = false
    }
  }

  function limparMovimentoForm() {
    Object.assign(movimentoForm, {
      produto: '',
      tipoMovto: 'E',
      qtdMovto: 0,
    })
  }

  onMounted(async () => {
    try {
      loading.value = true
      await carregarProdutos()
      await carregarMovimentos()
    } catch (e) {
      error.value = e.message ?? 'Erro ao carregar movimentações.'
    } finally {
      loading.value = false
    }
  })

  return {
    produtos,
    movimentos,
    movimentoForm,
    loading,
    error,
    registrarMovimento,
    limparMovimentoForm,
  }
}

