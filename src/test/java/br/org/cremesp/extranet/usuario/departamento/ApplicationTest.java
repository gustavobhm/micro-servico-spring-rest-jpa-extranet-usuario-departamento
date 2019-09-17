package br.org.cremesp.extranet.usuario.departamento;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.org.cremesp.extranet.usuario.departamento.Application;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Test
	public void contextLoads() {
	}

	@Test
	public void applicationStarts() {
		Application.main(new String[] {});
	}

}
