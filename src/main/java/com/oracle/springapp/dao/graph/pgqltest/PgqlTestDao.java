package com.oracle.springapp.dao.graph.pgqltest;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PgqlTestDao {
	public List<String> queryGraph(String graphName,String query,Map<String,Integer> params)  throws SQLException;
	public List<String> queryGraph2(String graphName, String query, Map<String, Integer> params) throws SQLException;
}
