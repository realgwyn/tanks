package io.tanks.server;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ServerAppTest {
	
	@Autowired
	ApplicationContext ctx;

	@Test
	public void applicationStarts() {
		assertThat(ctx).isNotNull();
	}

}