package org.openbox.sf5.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.openbox.sf5.dao.DAO;
import org.openbox.sf5.dao.DAOImpl;
import org.openbox.sf5.dao.DAOListImpl;
import org.openbox.sf5.db.ConnectionManager;
import org.openbox.sf5.db.TheDVBRangeValues;
import org.openbox.sf5.service.ObjectService;
import org.openbox.sf5.service.ObjectServiceImpl;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

public class TableFillerTest {

	private ConnectionManager cm;

	private DAO DAO;

	private ObjectService service;

	private ObjectsController contr;

	private ObjectsListService listService;

	private DAOListImpl DAOList;

	@Before
	public void setUp() {
		cm = new ConnectionManager();

		DAO = new DAOImpl();
		DAO.setCm(cm);

		DAOList = new DAOListImpl();
		DAOList.setCm(cm);

		service = new ObjectServiceImpl();
		service.setDAO(DAO);

		contr = new ObjectsController();
		contr.setService(service);

		listService = new ObjectsListService();
		listService.setDao(DAOList);

	}

	@Test
	@Transactional
	public void shouldFillTablesByTableFiller() {

		TableFiller tf = new TableFiller();
		tf.init();

		// there should be 2 records in THEDVBRANGEVALUES
		List<TheDVBRangeValues> rangesList = (List<TheDVBRangeValues>) listService.ObjectsList(TheDVBRangeValues.class);
		assertThat(rangesList.size()).isEqualTo(2);
	}

}
