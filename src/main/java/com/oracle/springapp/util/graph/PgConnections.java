package com.oracle.springapp.util.graph;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import oracle.pg.rdbms.pgql.PgqlConnection;

@Component
public class PgConnections {
	
	@Autowired
	private DataSource datasource;
	
	public PgqlConnection getPgqlConnection() throws SQLException {
		return PgqlConnection.getConnection(datasource.getConnection());
	}
}
