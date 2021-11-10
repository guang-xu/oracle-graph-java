package com.oracle.springapp.service.graph.graphsearch;

import java.sql.SQLException;
import java.util.Map;

import oracle.pg.rdbms.pgql.PgqlToSqlException;
import oracle.pgql.lang.PgqlException;

public interface GraphService {
	public Map<String,Object> getGraph(String graphName);
	public Map<String,Object> getGraphByQuery(String graphName,String Query)  throws SQLException;
	public void createGraph(String query) throws SQLException, PgqlToSqlException, PgqlException;
	public boolean updateGraph(String graphName,String query)  throws SQLException, PgqlToSqlException, PgqlException;	
	
}
