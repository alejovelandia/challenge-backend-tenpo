package com.tenpo.challenge.integration;

import com.tenpo.api.model.ApiResponseError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties = { "properties.external-api.percentage-api.path=/getpercentage/error" })
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext
class MathOperationExceptionITTest {

	public static final String BASE_PATH = "/math-operation";

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@Description("Percentage api error and not value saved in cache")
	public void whenMathOperationAddition_thenResponseException() {
		final String uriAddition = BASE_PATH + "/addition?num1={param1}&num2={param2}";

		final ResponseEntity<ApiResponseError> response = this.testRestTemplate.getForEntity(
				uriAddition, ApiResponseError.class, 4L, 6L);
		final ApiResponseError body = response.getBody();

		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		Assertions.assertNotNull(body);
		Assertions.assertEquals("ERROR ::external api error and not value saved in cache", body.getMessage());
	}

}
