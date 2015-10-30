package org.openbox.sf5.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("ConnectionManager")
@ApplicationScoped
public class ConnectionManager implements Serializable {

	public Connection getDBConnection() throws SQLException {
		Connection con = null;
		if (DataSource != null) {
			con = DataSource.getConnection();

			DatabaseMetaData dbmd = con.getMetaData();
			if (dbmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE)) {
				con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			}
		} else {
			throw new SQLException("Datasource from Glassfish application server returned as null!");
		}
		return con;
	}

	@Resource(name = "H2-test")
	private com.sun.appserv.jdbc.DataSource DataSource;

	private static final long serialVersionUID = -393036417948357440L;

}
