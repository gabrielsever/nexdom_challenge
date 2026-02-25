package com.nexdom.challenge.controller;

import com.nexdom.challenge.dto.MovtoEstoqueDTO;
import com.nexdom.challenge.service.MovtoEstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentos")
@RequiredArgsConstructor
public class MovtoEstoqueController {

    private final MovtoEstoqueService movtoEstoqueService;

    @PostMapping
    public ResponseEntity<MovtoEstoqueDTO> registrar(@RequestBody MovtoEstoqueDTO dto) {
        MovtoEstoqueDTO criado = movtoEstoqueService.registrarMovimento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<MovtoEstoqueDTO>> listar() {
        return ResponseEntity.ok(movtoEstoqueService.listar());
    }
}
