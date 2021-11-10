package com.oracle.springapp.service.graph.graphquery;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.pg.rdbms.Oracle;
import oracle.pg.rdbms.OraclePropertyGraph;

@Service
public class GraphInstanceServiceImpl implements GraphInstanceService{

	@Autowired
	DataSource datasource;
	
	@Override
	public OraclePropertyGraph getOraclePropertyGraphInstance(String graphName) throws SQLException {
		Oracle oracle = new Oracle(datasource.getConnection());
		OraclePropertyGraph opg = OraclePropertyGraph.getInstance(oracle, graphName);
		//opg.setEdgeFilterCallback(EdgeFilterCallback);
		return null;
	}
}
