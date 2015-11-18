package org.openbox.sf5.json.service;

import org.openbox.sf5.dao.DAO;
import org.openbox.sf5.dao.DAOList;
import org.openbox.sf5.db.ConnectionManager;
import org.openbox.sf5.service.CriterionService;
import org.openbox.sf5.service.ObjectService;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

public abstract class AbstractJsonizerTest {
	 public ConnectionManager cm;

	 public DAO DAO;

	 public DAOList DAOList;

	 public ObjectService service;

	 public ObjectsController contr;

	 public CriterionService criterionService;

	 public ObjectsListService listService;

	 public CommonJsonizer commonJsonizer;

}
