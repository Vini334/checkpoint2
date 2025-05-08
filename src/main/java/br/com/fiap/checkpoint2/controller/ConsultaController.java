package br.com.fiap.checkpoint2.controller;

import br.com.fiap.checkpoint2.dto.ConsultaDTO;
import br.com.fiap.checkpoint2.model.Consulta;
import br.com.fiap.checkpoint2.repository.ConsultaRepository;
import br.com.fiap.checkpoint2.repository.PacienteRepository;
import br.com.fiap.checkpoint2.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    @Autowired
    private ProfissionalRepository profissionalRepo;

    @PostMapping
    public ResponseEntity<Consulta> agendar(@RequestBody @Valid ConsultaDTO dto) {
        var nova = new Consulta();
        nova.setPaciente(pacienteRepo.findById(dto.pacienteId()).orElseThrow());
        nova.setProfissional(profissionalRepo.findById(dto.profissionalId()).orElseThrow());
        nova.setDataConsulta(dto.dataConsulta());
        nova.setStatusConsulta(dto.statusConsulta());
        nova.setQuantidadeHoras(dto.quantidadeHoras());
        nova.setValorConsulta(dto.valorConsulta());
        return ResponseEntity.status(201).body(consultaRepo.save(nova));
    }

    @GetMapping
    public List<Consulta> listarTodas() {
        return consultaRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        return consultaRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @RequestBody @Valid ConsultaDTO dto) {
        return consultaRepo.findById(id).map(c -> {
            c.setPaciente(pacienteRepo.findById(dto.pacienteId()).orElseThrow());
            c.setProfissional(profissionalRepo.findById(dto.profissionalId()).orElseThrow());
            c.setDataConsulta(dto.dataConsulta());
            c.setStatusConsulta(dto.statusConsulta());
            c.setQuantidadeHoras(dto.quantidadeHoras());
            c.setValorConsulta(dto.valorConsulta());
            return ResponseEntity.ok(consultaRepo.save(c));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        consultaRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
