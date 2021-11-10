package com.oracle.springapp.service.graph.graphquery;

import java.sql.SQLException;
import java.util.List;

import com.oracle.springapp.model.graph.graphquery.GraphQueryStatement;

public interface GraphQueryService {
	public int upsertQuery(GraphQueryStatement query) throws SQLException;	
	public List<GraphQueryStatement> getAllGraphQueryStatement() throws SQLException;
	public List<GraphQueryStatement> getByGraphQueryStatementName(String queryName) throws SQLException;
}
