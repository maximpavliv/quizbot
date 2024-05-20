package ua.max.quizbot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuizbotApplicationTests {

	private MockMvc mvc;

	@Autowired
	public QuizbotApplicationTests(MockMvc mvc) {
		this.mvc = mvc;
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void postInvalidUpdate() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void postValidUpdate() throws Exception {
		String jsonBody =
				"{" +
					"\"update_id\":0," +
					"\"message\":{" +
						"\"message_id\":0,"+
						"\"from\":{\"id\":0,\"is_bot\":false,\"first_name\":\"Test\",\"language_code\":\"en\"}," +
						"\"chat\":{\"id\":0,\"first_name\":\"Test\",\"type\":\"private\"}," +
						"\"date\":1714931245," +
						"\"text\":\"Test text\"" +
					"}" +
				"}";

		mvc.perform(MockMvcRequestBuilders.post("/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonBody)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
