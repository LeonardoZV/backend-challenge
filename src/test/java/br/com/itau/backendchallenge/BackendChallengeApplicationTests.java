package br.com.itau.backendchallenge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendChallengeApplicationTests {

	@Test
	void contextLoads() {
		BackendChallengeApplication backendChallengeApplication = new BackendChallengeApplication();
		backendChallengeApplication.main(new String[] {});
	}

}
