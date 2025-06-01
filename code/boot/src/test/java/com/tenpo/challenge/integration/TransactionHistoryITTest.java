package com.tenpo.challenge.integration;

import com.tenpo.api.model.PaginatedTransactionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext
class TransactionHistoryITTest {

	public static final String BASE_PATH = "/transaction";

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@Description("Return paginated transaction history")
	public void whenTransactionHistory_thenReturnSuccess() {
		final String finalUri = BASE_PATH + "/history?offset={param1}&limit={param2}";

		final ResponseEntity<PaginatedTransactionResponse> response = this.testRestTemplate.getForEntity(
				finalUri, PaginatedTransactionResponse.class, 0, 2);
		final PaginatedTransactionResponse body = response.getBody();

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(body);
		Assertions.assertEquals(2, body.getData().size());
		Assertions.assertEquals("2025-06-01T07:33:55.155Z", body.getData().getFirst().getCreationDate().toString());
		Assertions.assertEquals("/math-operation/addition", body.getData().getFirst().getEndpoint());
		Assertions.assertEquals("{\"num1\":\"2\",\"num2\":\"4\"}", body.getData().getFirst().getParameters());
		Assertions.assertEquals("{\"result\":16}", body.getData().getFirst().getResponse());

		Assertions.assertEquals("2025-06-01T09:36:34.581Z", body.getData().get(1).getCreationDate().toString());
		Assertions.assertEquals("/math-operation/addition", body.getData().get(1).getEndpoint());
		Assertions.assertEquals("{\"num1\":\"4\",\"num2\":\"6\"}", body.getData().get(1).getParameters());
		Assertions.assertEquals("{\"code\":\"404\",\"message\":\"ERROR ::external api error and not value saved in cache\"}",
				body.getData().get(1).getResponse());


	}

}
