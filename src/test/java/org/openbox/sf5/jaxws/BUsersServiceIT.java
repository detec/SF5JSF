package org.openbox.sf5.jaxws;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openbox.sf5.wsmodel.Users;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BUsersServiceIT extends AbstractWSTest {

	@Test
	public void shouldCheckCreateTestLogin() {

		long userId = SF5Port.ifSuchLoginExists(testUsername);
		if (userId > 0) {
			return;
		}

		// here we create user
		Users testUser = new Users();
		testUser.setLogin(testUsername);
		testUser.setName("Test WS User");

		userId = SF5Port.createUser(testUser);
		assertThat(userId).isNotZero();
	}

	@Test
	public void loginShouldNotBeFound() {
		long userId = SF5Port.ifSuchLoginExists("loginxxf");
		assertEquals(0, userId);

	}

	@Before
	public void setUp() throws Exception {
		setUpAbstract();
	}

}
