package org.openbox.sf5.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

//import org.apache.commons.lang3.text.StrBuilder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.internal.TypeLocatorImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.EnumType;
import org.hibernate.type.Type;
import org.hibernate.type.TypeResolver;
import org.openbox.sf5.db.CarrierFrequency;
import org.openbox.sf5.db.ConnectionManager;
import org.openbox.sf5.db.DVBStandards;
import org.openbox.sf5.db.Polarization;
import org.openbox.sf5.db.RangesOfDVB;
import org.openbox.sf5.db.Satellites;
import org.openbox.sf5.db.TheDVBRangeValues;
import org.openbox.sf5.db.Transponders;
import org.openbox.sf5.db.TypesOfFEC;
import org.openbox.sf5.db.ValueOfTheCarrierFrequency;
import org.openbox.sf5.service.ObjectsController;

@Named("IniReader")
@SessionScoped
public class IniReader implements Serializable {

	public IniReader(String filepath) {
		this.filepath = filepath;
	}

	// for injection we need empty constructor
	public IniReader() {

	}

	public ObjectsController getContr() {
		return contr;
	}

	public void setContr(ObjectsController contr) {
		this.contr = contr;
	}

	public ConnectionManager getCm() {
		return cm;
	}

	public void setCm(ConnectionManager cm) {
		this.cm = cm;
	}

