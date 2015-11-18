package org.openbox.sf5.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openbox.sf5.db.TheDVBRangeValues;
import org.openbox.sf5.json.service.AbstractJsonizerTest;

@RunWith(JUnit4.class)
public class TableFillerTests extends AbstractJsonizerTest {

	@Before
	public void setUp() {
		super.setUpAbstract();

	}

	@Test
	@Transactional
	public void shouldFillTablesByTableFiller() {

		executeTableFiller();

		// there should be 2 records in THEDVBRANGEVALUES
		List<TheDVBRangeValues> rangesList = (List<TheDVBRangeValues>) listService.ObjectsList(TheDVBRangeValues.class);
		assertThat(rangesList.size()).isEqualTo(2);
	}

	public void executeTableFiller() {
		TableFiller tf = new TableFiller();
		tf.setCm(cm);
		tf.setContr(contr);
		tf.init();

	}

}
