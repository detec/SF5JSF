package org.openbox.sf5.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

// Using Hibernate Validator instead of Spring
public class ValidatorTests {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void shouldNotValidateWhenFieldsEmpty() {

		Settings setting = new Settings();
		setting.setName("");

		Set<ConstraintViolation<Settings>> constraintViolations = validator.validate(setting);
		assertThat(constraintViolations.size()).isEqualTo(2);

		Iterator<ConstraintViolation<Settings>> settingIterator = constraintViolations.iterator();

		// check user
		ConstraintViolation<Settings> violation = settingIterator.next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("User");
		assertThat(violation.getMessage()).isEqualTo("may not be null");

		violation = settingIterator.next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("Name");
		assertThat(violation.getMessage()).isEqualTo("may not be empty");

		Transponders trans = new Transponders();

		// first round. All fields are empty
		Set<ConstraintViolation<Transponders>> trsnapondersConstraintViolations = validator.validate(trans);
		assertThat(trsnapondersConstraintViolations.size()).isEqualTo(7);
		// we move iterator in the cycle
		// ConstraintViolation<Transponders> transViolation =
		// trsnapondersConstraintViolations.iterator().next();

		Iterator<ConstraintViolation<Transponders>> iterator = trsnapondersConstraintViolations.iterator();

		HashMap<String, String> valuesmap = new HashMap<String, String>();
		valuesmap.put("RangeOfDVB", "may not be null");
		valuesmap.put("Carrier", "may not be null");
		valuesmap.put("Satellite", "may not be null");
		valuesmap.put("Polarization", "may not be null");
		valuesmap.put("VersionOfTheDVB", "may not be null");
		valuesmap.put("Speed", "must be greater than or equal to 1000");
		valuesmap.put("Frequency", "must be greater than or equal to 2000");

		while (iterator.hasNext()) {

			ConstraintViolation<Transponders> transViolation = iterator.next();
			String propertyPath = transViolation.getPropertyPath().toString();
			assertThat(valuesmap.get(propertyPath).equals(transViolation.getMessage()));

		}

	}
}
