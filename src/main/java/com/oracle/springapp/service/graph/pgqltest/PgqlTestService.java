package com.oracle.springapp.service.graph.pgqltest;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PgqlTestService {
	public List<String> pgqlTest(String graphName,String query,Map<String,Integer> params) throws SQLException ;
	public List<String> pgqlTest2(String graphName,String query,Map<String,Integer> params) throws SQLException ;
}
