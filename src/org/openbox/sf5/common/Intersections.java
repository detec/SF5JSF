package org.openbox.sf5.common;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.openbox.sf5.application.SettingsFormController.SettingsConversionPresentation;
import org.openbox.sf5.db.HibernateUtil;
import org.openbox.sf5.db.Settings;
import org.openbox.sf5.db.SettingsConversion;

public class Intersections {
	
    public static void checkIntersection(ObservableList<SettingsConversionPresentation> dataSettingsConversion
    		, Settings Object) throws SQLException {
    	
    	ReturningWork<ResultSet> rowsReturningWork = new ReturningWork<ResultSet>() {

            @Override
            public ResultSet execute(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                try {
                	
                	// syntax changed due to H2 limitations.
                	
                	// drop tables
                	preparedStatement = connection.prepareStatement(getDropTempTables());
                	preparedStatement.execute();
                	
                	// fill temp tables
                	preparedStatement = connection.prepareStatement(fillTempTables());
                	preparedStatement.setLong(1, Object.getId());
                	preparedStatement.execute();
                	
                    preparedStatement = connection.prepareStatement(getIntersectionQuery());
                    //preparedStatement.setLong(1, Object.getId());
                    resultSet = preparedStatement.executeQuery();
             
                    return resultSet;
                }catch (SQLException e) {
                    throw e;
                } finally {

                }

            }
        };

        Session session = HibernateUtil.openSession();
        ResultSet rs = null;
        rs = session.doReturningWork(rowsReturningWork);
        
        int rows = 0;
		while (rs.next()) {
			SettingsConversion sc = dataSettingsConversion.get(new BigDecimal(rs.getLong("LineNumber")).intValueExact()); 
			
			sc.setTheLineOfIntersection(rs.getLong("TheLineOfIntersection"));
			rows++;
		
		}
		
		 
		rs.close();
		
	//	refreshSettingsConversionTableview();
		
  		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("Transponders intersection result");
		alert.setContentText("Problem transponders: " + String.valueOf(rows));
		alert.showAndWait();
    }
	
    public static String getDropTempTables() {
    	return "\n"
   			+ "DROP TABLE  CONVERSIONTABLE IF EXISTS; \n"
   			+ "DROP TABLE  ManyFrequencies IF EXISTS; \n"
   			+ "DROP TABLE  IntersectionTable IF EXISTS; \n";

    			
    }
    
    public static String fillTempTables() {
    	return "\n"
				+ "CREATE MEMORY TEMPORARY TABLE CONVERSIONTABLE  AS ( \n"
    			+ "SELECT \n"
    			+ "LineNumber \n"

    			+ ", tp.frequency \n"

    			+ ", 0 as TheLineOfIntersection \n"

  				//+ "	into #ConversionTable \n"
 
    			+ "	FROM SettingsConversion conv \n"

  + "inner join Transponders tp \n"

  + "on conv.Transponder = tp.id \n"

  	+ " where parent_id = ? \n"
  
 	+ " ); \n"

	+ "CREATE MEMORY TEMPORARY TABLE ManyFrequencies AS (  \n"
	
 	+ "select \n"
 	+ "p1.LineNumber \n"
 	+ ", p1.frequency \n"
 	+ ", p1.TheLineOfIntersection \n"
 	//+ "into #ManyFrequencies \n"

 	+ "from ConversionTable p1  \n"
 
 	+ "union  \n"
 
 	+ "select  \n"
 	+ "p2.LineNumber  \n"
 	+ ", p2.frequency + 1 \n"
 	+ ", p2.TheLineOfIntersection AS  TheLineOfIntersection \n"  
 	+ "from ConversionTable p2 \n"
 
 	+ "union \n"
 
 	+ "select \n"
 	+ "p3.LineNumber \n"
 	+ ", p3.frequency - 1 \n"
 	+ ", p3.TheLineOfIntersection \n"   
 	+ "from ConversionTable p3 \n"
 
	+ " ); \n"
 	
	+ "CREATE MEMORY TEMPORARY TABLE IntersectionTable AS (   \n"

 	+ "select \n"
	+ "t1.LineNumber \n"
	+ ", t1.frequency \n"
	+ ", t2.LineNumber as TheLineOfIntersection \n"
	//+ "into #IntersectionTable \n"	 
	+ "from ManyFrequencies t1 \n"
	+ "inner join ManyFrequencies t2 \n"
	+ "on t1.frequency = t2.frequency \n"
	+ "and t1.LineNumber <> t2.LineNumber \n"
 
	+ " ); \n";
	
    }
    
