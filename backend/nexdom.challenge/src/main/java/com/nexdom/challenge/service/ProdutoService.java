package com.nexdom.challenge.service;

import com.nexdom.challenge.dto.ProdutoDTO;
import com.nexdom.challenge.dto.ProdutoLucroDTO;
import com.nexdom.challenge.dto.ProdutoResumoDTO;
import com.nexdom.challenge.model.Produto;
import com.nexdom.challenge.repository.MovtoEstoqueRepository;
import com.nexdom.challenge.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final MovtoEstoqueRepository movtoEstoqueRepository;

    @Transactional
    public ProdutoDTO criar(ProdutoDTO dto) {
        Produto salvo = produtoRepository.save(dto.toEntity());
        return ProdutoDTO.fromEntity(salvo);
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProdutoDTO buscarPorCodigo(String codProduto) {
        Produto produto = produtoRepository.findById(codProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + codProduto));
        return ProdutoDTO.fromEntity(produto);
    }

    @Transactional
    public ProdutoDTO atualizar(String codProduto, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(codProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + codProduto));

        produto.setDscProduto(dto.getDscProduto());
        produto.setTipoProduto(dto.getTipoProduto());
        produto.setVlrFornecedor(dto.getVlrFornecedor());
        produto.setVlrVenda(dto.getVlrVenda());
        produto.setQtdEstoque(dto.getQtdEstoque());

        Produto atualizado = produtoRepository.save(produto);
        return ProdutoDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(String codProduto) {
        if (!produtoRepository.existsById(codProduto)) {
            throw new IllegalArgumentException("Produto não encontrado: " + codProduto);
        }
        produtoRepository.deleteById(codProduto);
    }

    @Transactional(readOnly = true)
    public List<ProdutoResumoDTO> consultarPorTipo(String tipoProduto) {
        return produtoRepository.findByTipoProduto(tipoProduto)
                .stream()
                .map(produto -> {
                    Integer qtdSaidaTotal = movtoEstoqueRepository
                            .sumQuantidadeByCodProdutoAndTipoMovto(produto.getCodProduto(), "S");
                    if (qtdSaidaTotal == null) {
                        qtdSaidaTotal = 0;
                    }
                    ProdutoResumoDTO resumo = new ProdutoResumoDTO();
                    resumo.setCodProduto(produto.getCodProduto());
                    resumo.setDscProduto(produto.getDscProduto());
                    resumo.setTipoProduto(produto.getTipoProduto());
                    resumo.setQtdSaidaTotal(qtdSaidaTotal);
                    resumo.setQtdDisponivel(produto.getQtdEstoque());
                    return resumo;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProdutoLucroDTO> consultarLucroPorProduto() {
        return produtoRepository.findAll()
                .stream()
                .map(produto -> {
                    Integer qtdSaidaTotal = movtoEstoqueRepository
                            .sumQuantidadeByCodProdutoAndTipoMovto(produto.getCodProduto(), "S");
                    if (qtdSaidaTotal == null) {
                        qtdSaidaTotal = 0;
                    }

                    BigDecimal margem = produto.getVlrVenda()
                            .subtract(produto.getVlrFornecedor());
                    BigDecimal lucroTotal = margem.multiply(BigDecimal.valueOf(qtdSaidaTotal));

                    ProdutoLucroDTO lucroDTO = new ProdutoLucroDTO();
                    lucroDTO.setCodProduto(produto.getCodProduto());
                    lucroDTO.setDscProduto(produto.getDscProduto());
                    lucroDTO.setQtdSaidaTotal(qtdSaidaTotal);
                    lucroDTO.setLucroTotal(lucroTotal);
                    return lucroDTO;
                })
                .collect(Collectors.toList());
    }
}
