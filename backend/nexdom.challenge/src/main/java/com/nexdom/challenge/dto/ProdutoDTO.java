package com.nexdom.challenge.dto;

import com.nexdom.challenge.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private String codProduto;
    private String dscProduto;
    private String tipoProduto;
    private BigDecimal vlrFornecedor;
    private BigDecimal vlrVenda;
    private Integer qtdEstoque;

    public static ProdutoDTO fromEntity(Produto produto) {
        if (produto == null) {
            return null;
        }
        ProdutoDTO dto = new ProdutoDTO();
        dto.setCodProduto(produto.getCodProduto());
        dto.setDscProduto(produto.getDscProduto());
        dto.setTipoProduto(produto.getTipoProduto());
        dto.setVlrFornecedor(produto.getVlrFornecedor());
        dto.setVlrVenda(produto.getVlrVenda());
        dto.setQtdEstoque(produto.getQtdEstoque());
        return dto;
    }

    public Produto toEntity() {
        Produto produto = new Produto();
        produto.setCodProduto(this.codProduto);
        produto.setDscProduto(this.dscProduto);
        produto.setTipoProduto(this.tipoProduto);
        produto.setVlrFornecedor(this.vlrFornecedor);
        produto.setVlrVenda(this.vlrVenda);
        produto.setQtdEstoque(this.qtdEstoque);
        return produto;
    }
}