    public static String getIntersectionQuery() {
    	
    	return "\n"
//    			+ "DROP TABLE  CONVERSIONTABLE IF EXISTS; \n"
//    			+ "DROP TABLE  ManyFrequencies IF EXISTS; \n"
//    			+ "DROP TABLE  IntersectionTable IF EXISTS; \n"
    			
//    			+ "SET NOCOUNT ON \n"
//    			+ "IF OBJECT_ID('tempdb..#ConversionTable') IS NOT NULL DROP Table #ConversionTable \n"
//    			+ "IF OBJECT_ID('tempdb..#ManyFrequencies') IS NOT NULL DROP Table #ManyFrequencies \n"
//    			+ "IF OBJECT_ID('tempdb..#IntersectionTable') IS NOT NULL DROP Table #IntersectionTable \n"

//				+ "CREATE MEMORY TEMPORARY TABLE CONVERSIONTABLE  AS ( \n"
//    			+ "SELECT \n"
//    			+ "LineNumber \n"
//
//    			+ ", tp.frequency \n"
//
//    			+ ", 0 as TheLineOfIntersection \n"
//
//  				//+ "	into #ConversionTable \n"
// 
//    			+ "	FROM SettingsConversion conv \n"
//
//  + "inner join Transponders tp \n"
//
//  + "on conv.Transponder = tp.id \n"
//
//  	+ " where parent_id = ? \n"
//  
// 	+ " ); \n"
//
//	+ "CREATE MEMORY TEMPORARY TABLE ManyFrequencies AS (  \n"
//	
// 	+ "select \n"
// 	+ "p1.LineNumber \n"
// 	+ ", p1.frequency \n"
// 	+ ", p1.TheLineOfIntersection \n"
// 	//+ "into #ManyFrequencies \n"
//
// 	+ "from #ConversionTable p1  \n"
// 
// 	+ "union  \n"
// 
// 	+ "select  \n"
// 	+ "p2.LineNumber  \n"
// 	+ ", p2.frequency + 1 \n"
// 	+ ", p2.TheLineOfIntersection AS  TheLineOfIntersection \n"  
// 	+ "from #ConversionTable p2 \n"
// 
// 	+ "union \n"
// 
// 	+ "select \n"
// 	+ "p3.LineNumber \n"
// 	+ ", p3.frequency - 1 \n"
// 	+ ", p3.TheLineOfIntersection \n"   
// 	+ "from #ConversionTable p3 \n"
// 
//	+ " ); \n"
// 	
//+ "CREATE MEMORY TEMPORARY TABLE IntersectionTable AS (   \n"
//
// 	+ "select \n"
//	+ "t1.LineNumber \n"
//	+ ", t1.frequency \n"
//	+ ", t2.LineNumber as TheLineOfIntersection \n"
//	//+ "into #IntersectionTable \n"	 
//	+ "from #ManyFrequencies t1 \n"
//	+ "inner join #ManyFrequencies t2 \n"
//	+ "on t1.frequency = t2.frequency \n"
//	+ "and t1.LineNumber <> t2.LineNumber \n"
// 
//	+ " ); \n"
	
 	+ "select distinct \n"
 	+ "conv.LineNumber \n"
 	+ ", ISNULL(inter.TheLineOfIntersection, 0) AS TheLineOfIntersection \n"
 
 	+ "from ConversionTable conv \n"
 	+ "inner join IntersectionTable inter \n"
 	+ "on inter.LineNumber = conv.LineNumber \n"
 	
 	+ "order by \n" 
 	+ "conv.LineNumber \n"
 
// 	+ "DROP TABLE #ConversionTable \n" 
// 	+ "DROP TABLE #ManyFrequencies \n"
// 	+ "DROP TABLE #IntersectionTable \n"
 	+ "";
    	
    }

}
