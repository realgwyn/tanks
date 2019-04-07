package io.tanks.server.service;

import static io.tanks.server.service.AuthenticationService.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class AuthenticationServiceTest {

	@Test
	public void testCheckPassword(){
		String password = "abcdefgh";
		String hash = "$2a$12$Oa31Ez1/m5L7shF8X/5yXeeltruyStk9DkSeZgvmGVt5wv8/.CPI.";

		assertThat(checkPassword(password, hash)).isTrue();
	}
	
	@Test
	public void testComputeHash(){
		String password = "abcdefgh";
		String computedHash = createPasswordHash(password);

		assertThat(checkPassword(password, computedHash)).isTrue();
	}
	
}