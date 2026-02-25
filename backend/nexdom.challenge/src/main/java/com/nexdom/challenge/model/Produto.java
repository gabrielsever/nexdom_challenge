package com.nexdom.challenge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    @Column(name = "cod_produto")
    private String codProduto;

    @Column(name="dsc_produto")
    private String dscProduto;

    @Column(name="tipo_produto")
    private String tipoProduto;

    @Column(name="vlr_fornecedor")
    private BigDecimal vlrFornecedor;

    @Column(name="vlr_venda")
    private BigDecimal vlrVenda;

    @Column(name="qtd_estoque")
    private Integer qtdEstoque;

}
