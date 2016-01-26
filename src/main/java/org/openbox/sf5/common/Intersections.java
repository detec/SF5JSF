package org.openbox.sf5.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.ProgressDialect;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.jdbc.ReturningWork;
import org.openbox.sf5.db.ConnectionManager;
import org.openbox.sf5.model.Settings;
import org.openbox.sf5.model.SettingsConversion;

@Named
@SessionScoped
public class Intersections implements Serializable {

	public ConnectionManager getCm() {
		return cm;
	}

	public void setCm(ConnectionManager cm) {
		this.cm = cm;
	}

	private static final long serialVersionUID = -8627178893457939969L;

	@Inject
	private ConnectionManager cm;

	public int checkIntersection(List<SettingsConversion> dataSettingsConversion, Settings Object) throws SQLException {

		ReturningWork<ResultSet> rowsReturningWork = new ReturningWork<ResultSet>() {

			@Override
			public ResultSet execute(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;

				// syntax changed due to H2 and Postgre limitations.

				// http://stackoverflow.com/questions/1571928/retrieve-auto-detected-hibernate-dialect
				Dialect dialect = ((SessionFactoryImplementor) cm.getSessionFactroy()).getDialect();

				String tempTableDrop = dialect.getDropTemporaryTableString();

				// drop tables
				try {
					preparedStatement = connection.prepareStatement(getDropTempTables(dialect));
					preparedStatement.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					// fill temp tables
					preparedStatement = connection.prepareStatement(fillTempTables(dialect));
					preparedStatement.setLong(1, Object.getId());
					preparedStatement.execute();

					preparedStatement = connection.prepareStatement(getIntersectionQuery());
					// preparedStatement.setLong(1, Object.getId());
					resultSet = preparedStatement.executeQuery();

					// 11.08.2015, trying to remove locks
					// this removes lock from sys and temp tables.
					connection.commit();
					// 11.08.2015

					return resultSet;
				} catch (SQLException e) {
					throw e;
				}
				/*
				 * finally { preparedStatement.close(); resultSet.close();
				 * connection.close();
				 *
				 * }
				 */

			}
		};

		Session session = cm.getSessionFactroy().openSession();

		ResultSet rs = null;
		rs = session.doReturningWork(rowsReturningWork);

		List<Integer> arrayLines = new ArrayList<Integer>();
		while (rs.next())

		{

			int rowIndex = new BigDecimal(
					// 11.08.2015, there seems to be a bug in defining rows.
					rs.getLong("LineNumber")).intValueExact();

			arrayLines.add(new Integer(rowIndex));

			SettingsConversion sc = dataSettingsConversion.get(rowIndex);

			long IntersectionValue = rs.getLong("TheLineOfIntersection");

			sc.setTheLineOfIntersection(IntersectionValue + 1);

		}

		rs.close();

		// remove duplicates
		Set<Integer> hs = new HashSet<>();
		hs.addAll(arrayLines);
		arrayLines.clear();
		arrayLines.addAll(hs);
		// return rows;

		session.close();

		return arrayLines.size();

	}

	public static String getDropTempTables(Dialect dialect) {
		String returnString = "";
		if (dialect instanceof H2Dialect) {
			returnString = "\n" + "DROP TABLE  CONVERSIONTABLE IF EXISTS; \n"
					+ "DROP TABLE  ManyFrequencies IF EXISTS; \n" + "DROP TABLE  IntersectionTable IF EXISTS; \n";
		}

		else if (dialect instanceof ProgressDialect) {
			returnString = "\n" + "DROP TABLE IF EXISTS CONVERSIONTABLE; \n"
					+ "DROP TABLE  IF EXISTS ManyFrequencies;  \n" + "DROP TABLE  IF EXISTS IntersectionTable; \n";
		}
		return returnString;

	}

