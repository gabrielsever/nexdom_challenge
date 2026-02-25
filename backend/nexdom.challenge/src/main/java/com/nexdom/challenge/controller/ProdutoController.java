package com.nexdom.challenge.controller;

import com.nexdom.challenge.dto.ProdutoDTO;
import com.nexdom.challenge.dto.ProdutoLucroDTO;
import com.nexdom.challenge.dto.ProdutoResumoDTO;
import com.nexdom.challenge.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@RequestBody ProdutoDTO dto) {
        ProdutoDTO criado = produtoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @GetMapping("/{codProduto}")
    public ResponseEntity<ProdutoDTO> buscarPorCodigo(@PathVariable String codProduto) {
        return ResponseEntity.ok(produtoService.buscarPorCodigo(codProduto));
    }

    @PutMapping("/{codProduto}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable String codProduto,
                                                @RequestBody ProdutoDTO dto) {
        return ResponseEntity.ok(produtoService.atualizar(codProduto, dto));
    }

    @DeleteMapping("/{codProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String codProduto) {
        produtoService.deletar(codProduto);
    }

    @GetMapping("/tipo/{tipoProduto}")
    public ResponseEntity<List<ProdutoResumoDTO>> consultarPorTipo(@PathVariable String tipoProduto) {
        return ResponseEntity.ok(produtoService.consultarPorTipo(tipoProduto));
    }

    @GetMapping("/lucro")
    public ResponseEntity<List<ProdutoLucroDTO>> consultarLucro() {
        return ResponseEntity.ok(produtoService.consultarLucroPorProduto());
    }
}
