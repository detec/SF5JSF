package org.openbox.sf5.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.openbox.sf5.db.CarrierFrequency;
import org.openbox.sf5.db.DVBStandards;
import org.openbox.sf5.db.Polarization;
import org.openbox.sf5.db.RangesOfDVB;
import org.openbox.sf5.db.Satellites;
import org.openbox.sf5.db.Settings;
import org.openbox.sf5.db.SettingsConversion;
import org.openbox.sf5.db.Transponders;
import org.openbox.sf5.db.TypesOfFEC;
import org.openbox.sf5.db.Users;

public class AbstractServiceTests {

	protected ObjectsController contr = new ObjectsController();

	@Test
	@Transactional
	public void shouldInsertSatellite() {

		Satellites newSat = getNewSatellite();
		contr.saveOrUpdate(newSat);
		assertThat(newSat.getId()).isNotEqualTo(0);
	}

	@Test
	@Transactional
	public void shouldInsertTransponder() {
		Transponders trans = getNewTransponder();
		contr.saveOrUpdate(trans);
		assertThat(trans.getId()).isNotEqualTo(0);

	}

	@Test
	@Transactional
	public void shouldInsertSetting() {
		Settings setting = getNewSetting();
		contr.saveOrUpdate(setting);
		assertThat(setting.getId()).isNotEqualTo(0);
	}

	private Satellites getNewSatellite() {
		Satellites newSat = new Satellites();
		newSat.setName("Test sat");

		return newSat;
	}

	private Transponders getNewTransponder() {
		Satellites newSat = getNewSatellite();
		contr.saveOrUpdate(newSat);

		Transponders trans = new Transponders();
		trans.setCarrier(CarrierFrequency.Top);
		trans.setFEC(TypesOfFEC._23);
		trans.setFrequency(11555);
		trans.setPolarization(Polarization.V);
		trans.setRangeOfDVB(RangesOfDVB.Ku);
		trans.setSatellite(newSat);
		trans.setSpeed(10000);
		trans.setVersionOfTheDVB(DVBStandards.DVBS2);

		return trans;
	}

	private SettingsConversion getNewSettingsConversionLine() {
		Transponders trans = getNewTransponder();
		trans.setSpeed(10500);
		contr.saveOrUpdate(trans);

		SettingsConversion sc = new SettingsConversion();
		sc.setLineNumber(1);
		sc.setNote("First");
		sc.setSatindex(1);
		sc.setTpindex(2);
		sc.setTransponder(trans);

		return sc;
	}

	private Users getNewUser() {
		Users user = new Users();
		// user.setenabled(true);
		// user.setPassword("1");
		// user.setusername("testuser");
		user.setLogin("test");
		user.setName("test user");
		return user;
	}

	private Settings getNewSetting() {
		Users user = getNewUser();
		contr.saveOrUpdate(user);

		Settings setting = new Settings();
		setting.setName("Test");
		setting.setUser(user);

		List<SettingsConversion> scList = new ArrayList<SettingsConversion>();

		SettingsConversion sc1 = getNewSettingsConversionLine();
		sc1.setLineNumber(1);
		sc1.setparent_id(setting);

		SettingsConversion sc2 = getNewSettingsConversionLine();
		sc2.setLineNumber(2);
		sc2.setparent_id(setting);
		sc2.setNote("Useful");

		scList.add(sc1);
		scList.add(sc2);
		setting.setConversion(scList);

		return setting;
	}
}
