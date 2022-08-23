package br.com.itau.backendchallenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendChallengeApplicationIntegrationTest {

	@Test
	void contextLoads() {
		Assertions.assertDoesNotThrow(() -> BackendChallengeApplication.main(new String[] {}));
	}

}
