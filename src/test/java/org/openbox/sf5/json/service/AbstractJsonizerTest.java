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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

// This class is intended for routine work to setup dao and service layers.
public abstract class AbstractJsonizerTest {
	public ConnectionManager cm;

	public DAO DAO;

	public DAOList DAOList;

	public ObjectService service;

	public ObjectsController contr;

	public CriterionService criterionService;

	public ObjectsListService listService;

	public CommonJsonizer commonJsonizer;

	public ObjectMapper mapper = new ObjectMapper();

	public void setUpAbstract() {

		cm = new ConnectionManager();
		cm.disableLogsWhenTesting(); // to disable infos and warnings.

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

		// mapper.configure(SerializationFeature.WRITE_NULL_PROPERTIES,
		// true);
		mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		// mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	}

}
