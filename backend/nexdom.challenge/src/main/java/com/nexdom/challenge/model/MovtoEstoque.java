package com.nexdom.challenge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="MOVTO_ESTOQUE")
public class MovtoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cod_produto")
    private String codProduto;

    @Column(name="tipo_movto")
    private String tipoMovto;

    @Column(name="vlr_total")
    private BigDecimal vlrTotal;

    @Column(name="qtd_movto")
    private Integer qtdMovto;

    @Column(name="dt_movto")
    private Date dtMovto;
}
