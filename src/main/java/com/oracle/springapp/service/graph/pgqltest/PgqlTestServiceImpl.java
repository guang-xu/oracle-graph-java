package com.oracle.springapp.service.graph.pgqltest;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.springapp.dao.graph.pgqltest.PgqlTestDao;

@Service
public class PgqlTestServiceImpl implements PgqlTestService {

	@Autowired
	PgqlTestDao pgqlTestDao;
	
	@Override
	public List<String> pgqlTest(String graphName,String query,Map<String,Integer> params) throws SQLException {
		return pgqlTestDao.queryGraph(graphName, query, params);
	}
	
	@Override
	public List<String> pgqlTest2(String graphName,String query,Map<String,Integer> params) throws SQLException {
		return pgqlTestDao.queryGraph2(graphName, query, params);
	}

}
