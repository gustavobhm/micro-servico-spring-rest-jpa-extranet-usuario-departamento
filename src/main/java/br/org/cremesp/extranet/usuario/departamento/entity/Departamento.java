package br.org.cremesp.extranet.usuario.departamento.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "DEPTO")
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
public class Departamento implements Serializable {

	private static final long serialVersionUID = 1L;

	public Departamento(Integer id, String nome, String sigla) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
	}

	@Id
	@Column(name = "ID_DEPTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NOME_DEPTO")
	private String nome;

	@Column(name = "SIGLA")
	private String sigla;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento")
	@JsonIgnore
	private List<Usuario> usuarios = new ArrayList<>();

}