	public static String fillTempTables(Dialect dialect) {
		// Adjusting to different ddatabase dialects.

		// return "\n" + "CREATE MEMORY TEMPORARY TABLE CONVERSIONTABLE AS ( \n"
		// + "SELECT \n" + "LineNumber \n"
		// return

		// http://stackoverflow.com/questions/1915074/understanding-the-in-javas-format-strings
		String tempTableCreate = dialect.getCreateTemporaryTableString();
		String fromatString = "\n" + "%1$s CONVERSIONTABLE  AS ( \n" + "SELECT \n" + "LineNumber \n"

				+ ", tp.frequency \n"

				+ ", 0 as TheLineOfIntersection \n"

		// + " into #ConversionTable \n"

				+ "	FROM SettingsConversion conv \n"

				+ "inner join Transponders tp \n"

				+ "on conv.Transponder = tp.id \n"

				+ " where parent_id = ? \n"

				+ " ); \n"

		// + "CREATE MEMORY TEMPORARY TABLE ManyFrequencies AS ( \n"
				+ "%1$s ManyFrequencies AS (  \n"

				+ "select \n" + "p1.LineNumber \n" + ", p1.frequency \n" + ", p1.TheLineOfIntersection \n"
				// + "into #ManyFrequencies \n"

				+ "from ConversionTable p1  \n"

				+ "union  \n"

				+ "select  \n" + "p2.LineNumber  \n" + ", p2.frequency + 1 \n"
				+ ", p2.TheLineOfIntersection AS  TheLineOfIntersection \n" + "from ConversionTable p2 \n"

				+ "union \n"

				+ "select \n" + "p3.LineNumber \n" + ", p3.frequency - 1 \n" + ", p3.TheLineOfIntersection \n"
				+ "from ConversionTable p3 \n"

				+ " ); \n"

		// + "CREATE MEMORY TEMPORARY TABLE IntersectionTable AS ( \n"
				+ "%1$s IntersectionTable AS (   \n"

				+ "select \n" + "t1.LineNumber \n" + ", t1.frequency \n" + ", t2.LineNumber as TheLineOfIntersection \n"
				// + "into #IntersectionTable \n"
				+ "from ManyFrequencies t1 \n" + "inner join ManyFrequencies t2 \n"
				+ "on t1.frequency = t2.frequency \n" + "and t1.LineNumber <> t2.LineNumber \n"

				+ " ); \n";

		return String.format(fromatString, tempTableCreate);

	}

	public static String getIntersectionQuery() {

		return "\n"
				// + "DROP TABLE CONVERSIONTABLE IF EXISTS; \n"
				// + "DROP TABLE ManyFrequencies IF EXISTS; \n"
				// + "DROP TABLE IntersectionTable IF EXISTS; \n"

				// + "SET NOCOUNT ON \n"
				// +
				// "IF OBJECT_ID('tempdb..#ConversionTable') IS NOT NULL DROP
				// Table #ConversionTable \n"
				// +
				// "IF OBJECT_ID('tempdb..#ManyFrequencies') IS NOT NULL DROP
				// Table #ManyFrequencies \n"
				// +
				// "IF OBJECT_ID('tempdb..#IntersectionTable') IS NOT NULL DROP
				// Table #IntersectionTable \n"

				// + "CREATE MEMORY TEMPORARY TABLE CONVERSIONTABLE AS ( \n"
				// + "SELECT \n"
				// + "LineNumber \n"
				//
				// + ", tp.frequency \n"
				//
				// + ", 0 as TheLineOfIntersection \n"
				//
				// //+ " into #ConversionTable \n"
				//
				// + " FROM SettingsConversion conv \n"
				//
				// + "inner join Transponders tp \n"
				//
				// + "on conv.Transponder = tp.id \n"
				//
				// + " where parent_id = ? \n"
				//
				// + " ); \n"
				//
				// + "CREATE MEMORY TEMPORARY TABLE ManyFrequencies AS ( \n"
				//
				// + "select \n"
				// + "p1.LineNumber \n"
				// + ", p1.frequency \n"
				// + ", p1.TheLineOfIntersection \n"
				// //+ "into #ManyFrequencies \n"
				//
				// + "from #ConversionTable p1 \n"
				//
				// + "union \n"
				//
				// + "select \n"
				// + "p2.LineNumber \n"
				// + ", p2.frequency + 1 \n"
				// + ", p2.TheLineOfIntersection AS TheLineOfIntersection \n"
				// + "from #ConversionTable p2 \n"
				//
				// + "union \n"
				//
				// + "select \n"
				// + "p3.LineNumber \n"
				// + ", p3.frequency - 1 \n"
				// + ", p3.TheLineOfIntersection \n"
				// + "from #ConversionTable p3 \n"
				//
				// + " ); \n"
				//
				// + "CREATE MEMORY TEMPORARY TABLE IntersectionTable AS ( \n"
				//
				// + "select \n"
				// + "t1.LineNumber \n"
				// + ", t1.frequency \n"
				// + ", t2.LineNumber as TheLineOfIntersection \n"
				// //+ "into #IntersectionTable \n"
				// + "from #ManyFrequencies t1 \n"
				// + "inner join #ManyFrequencies t2 \n"
				// + "on t1.frequency = t2.frequency \n"
				// + "and t1.LineNumber <> t2.LineNumber \n"
				//
				// + " ); \n"

				+ "select distinct \n" + "conv.LineNumber \n"
				// + ", ISNULL(inter.TheLineOfIntersection, 0) AS
				// TheLineOfIntersection \n"
				+ ", COALESCE(inter.TheLineOfIntersection, 0) AS TheLineOfIntersection \n"

				+ "from ConversionTable conv \n" + "inner join IntersectionTable inter \n"
				+ "on inter.LineNumber = conv.LineNumber \n"

				+ "order by \n" + "conv.LineNumber \n"

		// + "DROP TABLE #ConversionTable \n"
		// + "DROP TABLE #ManyFrequencies \n"
		// + "DROP TABLE #IntersectionTable \n"
				+ "";

	}

}
