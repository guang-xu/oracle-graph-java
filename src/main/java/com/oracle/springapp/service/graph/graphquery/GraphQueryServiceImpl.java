package com.oracle.springapp.service.graph.graphquery;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.springapp.dao.graph.graphquery.GraphQueryDao;
import com.oracle.springapp.model.graph.graphquery.GraphQueryStatement;

@Service
public class GraphQueryServiceImpl implements GraphQueryService{

	@Autowired
	private GraphQueryDao graphQueryDao;
	
	@Override
	public int upsertQuery(GraphQueryStatement query) throws SQLException {
		return graphQueryDao.upsertQuery(query);
	}

	@Override
	public List<GraphQueryStatement> getAllGraphQueryStatement() throws SQLException {
		return graphQueryDao.getAllGraphQueryStatement();
	}

	@Override
	public List<GraphQueryStatement> getByGraphQueryStatementName(String queryName) throws SQLException {
		return graphQueryDao.getByGraphQueryStatementName(queryName);
	}
}
