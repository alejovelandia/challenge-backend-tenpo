package com.tenpo.challenge.integration;

import com.tenpo.api.model.AdditionResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties = { "properties.external-api.percentage-api.path=/getpercentage" })
@ActiveProfiles("test")
@DirtiesContext
class MathOperationITTest {

	public static final String BASE_PATH = "/math-operation";

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@Description("Percentage got from external api")
	public void whenMathOperationAddition_thenResponseSuccess() {
		final String uriAddition = BASE_PATH + "/addition?num1={param1}&num2={param2}";

		final ResponseEntity<AdditionResponse> response = this.testRestTemplate.getForEntity(
				uriAddition, AdditionResponse.class, 4L, 6L);
		final AdditionResponse body = response.getBody();

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(body);
		Assertions.assertEquals(16, body.getResult());
	}

}
