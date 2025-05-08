package br.com.fiap.checkpoint2.controller;

import br.com.fiap.checkpoint2.model.Profissional;
import br.com.fiap.checkpoint2.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalRepository profissionalRepo;

    @PostMapping
    public ResponseEntity<Profissional> criar(@RequestBody @Valid Profissional novoProfissional) {
        return ResponseEntity.status(201).body(profissionalRepo.save(novoProfissional));
    }

    @GetMapping
    public List<Profissional> listarTodos() {
        return profissionalRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> obter(@PathVariable Long id) {
        return profissionalRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> atualizar(@PathVariable Long id, @RequestBody @Valid Profissional dados) {
        return profissionalRepo.findById(id).map(p -> {
            p.setNome(dados.getNome());
            p.setEspecialidade(dados.getEspecialidade());
            p.setValorHora(dados.getValorHora());
            return ResponseEntity.ok(profissionalRepo.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        profissionalRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
