package br.com.fiap.checkpoint2.controller;

import br.com.fiap.checkpoint2.model.Paciente;
import br.com.fiap.checkpoint2.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepo;

    @PostMapping
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody @Valid Paciente novoPaciente) {
        Paciente salvo = pacienteRepo.save(novoPaciente);
        return ResponseEntity.created(null).body(salvo);
    }

    @GetMapping
    public List<Paciente> listarPacientes() {
        return pacienteRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return pacienteRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable Long id, @RequestBody @Valid Paciente dados) {
        return pacienteRepo.findById(id).map(paciente -> {
            paciente.setNome(dados.getNome());
            paciente.setEndereco(dados.getEndereco());
            paciente.setBairro(dados.getBairro());
            paciente.setEmail(dados.getEmail());
            paciente.setTelefoneCompleto(dados.getTelefoneCompleto());
            paciente.setDataNascimento(dados.getDataNascimento());
            Paciente atualizado = pacienteRepo.save(paciente);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPaciente(@PathVariable Long id) {
        pacienteRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
