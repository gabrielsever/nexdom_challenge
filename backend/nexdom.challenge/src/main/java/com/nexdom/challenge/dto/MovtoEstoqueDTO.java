package com.nexdom.challenge.dto;

import com.nexdom.challenge.model.MovtoEstoque;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovtoEstoqueDTO {

    private Integer id;
    /**
     * Código do produto
     */
    private String produto;
    private String tipoMovto;
    private BigDecimal vlrTotal;
    private Integer qtdMovto;
    private Date dtMovto;

    public static MovtoEstoqueDTO fromEntity(MovtoEstoque movto) {
        if (movto == null) {
            return null;
        }
        MovtoEstoqueDTO dto = new MovtoEstoqueDTO();
        dto.setId(movto.getId());
        dto.setProduto(movto.getCodProduto());
        dto.setTipoMovto(movto.getTipoMovto());
        dto.setVlrTotal(movto.getVlrTotal());
        dto.setQtdMovto(movto.getQtdMovto());
        dto.setDtMovto(movto.getDtMovto());
        return dto;
    }

    public MovtoEstoque toEntity() {
        MovtoEstoque movto = new MovtoEstoque();
        movto.setId(this.id);
        movto.setCodProduto(this.produto);
        movto.setTipoMovto(this.tipoMovto);
        movto.setVlrTotal(this.vlrTotal);
        movto.setQtdMovto(this.qtdMovto);
        movto.setDtMovto(this.dtMovto);
        return movto;
    }
}
