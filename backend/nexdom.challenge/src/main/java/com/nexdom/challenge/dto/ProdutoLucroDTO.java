package com.nexdom.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoLucroDTO {

    private String codProduto;
    private String dscProduto;
    private Integer qtdSaidaTotal;
    private BigDecimal lucroTotal;
}

