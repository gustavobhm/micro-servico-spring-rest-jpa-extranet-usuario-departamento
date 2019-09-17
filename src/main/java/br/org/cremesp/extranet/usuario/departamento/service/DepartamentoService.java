package br.org.cremesp.extranet.usuario.departamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.extranet.usuario.departamento.constantes.UsuarioDepartamentoEnum;
import br.org.cremesp.extranet.usuario.departamento.entity.Departamento;
import br.org.cremesp.extranet.usuario.departamento.exception.BadRequestException;
import br.org.cremesp.extranet.usuario.departamento.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	public List<Departamento> getAll() {
		return departamentoRepository.findAllByOrderByIdAsc();
	}

	public Departamento get(int id) throws BadRequestException {
		return departamentoRepository.findById(id).orElseThrow(
				() -> new BadRequestException(UsuarioDepartamentoEnum.MSG_DEPARTAMENTO_FIND_ERRO.getTexto()));
	}

	public Departamento add(Departamento departamento) {
		return departamentoRepository.save(departamento);
	}

	public Departamento edit(Departamento departamento) throws BadRequestException {
		Departamento d = departamentoRepository.findById(departamento.getId()).orElseThrow(
				() -> new BadRequestException(UsuarioDepartamentoEnum.MSG_DEPARTAMENTO_UPDATE_ERRO.getTexto()));
		d.setNome(departamento.getNome());
		d.setSigla(departamento.getSigla());
		return departamentoRepository.save(d);
	}

	public void delete(int id) throws BadRequestException {
		try {
			departamentoRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(UsuarioDepartamentoEnum.MSG_DEPARTAMENTO_DELETE_ERRO.getTexto());
		}

	}
}
