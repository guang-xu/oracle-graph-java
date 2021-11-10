package com.oracle.springapp.service.graph.graphsearch;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.springapp.dao.graph.graphsearch.GraphDao;

import oracle.pg.rdbms.pgql.PgqlToSqlException;
import oracle.pgql.lang.PgqlException;

@Service
public class GraphServiceImpl implements GraphService{

	@Autowired
	private GraphDao graphDao;
	
	public Map<String, Object> getGraph(String graphName) {
		try {
			return graphDao.getGraph(graphName);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, Object> getGraphByQuery(String graphName, String Query) throws SQLException {
		try {
			return graphDao.getGraphByQuery(graphName, Query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void createGraph(String query) throws SQLException, PgqlToSqlException, PgqlException {
		graphDao.createGraph(query);
	}

	@Override
	public boolean updateGraph(String graphName, String query) throws SQLException, PgqlToSqlException, PgqlException {
		return graphDao.updateGraph(graphName, query);
	}
}
