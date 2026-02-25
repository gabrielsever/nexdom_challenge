<script setup>
import EstoqueView from '@/views/EstoqueView.vue'
</script>

<template>
  <EstoqueView />
</template>

<style>
.app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: radial-gradient(circle at top left, #e3f2fd, #f8fafc);
  color: #0f172a;
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
}

.app-header {
  padding: 1.5rem 2rem;
  background: linear-gradient(135deg, #0ea5e9, #22c55e);
  color: white;
  box-shadow: 0 2px 8px rgba(15, 23, 42, 0.35);
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
}

.logo {
  font-size: 1.5rem;
  font-weight: 700;
  letter-spacing: 0.03em;
}

.subtitle {
  font-size: 0.95rem;
  opacity: 0.9;
}

.app-main {
  flex: 1;
  padding: 1.5rem 2rem 2rem;
  display: grid;
  grid-template-columns: 2fr 2fr;
  gap: 1.5rem;
}

.card {
  background: white;
  border-radius: 1rem;
  padding: 1.5rem;
  box-shadow: 0 10px 30px rgba(15, 23, 42, 0.1);
  border: 1px solid #e5e7eb;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.card h2 {
  font-size: 1.2rem;
  font-weight: 600;
  color: #0f172a;
}

.card h3 {
  margin-top: 0.75rem;
  font-size: 1rem;
  font-weight: 600;
  color: #0f172a;
}

.form-grid {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 0.75rem 1rem;
}

label {
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
  font-size: 0.8rem;
  color: #4b5563;
}

input,
select {
  border-radius: 0.5rem;
  border: 1px solid #d1d5db;
  padding: 0.4rem 0.6rem;
  font-size: 0.85rem;
  transition: border-color 0.15s ease, box-shadow 0.15s ease;
}

input:focus,
select:focus {
  outline: none;
  border-color: #0ea5e9;
  box-shadow: 0 0 0 1px rgba(14, 165, 233, 0.3);
}

.form-actions {
  grid-column: 1 / -1;
  display: flex;
  gap: 0.5rem;
  justify-content: flex-end;
  margin-top: 0.25rem;
}

button {
  border-radius: 999px;
  border: none;
  padding: 0.45rem 1rem;
  font-size: 0.85rem;
  font-weight: 500;
  cursor: pointer;
  background: linear-gradient(135deg, #0ea5e9, #22c55e);
  color: white;
  box-shadow: 0 3px 8px rgba(22, 163, 74, 0.35);
  transition: transform 0.1s ease, box-shadow 0.1s ease, filter 0.1s ease;
}

button:hover {
  transform: translateY(-1px);
  box-shadow: 0 5px 12px rgba(22, 163, 74, 0.4);
  filter: brightness(1.05);
}

button.secondary {
  background: #e5e7eb;
  color: #111827;
  box-shadow: none;
}

button.secondary:hover {
  box-shadow: 0 3px 6px rgba(148, 163, 184, 0.4);
}

button.link {
  background: transparent;
  color: #0ea5e9;
  box-shadow: none;
  padding: 0.2rem 0.4rem;
}

button.link:hover {
  text-decoration: underline;
  transform: none;
}

button.link.danger {
  color: #ef4444;
}

.table {
  width: 100%;
  border-collapse: collapse;
  font-size: 0.8rem;
}

.table th,
.table td {
  padding: 0.4rem 0.5rem;
  border-bottom: 1px solid #e5e7eb;
  text-align: left;
}

.table th {
  font-weight: 600;
  color: #4b5563;
  background: #f9fafb;
}

.table tbody tr:hover {
  background: #f9fafb;
}

.table .empty {
  text-align: center;
  color: #9ca3af;
  font-style: italic;
}

.form-inline {
  display: flex;
  flex-wrap: wrap;
  gap: 0.75rem;
  align-items: flex-end;
}

.form-inline label {
  min-width: 160px;
}

.consulta-tipo,
.consulta-lucro {
  margin-top: 0.5rem;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.app-footer {
  padding: 0.6rem 2rem;
  display: flex;
  justify-content: space-between;
  gap: 1rem;
  font-size: 0.8rem;
  color: #4b5563;
  border-top: 1px solid #e5e7eb;
  background: #f9fafb;
}

.error {
  color: #b91c1c;
}

@media (max-width: 1200px) {
  .app-main {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .app-header {
    padding: 1rem;
  }

  .app-main {
    padding: 1rem;
  }

  .card {
    padding: 1rem;
  }

  .form-grid {
    grid-template-columns: 1fr;
  }
}
</style>
