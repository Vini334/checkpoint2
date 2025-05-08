package br.com.fiap.checkpoint2.repository;

import br.com.fiap.checkpoint2.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {}
