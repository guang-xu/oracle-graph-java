package com.oracle.springapp.dao.graph.graphsearch;

import java.sql.SQLException;
import java.util.Map;

import oracle.pg.rdbms.pgql.PgqlToSqlException;
import oracle.pgql.lang.PgqlException;

public interface GraphDao {
	public Map<String,Object> getGraph(String graphName)  throws SQLException;	
	public boolean updateGraph(String graphName,String query)  throws SQLException, PgqlToSqlException, PgqlException;	
	public Map<String,Object> getGraphByQuery(String graphName,String Query)  throws SQLException;	
	public void createGraph(String query) throws SQLException, PgqlToSqlException, PgqlException;
}
