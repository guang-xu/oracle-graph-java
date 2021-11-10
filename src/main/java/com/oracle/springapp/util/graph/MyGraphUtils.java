package com.oracle.springapp.util.graph;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import oracle.pg.rdbms.Oracle;
import oracle.pg.rdbms.OraclePropertyGraphUtils;
import oracle.ucp.jdbc.PoolDataSource;

@Component
public class MyGraphUtils {
	
	@Autowired
	private PoolDataSource datasource;
	
	public List<String> getGraphNames() throws SQLException{
		return OraclePropertyGraphUtils.getGraphNames(new Oracle(datasource.getConnection()));
	}
}
