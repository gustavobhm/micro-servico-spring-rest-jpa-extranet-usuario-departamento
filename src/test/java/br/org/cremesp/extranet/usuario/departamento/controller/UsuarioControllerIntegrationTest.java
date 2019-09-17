package br.org.cremesp.extranet.usuario.departamento.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import br.org.cremesp.extranet.usuario.departamento.Application;
import br.org.cremesp.extranet.usuario.departamento.entity.Departamento;
import br.org.cremesp.extranet.usuario.departamento.entity.Usuario;
import br.org.cremesp.extranet.usuario.departamento.repository.DepartamentoRepository;
import br.org.cremesp.extranet.usuario.departamento.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class UsuarioControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	private Gson gson = new Gson();

	@Before
	public void init() {

		Departamento departamento = new Departamento(null, "Dapartamento 1", "DP 1");
		departamentoRepository.saveAndFlush(departamento);

		Usuario usuario1 = new Usuario(1, "Usuário 1", departamento);
		usuarioRepository.saveAndFlush(usuario1);

		Usuario usuario2 = new Usuario(2, "Usuário 2", departamento);
		usuarioRepository.saveAndFlush(usuario2);

	}

	@Test
	public void getUsuarios_ValidTest() throws Exception {
		mvc.perform(get("/usuarios") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[1].nome", is("Usuário 2")));
	}

	@Test
	public void getByIdUsuario_ValidTest() throws Exception {
		mvc.perform(get("/usuarios/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.id", is(1))) //
				.andExpect(jsonPath("$.nome", is("Usuário 1")));
	}

	@Test
	public void getByIdUsuario_InvalidTest() throws Exception {
		mvc.perform(get("/usuarios/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void addUsuario_ValidTest() throws Exception {

		Usuario usuario = new Usuario(3, "Usuário 3", new Departamento(1, null, null));

		mvc.perform(post("/usuarios") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(usuario))) //
				.andExpect(status().isOk());
	}

	@Test
	public void addUsuario_InvalidTest() throws Exception {

		Usuario usuario = new Usuario(null, "Usuário 2", new Departamento(2, null, null));

		mvc.perform(post("/usuarios") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(usuario))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateUsuario_ValidTest() throws Exception {

		Usuario usuario = new Usuario(2, "Usuário 2 Update", new Departamento(1, null, null));

		mvc.perform(put("/usuarios") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(usuario))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateUsuario_InvalidTest() throws Exception {

		Usuario usuario = new Usuario(3, "Usuário 3", new Departamento(1, null, null));

		mvc.perform(put("/usuarios") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(usuario))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteUsuario_ValidTest() throws Exception {
		mvc.perform(delete("/usuarios/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk());
	}

	@Test
	public void deleteUsuario_InvalidTest() throws Exception {
		mvc.perform(delete("/usuarios/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

}
