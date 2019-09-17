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

import br.org.cremesp.extranet.usuario.departamento.entity.Usuario;
import br.org.cremesp.extranet.usuario.departamento.exception.BadRequestException;
import br.org.cremesp.extranet.usuario.departamento.service.UsuarioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public List<Usuario> getAll() {
		return usuarioService.getAll();
	}

	@GetMapping("/{id}")
	public Usuario get(@PathVariable int id) throws BadRequestException {
		return usuarioService.get(id);
	}

	@PostMapping
	public Usuario add(@RequestBody Usuario usuario) throws BadRequestException {
		return usuarioService.add(usuario);
	}

	@PutMapping
	public Usuario edit(@RequestBody Usuario usuario) throws BadRequestException {
		return usuarioService.edit(usuario);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) throws BadRequestException {
		usuarioService.delete(id);
	}
}
