package br.org.cremesp.extranet.usuario.departamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.cremesp.extranet.usuario.departamento.entity.Departamento;
import br.org.cremesp.extranet.usuario.departamento.exception.BadRequestException;
import br.org.cremesp.extranet.usuario.departamento.service.DepartamentoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping
	public List<Departamento> getAll() {
		return departamentoService.getAll();
	}

	@GetMapping("/{id}")
	public Departamento get(@PathVariable int id) throws BadRequestException {
		return departamentoService.get(id);
	}

	@PostMapping
	public Departamento add(@RequestBody Departamento departamento) {
		return departamentoService.add(departamento);
	}

	@PutMapping
	public Departamento edit(@RequestBody Departamento departamento) throws BadRequestException {
		return departamentoService.edit(departamento);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) throws BadRequestException {
		departamentoService.delete(id);
	}
}
