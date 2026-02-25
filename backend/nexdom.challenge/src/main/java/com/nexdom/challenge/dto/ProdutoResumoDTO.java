package com.nexdom.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResumoDTO {

    private String codProduto;
    private String dscProduto;
    private String tipoProduto;
    private Integer qtdSaidaTotal;
    private Integer qtdDisponivel;
}

