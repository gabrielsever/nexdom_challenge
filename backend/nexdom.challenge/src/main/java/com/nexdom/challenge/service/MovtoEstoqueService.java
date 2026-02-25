package com.nexdom.challenge.service;

import com.nexdom.challenge.dto.MovtoEstoqueDTO;
import com.nexdom.challenge.exception.SaldoInsuficienteException;
import com.nexdom.challenge.model.MovtoEstoque;
import com.nexdom.challenge.model.Produto;
import com.nexdom.challenge.repository.MovtoEstoqueRepository;
import com.nexdom.challenge.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovtoEstoqueService {

    private final MovtoEstoqueRepository movtoEstoqueRepository;
    private final ProdutoRepository produtoRepository;

    @Transactional
    public MovtoEstoqueDTO registrarMovimento(MovtoEstoqueDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProduto())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + dto.getProduto()));

        Integer quantidade = dto.getQtdMovto();
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade movimentada deve ser maior que zero.");
        }

        String tipoMovto = dto.getTipoMovto();
        Integer estoqueAtual = produto.getQtdEstoque();
        if (estoqueAtual == null) {
            estoqueAtual = 0;
        }

        BigDecimal vlrTotal = null;
        if ("E".equalsIgnoreCase(tipoMovto)) {
            produto.setQtdEstoque(estoqueAtual + quantidade);

            vlrTotal = produto.getVlrFornecedor()
                    .multiply(BigDecimal.valueOf(quantidade));

        } else if ("S".equalsIgnoreCase(tipoMovto)) {
            if (estoqueAtual < quantidade) {
                throw new SaldoInsuficienteException("Quantidade em estoque insuficiente para saída do produto " + produto.getCodProduto());
            }
            produto.setQtdEstoque(estoqueAtual - quantidade);

            vlrTotal = produto.getVlrVenda()
                    .multiply(BigDecimal.valueOf(quantidade));
        } else {
            throw new IllegalArgumentException("Tipo de movimentação inválido. Use 'E' para entrada ou 'S' para saída.");
        }


        MovtoEstoque movto = dto.toEntity();
        movto.setCodProduto(produto.getCodProduto());
        movto.setVlrTotal(vlrTotal);
        if (movto.getDtMovto() == null) {
            movto.setDtMovto(new Date());
        }

        produtoRepository.save(produto);
        MovtoEstoque salvo = movtoEstoqueRepository.save(movto);
        return MovtoEstoqueDTO.fromEntity(salvo);
    }

    @Transactional(readOnly = true)
    public List<MovtoEstoqueDTO> listar() {
        return movtoEstoqueRepository.findAll()
                .stream()
                .map(MovtoEstoqueDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
