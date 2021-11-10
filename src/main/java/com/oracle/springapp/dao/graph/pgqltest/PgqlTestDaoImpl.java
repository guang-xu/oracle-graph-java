package com.oracle.springapp.dao.graph.pgqltest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oracle.springapp.util.graph.PgConnections;

import oracle.pg.rdbms.pgql.PgqlConnection;
import oracle.pg.rdbms.pgql.PgqlPreparedStatement;
import oracle.pg.rdbms.pgql.PgqlResultSet;
import oracle.pg.rdbms.pgql.PgqlToSqlException;
import oracle.pgql.lang.PgqlException;

@Component
public class PgqlTestDaoImpl implements PgqlTestDao {

	@Autowired
	PgConnections pgConn;

	@Override
	public List<String> queryGraph(String graphName, String query, Map<String, Integer> params) throws SQLException {
		PgqlConnection pgql = pgConn.getPgqlConnection();
		pgql.setGraph(graphName);
		List<String> result = new ArrayList<String>();
		try {
			PgqlPreparedStatement pgqlPstmt = pgql.prepareStatement(query);

			Set<Entry<String,Integer>> entry = params.entrySet();		
			Iterator<Entry<String,Integer>> it = entry.iterator();
			
			while(it.hasNext()) {
				Entry<String,Integer> item = it.next();
				int skuCode = Integer.parseInt(item.getKey());
				int channelId = item.getValue();
				pgqlPstmt.setString(1, item.getKey());
				pgqlPstmt.setInt(2, channelId);
				
				long startTime = System.currentTimeMillis();
				pgqlPstmt.execute();
				PgqlResultSet pgqlRs = pgqlPstmt.getResultSet();
				pgqlRs.print();
				long endTime=System.currentTimeMillis();
				long time = endTime-startTime;
				String rs = "skucode:"+skuCode+", "+"channelId:"+channelId+", execute_time:"+time;
				result.add(rs);
				
			}
		} catch (PgqlToSqlException | PgqlException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<String> queryGraph2(String graphName, String query, Map<String, Integer> params) throws SQLException {
		PgqlConnection pgql = pgConn.getPgqlConnection();
		pgql.setGraph(graphName);
		List<String> result = new ArrayList<String>();
		try {
			PgqlPreparedStatement pgqlPstmt = pgql.prepareStatement(query);

			Set<Entry<String,Integer>> entry = params.entrySet();		
			Iterator<Entry<String,Integer>> it = entry.iterator();
			
			while(it.hasNext()) {
				Entry<String,Integer> item = it.next();
				int skuCode = Integer.parseInt(item.getKey());
				int channelId = item.getValue();
				pgqlPstmt.setString(1, item.getKey());
				
				long startTime = System.currentTimeMillis();
				pgqlPstmt.execute();
				PgqlResultSet pgqlRs = pgqlPstmt.getResultSet();
				long endTime=System.currentTimeMillis();
				long time = endTime-startTime;
				pgqlRs.print();
				String rs = "skucode:"+skuCode+", execute_time:"+time;
				result.add(rs);
				
			}
		} catch (PgqlToSqlException | PgqlException e) {
			e.printStackTrace();
		}
		return result;
	}
}
