package com.nexdom.challenge;

import com.nexdom.challenge.model.Produto;
import com.nexdom.challenge.repository.MovtoEstoqueRepository;
import com.nexdom.challenge.repository.ProdutoRepository;
import com.nexdom.challenge.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private MovtoEstoqueRepository movtoEstoqueRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void deveCalcularLucroCorretamente() {

        Produto produto = new Produto();
        produto.setCodProduto("PROD1");
        produto.setDscProduto("Produto Teste");
        produto.setVlrFornecedor(new BigDecimal("50.00"));
        produto.setVlrVenda(new BigDecimal("100.00"));

        when(produtoRepository.findAll())
                .thenReturn(List.of(produto));

        when(movtoEstoqueRepository
                .sumQuantidadeByCodProdutoAndTipoMovto("PROD1", "S"))
                .thenReturn(3);

        var resultado = produtoService.consultarLucroPorProduto();

        assertEquals(1, resultado.size());
        assertEquals(new BigDecimal("150.00"),
                resultado.get(0).getLucroTotal());
    }
}