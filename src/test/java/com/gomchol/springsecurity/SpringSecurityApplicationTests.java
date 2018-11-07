package com.gomchol.springsecurity;

import com.gomchol.springsecurity.controllers.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class SpringSecurityApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void givenRequestOnPublicPage_shouldReturn200() throws Exception {
		mvc.perform(
				get("/")
		).andExpect(
				status().isOk()
		);
	}

	@Test
	public void givenRequestOnPrivateAndAdminPageWithoutLogin_shouldReturn401() throws Exception {
		mvc.perform(
				get("/private")
		).andExpect(
				status().isUnauthorized()
		);

		mvc.perform(
				get("/admin")
		).andExpect(
				status().isUnauthorized()
		);
	}
}
