package org.openbox.sf5.json.service;

import org.openbox.sf5.dao.DAO;
import org.openbox.sf5.dao.DAOImpl;
import org.openbox.sf5.dao.DAOList;
import org.openbox.sf5.dao.DAOListImpl;
import org.openbox.sf5.db.ConnectionManager;
import org.openbox.sf5.service.CriterionService;
import org.openbox.sf5.service.ObjectService;
import org.openbox.sf5.service.ObjectServiceImpl;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

public class JsonizerTestSetup {

	public static void setUp(ConnectionManager cm, DAO DAO, DAOList DAOList, ObjectService service, ObjectsController contr, CriterionService criterionService, ObjectsListService listService, CommonJsonizer commonJsonizer) {

		cm = new ConnectionManager();
		DAO = new DAOImpl();
		DAO.setCm(cm);

		service = new ObjectServiceImpl();
		service.setDAO(DAO);

		contr = new ObjectsController();
		contr.setService(service);

		DAOList = new DAOListImpl();
		DAOList.setCm(cm);

		listService = new ObjectsListService();
		listService.setDao(DAOList);

		criterionService = new CriterionService();
		criterionService.setContr(contr);
		criterionService.setListService(listService);

		commonJsonizer = new CommonJsonizer();
		commonJsonizer.setContr(contr);

	}
}
