package br.org.cremesp.extranet.usuario.departamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.extranet.usuario.departamento.constantes.UsuarioDepartamentoEnum;
import br.org.cremesp.extranet.usuario.departamento.entity.Usuario;
import br.org.cremesp.extranet.usuario.departamento.exception.BadRequestException;
import br.org.cremesp.extranet.usuario.departamento.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> getAll() {
		return usuarioRepository.findAllByOrderByIdAsc();
	}

	public Usuario get(int id) throws BadRequestException {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new BadRequestException(UsuarioDepartamentoEnum.MSG_USUARIO_FIND_ERRO.getTexto()));
	}

	public Usuario add(Usuario usuario) throws BadRequestException {
		try {
			return usuarioRepository.save(usuario);
		} catch (Exception e) {
			throw new BadRequestException(UsuarioDepartamentoEnum.MSG_USUARIO_SAVE_ERRO.getTexto());
		}
	}

	public Usuario edit(Usuario usuario) throws BadRequestException {
		Usuario u = usuarioRepository.findById(usuario.getId())
				.orElseThrow(() -> new BadRequestException(UsuarioDepartamentoEnum.MSG_USUARIO_UPDATE_ERRO.getTexto()));
		u.setNome(usuario.getNome());
		u.setDepartamento(usuario.getDepartamento());
		return usuarioRepository.save(u);
	}

	public void delete(int id) throws BadRequestException {
		try {
			usuarioRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(UsuarioDepartamentoEnum.MSG_USUARIO_DELETE_ERRO.getTexto());
		}

	}
}
