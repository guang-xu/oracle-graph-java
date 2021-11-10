package com.oracle.springapp.service.graph.graphquery;

import java.sql.SQLException;

import oracle.pg.rdbms.OraclePropertyGraph;

public interface GraphInstanceService {
	public OraclePropertyGraph getOraclePropertyGraphInstance(String graphName)  throws SQLException ;
}
