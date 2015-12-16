package org.openbox.sf5.json.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openbox.sf5.model.Users;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BUsersJsonizerTests extends AbstractJsonizerTest {

	@Test
	public void shouldCreateNewUser() {
		// here we create user
		Users testUser = new Users("Test user", testUsername);
		int status = usersJsonizer.saveNewUser(testUser);
		assertEquals(201, status);
	}

	@Test
	public void shouldTestfUsernameExists() {
		long userId = usersJsonizer.checkIfUsernameExists(testUsername);
		assertThat(userId).isGreaterThan(0);
	}

	@Test
	public void shouldGetUserByLogin() throws JsonParseException, JsonMappingException, IOException {
		String result = usersJsonizer.getUserByLogin(testUsername);

		Users readUser = mapper.readValue(result, Users.class);
		assertThat(readUser).isNotNull();
	}

	@Before
	public void setUp() {

		super.setUpAbstract();

		usersJsonizer = new UsersJsonizer();
		usersJsonizer.setCommonJsonizer(commonJsonizer);
		usersJsonizer.setCriterionService(criterionService);
		usersJsonizer.setListService(listService);
		usersJsonizer.setObjectsController(objectsController);
	}

	private UsersJsonizer usersJsonizer;
}
