package br.org.cremesp.extranet.usuario.departamento.constantes;

public enum UsuarioDepartamentoEnum {

	MSG_USUARIO_FIND_ERRO("Usuário não encontrado!"), MSG_USUARIO_SAVE_ERRO("O usuário não foi salvo!"),
	MSG_USUARIO_UPDATE_ERRO("O usuário não foi atualizado!"), MSG_USUARIO_DELETE_ERRO("O usuário não foi excluído!"),

	MSG_DEPARTAMENTO_FIND_ERRO("Departamento não encontrado!"),
	MSG_DEPARTAMENTO_SAVE_ERRO("O departamento não foi salvo!"),
	MSG_DEPARTAMENTO_UPDATE_ERRO("O departamento não foi atualizado!"),
	MSG_DEPARTAMENTO_DELETE_ERRO("O departamento não foi excluído!"),

	;

	private String texto;

	private UsuarioDepartamentoEnum(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}
}
