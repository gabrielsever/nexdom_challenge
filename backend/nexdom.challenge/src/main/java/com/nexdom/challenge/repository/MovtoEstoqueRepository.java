package com.nexdom.challenge.repository;

import com.nexdom.challenge.model.MovtoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovtoEstoqueRepository extends JpaRepository<MovtoEstoque, Integer> {

    @Query("select coalesce(sum(m.qtdMovto),0) from MovtoEstoque m " +
           "where m.codProduto = :codProduto and m.tipoMovto = :tipoMovto")
    Integer sumQuantidadeByCodProdutoAndTipoMovto(@Param("codProduto") String codProduto,
                                                  @Param("tipoMovto") String tipoMovto);
}
