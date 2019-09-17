package br.org.cremesp.extranet.usuario.departamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.extranet.usuario.departamento.entity.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

	public List<Departamento> findAllByOrderByIdAsc();
}