	public void readData() throws IOException {

		// Open the file
		FileInputStream fstream = new FileInputStream(filepath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		// (\d{1,3})=(\d{5}),(H|V|L|R),(\d{4,5}),(\d{2,3}),(DVB-S|S2),(QPSK|8PSK)(\sACM)?

		// Read File Line By Line

		while ((strLine = br.readLine()) != null) {

			// Print the content on the console
			// System.out.println (strLine);

			if (strLine.equals("[SATTYPE]")) {
				readSatData(br);
			}

			if (strLine.equals("[DVB]")) {
				readTransponderData(br);
			}

		}

		// System.out.println("Done!");
		// Alert alert = new Alert(AlertType.INFORMATION);
		// alert.setTitle("Information");
		// alert.setHeaderText("INI import result");
		// alert.setContentText("Import success!");
		// alert.showAndWait();

		// Close the input stream

		br.close();

		result = true;
	}

	private void readSatData(BufferedReader br) throws IOException {

		br.readLine(); // 1=0130
		String satline = br.readLine();
		// In web server, Glassfish 4, it cannot work with Apache commons.
		// We will replace with Java Core methods.
		// StrBuilder aStrBuilder = new StrBuilder(satline);

		// String satName = aStrBuilder.substring(2);

		String satName = satline.substring(2); // 2 characters

		String hql = "select id from Satellites where name = :name";
		// Session s = HibernateUtil.openSession();

		Session s = cm.getSessionFactroy().openSession();

		Query query = s.createQuery(hql);
		query.setParameter("name", satName);
		ArrayList<Long> rs = (ArrayList<Long>) query.list();

		if (rs.isEmpty()) {
			// no satellite found
			sat = new Satellites(satName);

			// saving satellite
			contr.saveOrUpdate(sat);
		} else {
			// get sat
			sat = contr.select(Satellites.class, rs.get(0));
		}
	}

	private void readTransponderData(BufferedReader br) throws IOException {

		Transponders selectedTrans = null;
		// replace with Java core

		// String transCountString = new StrBuilder(br.readLine()).substring(2);
		String transCountString = br.readLine().substring(2);

		int transCount = Integer.parseInt(transCountString);

		// List<Transponders> transList = new ArrayList<Transponders>();

		pattern = Pattern.compile(REGEX);

		for (int i = 1; i <= transCount; i++) {
			String transDataString = br.readLine();

			// Initialize

			matcher = pattern.matcher(transDataString);
			RangesOfDVB rangeEnum = null;
			CarrierFrequency carrierEnum = null;

			// 62=11919,V,27500,23,S2,8PSK ACM/VCM
			int count = 0;
			while (matcher.find()) {
				count++;

				// name will be transponder number in
				// String Name = matcher.group(1);

				// let's check, that it isn't Multistream
				String Multistream = matcher.group(8);
				if (Multistream != null) {
					continue;
				}

				// frequency
				String FrequencyString = matcher.group(2);
				// System.out.println(FrequencyString);
				Long Frequency = Long.parseLong(FrequencyString);

				// polarization
				Polarization aPolarization = Polarization.valueOf(matcher.group(3));

				// speed
				Long Speed = Long.parseLong(matcher.group(4));

				// FEC
				TypesOfFEC FEC = TypesOfFEC.valueOf("_" + matcher.group(5));

				// DVB standard
				DVBStandards DVBStandard = null;
				String Standard = matcher.group(6);
				if (Standard.equals("DVB-S")) {
					DVBStandard = DVBStandards.DVBS;
				}

				if (Standard.equals("S2")) {
					DVBStandard = DVBStandards.DVBS2;
				}

				// define range

				Properties params = new Properties();
				params.put("enumClass", "org.openbox.sf5.db.RangesOfDVB");
				params.put("type",
						"12"); /*
								 * type 12 instructs to use the String
								 * representation of enum value
								 */
				Type myEnumType = new TypeLocatorImpl(new TypeResolver()).custom(EnumType.class, params);

				String sqltext = "SELECT RangeOfDVB FROM TheDVBRangeValues where :Frequency between LowerThreshold and UpperThreshold";
				// Session s = HibernateUtil.openSession();

				Session session = cm.getSessionFactroy().openSession();

				List<TheDVBRangeValues> range = session.createSQLQuery(sqltext).addScalar("RangeOfDVB", myEnumType)
						.setParameter("Frequency", Frequency)
						.setResultTransformer(Transformers.aliasToBean(TheDVBRangeValues.class)).list();

				if (!range.isEmpty()) {
					rangeEnum = range.get(0).getRangeOfDVB();
				} else {
					continue;
				}

				// get carrier frequency
				params = new Properties();
				params.put("enumClass", "org.openbox.sf5.db.CarrierFrequency");
				params.put("type",
						"12"); /*
								 * type 12 instructs to use the String
								 * representation of enum value
								 */
				myEnumType = new TypeLocatorImpl(new TypeResolver()).custom(EnumType.class, params);

				sqltext = "SELECT TypeOfCarrierFrequency FROM ValueOfTheCarrierFrequency "
						+ "where (:Frequency between LowerThreshold and UpperThreshold) "
						+ "and (Polarization = :KindOfPolarization)";

				session = cm.getSessionFactroy().openSession();

				List<ValueOfTheCarrierFrequency> carrierList = session.createSQLQuery(sqltext)
						.addScalar("TypeOfCarrierFrequency", myEnumType).setParameter("Frequency", Frequency)
						.setParameter("KindOfPolarization", Polarization.getPolarizationKind(aPolarization).ordinal())
						.setResultTransformer(Transformers.aliasToBean(ValueOfTheCarrierFrequency.class)).list();

				if (!carrierList.isEmpty()) {
					carrierEnum = carrierList.get(0).getTypeOfCarrierFrequency();
				} else {
					continue;
				}

				// let's check if such frequency already exists on the given
				// satellite
				sqltext = "Select id FROM Transponders where frequency = :Frequency and satellite = :satelliteId";

				List<Object> transIdList = new ArrayList<>();
				transIdList = session.createSQLQuery(sqltext)
						// .addScalar("id")
						.setParameter("Frequency", Frequency).setParameter("satelliteId", sat.getId())
						// .setResultTransformer(Transformers.aliasToBean(Transponders.class))
						.list();

				Transponders newTrans = new Transponders(Frequency, aPolarization, FEC, carrierEnum, Speed, DVBStandard,
						rangeEnum, sat);

				if (transIdList.isEmpty()) {
					contr.saveOrUpdate(newTrans);
				}

				else {

					long transId = ((BigInteger) transIdList.get(0)).longValue();
					selectedTrans = contr.select(Transponders.class, transId);

					// check if this trans changed to newly read trans
					if (!selectedTrans.equals(newTrans)) {
						// we should update all properties of the selected
						// trans.
						selectedTrans.setCarrier(carrierEnum);
						selectedTrans.setFEC(FEC);
						selectedTrans.setFrequency(Frequency);
						selectedTrans.setPolarization(aPolarization);
						selectedTrans.setRangeOfDVB(rangeEnum);
						selectedTrans.setSatellite(sat);
						selectedTrans.setSpeed(Speed);
						selectedTrans.setVersionOfTheDVB(DVBStandard);
						contr.update(selectedTrans);
					}

				}

				session.close();
			}

		}
	}

	@Inject
	private ObjectsController contr;

	@Inject
	private ConnectionManager cm;

	private static final long serialVersionUID = -1699774508872380035L;

	private Satellites sat;

	final String REGEX = "(\\d{1,3})=(\\d{5}),(H|V|L|R),(\\d{4,5}),(\\d{2}),(DVB-S|S2),(QPSK|8PSK)(\\sACM)?";
	private static Pattern pattern;
	private static Matcher matcher;

	private boolean result = false;

	private String filepath;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
}
