import { ref, reactive, onMounted } from 'vue'
import { api } from '@/services/api'

export function useProdutos() {
  const produtos = ref([])
  const produtoForm = reactive({
    codProduto: '',
    dscProduto: '',
    tipoProduto: '',
    vlrFornecedor: '',
    vlrVenda: '',
    qtdEstoque: 0,
  })
  const loading = ref(false)
  const error = ref('')
  const tiposProduto = ref({
    '1': 'Eletrodoméstico',
    '2': 'Eletrônico',
    '3': 'Móvel',
  })

  async function listar() {
    loading.value = true
    error.value = ''
    try {
      produtos.value = await api.get('/api/produtos')
    } catch (e) {
      error.value = e.message ?? 'Erro ao carregar produtos.'
    } finally {
      loading.value = false
    }
  }

  async function salvarProduto() {
    loading.value = true
    error.value = ''
    try {
      const existe = produtos.value.some((p) => p.codProduto === produtoForm.codProduto)
      const payload = {
        ...produtoForm,
        vlrFornecedor: Number(produtoForm.vlrFornecedor),
        vlrVenda: Number(produtoForm.vlrVenda),
        qtdEstoque: Number(produtoForm.qtdEstoque),
      }

      if (existe) {
        await api.put(`/api/produtos/${encodeURIComponent(produtoForm.codProduto)}`, payload)
      } else {
        await api.post('/api/produtos', payload)
      }

      await listar()
      limparProdutoForm()
    } catch (e) {
      error.value = e.message ?? 'Erro ao salvar produto.'
    } finally {
      loading.value = false
    }
  }

  function editarProduto(produto) {
    Object.assign(produtoForm, {
      codProduto: produto.codProduto,
      dscProduto: produto.dscProduto,
      tipoProduto: produto.tipoProduto,
      vlrFornecedor: produto.vlrFornecedor,
      vlrVenda: produto.vlrVenda,
      qtdEstoque: produto.qtdEstoque,
    })
  }

  async function excluirProduto(codProduto) {
    const confirmado = window.confirm('Deseja realmente excluir este produto?')
    if (!confirmado) return

    loading.value = true
    error.value = ''
    try {
      await api.del(`/api/produtos/${encodeURIComponent(codProduto)}`)
      await listar()
    } catch (e) {
      error.value = e.message ?? 'Erro ao excluir produto.'
    } finally {
      loading.value = false
    }
  }

  function limparProdutoForm() {
    Object.assign(produtoForm, {
      codProduto: '',
      dscProduto: '',
      tipoProduto: '',
      vlrFornecedor: '',
      vlrVenda: '',
      qtdEstoque: 0,
    })
  }

  onMounted(listar)

  return {
    produtos,
    produtoForm,
    loading,
    error,
    tiposProduto,
    salvarProduto,
    editarProduto,
    excluirProduto,
    limparProdutoForm,
  }
}

