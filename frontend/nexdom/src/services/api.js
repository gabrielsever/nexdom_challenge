const API_BASE_URL = 'http://localhost:8080'

async function request(path, options = {}) {
  const url = `${API_BASE_URL}${path}`
  const defaultHeaders = { 'Content-Type': 'application/json' }

  const resp = await fetch(url, {
    headers: defaultHeaders,
    ...options,
  })

  const data = await resp.json().catch(() => null)
  if (!resp.ok) {
    throw new Error(data?.message || 'Erro ao processar requisição.')
  }
  return data
}

export const api = {
  get: (path) => request(path),
  post: (path, body) => request(path, { method: 'POST', body: JSON.stringify(body) }),
  put: (path, body) => request(path, { method: 'PUT', body: JSON.stringify(body) }),
  del: (path) => request(path, { method: 'DELETE' }),
}

