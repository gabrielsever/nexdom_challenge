package com.nexdom.challenge.service;

import com.nexdom.challenge.model.Produto;
import com.nexdom.challenge.repository.MovtoEstoqueRepository;
import com.nexdom.challenge.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovtoEstoqueServiceTest {

    @Mock
    private MovtoEstoqueRepository movtoEstoqueRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private MovtoEstoqueService service;

    @Test
    void deveRegistrarEntrada() {

        Produto produto = new Produto();
        produto.setCodProduto("PROD1");
        produto.setQtdEstoque(5);
        produto.setVlrFornecedor(new BigDecimal("10.00"));

        when(produtoRepository.findById("PROD1"))
                .thenReturn(java.util.Optional.of(produto));

        when(produtoRepository.save(produto))
                .thenReturn(produto);

        when(movtoEstoqueRepository.save(org.mockito.ArgumentMatchers.any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        var dto = new com.nexdom.challenge.dto.MovtoEstoqueDTO();
        dto.setProduto("PROD1");
        dto.setTipoMovto("E");
        dto.setQtdMovto(2);

        service.registrarMovimento(dto);

        assertEquals(7, produto.getQtdEstoque());
    }
}