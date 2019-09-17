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
import br.org.cremesp.extranet.usuario.departamento.repository.DepartamentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class DepartamentoControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	private Gson gson = new Gson();

	@Before
	public void init() {

		Departamento departamento1 = new Departamento(1, "Departamento 1", "DP 1");
		departamentoRepository.saveAndFlush(departamento1);

		Departamento departamento2 = new Departamento(2, "Departamento 2", "DP 2");
		departamentoRepository.saveAndFlush(departamento2);

	}

	@Test
	public void getDepartamentos_ValidTest() throws Exception {
		mvc.perform(get("/departamentos") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[1].nome", is("Departamento 2")));
	}

	@Test
	public void getByIdDepartamento_ValidTest() throws Exception {
		mvc.perform(get("/departamentos/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.id", is(1))) //
				.andExpect(jsonPath("$.nome", is("Departamento 1")));
	}

	@Test
	public void getByIdDepartamento_InvalidTest() throws Exception {
		mvc.perform(get("/departamentos/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void addDepartamento_ValidTest() throws Exception {

		Departamento departamento = new Departamento(3, "Departamento 3", "DP 3");

		mvc.perform(post("/departamentos") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(departamento))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateDepartamento_ValidTest() throws Exception {

		Departamento departamento = new Departamento(2, "Departamento 2 Update", "DP 2");

		mvc.perform(put("/departamentos") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(departamento))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateDepartamento_InvalidTest() throws Exception {

		Departamento departamento = new Departamento(3, "Departamento 3", "DP 3");

		mvc.perform(put("/departamentos") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(departamento))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteDepartamento_ValidTest() throws Exception {
		mvc.perform(delete("/departamentos/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk());
	}

	@Test
	public void deleteDepartamento_InvalidTest() throws Exception {
		mvc.perform(delete("/departamentos/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

}
