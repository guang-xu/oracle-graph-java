package com.oracle.springapp.dao.graph.graphsearch;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oracle.springapp.util.graph.PgConnections;

import oracle.pg.rdbms.pgql.PgqlConnection;
import oracle.pg.rdbms.pgql.PgqlPreparedStatement;
import oracle.pg.rdbms.pgql.PgqlResultSet;
import oracle.pg.rdbms.pgql.PgqlToSqlException;
import oracle.pgql.lang.PgqlException;

@Component
public class GraphDaoImpl implements GraphDao{
	
	@Autowired
	PgConnections pgConn;

	 
	@Override
	public Map<String, Object> getGraph(String graphName) throws SQLException {
		
//		String query = "SELECT wv.SKU_CODE as warehouse_sku_code, "
//				+ "       wv.WAREHOUSE_NAME   as warehouse_name, "
//				+ "       wv.QTY_PREOUT       as warehouse_QTY_PREOUT, "
//				+ "       wv.QTY_PREIN        as warehouse_QTY_PREIN, "
//				+ "       wv.QTY_STORAGE      as warehouse_QTY_STORAGE, "
//				+ "       wv.QTY_SHARE        as warehouse_QTY_SHARE, "
//				+ "       wv.QTY_AVAILABLE    as warehouse_QTY_AVAILABLE, "
//				+ "       e.RATIO             as E1_RATIO, "
//				+ "       sv.SKU_CODE         as sharestorage_SKU_CODE, "
//				+ "       sv.SHARE_STORE_ID   as sharestorage_SHARE_STORE_ID, "
//				+ "       sv.SHARE_STORE_NAME as sharestorage_SHARE_STORE_NAME, "
//				+ "       sv.QTY_PREOUT       as sharestorage_QTY_PREOUT, "
//				+ "       sv.QTY_PREIN        as sharestorage_QTY_PREIN, "
//				+ "       sv.QTY_STORAGE      as sharestorage_QTY_STORAGE, "
//				+ "       e1.RATIO            as E2_RATIO, "
//				+ "       cv.SKU_CODE         as channelstorage_SKU_CODE, "
//				+ "       cv.CHANNEL_ID       as channelstorage_CHANNEL_ID, "
//				+ "       cv.CHANNEL_NAME     as channelstorage_CHANNEL_NAME, "
//				+ "       cv.QTY_PREOUT       as channelstorage_QTY_PREOUT, "
//				+ "       cv.QTY_PREIN        as channelstorage_QTY_PREIN, "
//				+ "       cv.QTY_STORAGE      as channelstorage_QTY_STORAGE "
//				+ "  FROM MATCH (wv:WAREHOUSE_SKU )-[e:SUPPLY ]->(sv:SHARE_STORAGE_SKU )-[e1:SUPPLY ]->(cv:CHANNEL_STORAGE_SKU ) "
//				+ "WHERE wv.SKU_CODE = '127323' "
//				+ "and  cv.CHANNEL_ID = 1527";
//		return getGraphByQuery(graphName, query);
		return null;
	}


	@Override
	public Map<String, Object> getGraphByQuery(String graphName, String query) throws SQLException {
		PgqlConnection pgql = pgConn.getPgqlConnection();
		pgql.setGraph(graphName);
		Map<String, Object> result = new HashMap<String,Object>();
		
		try {
			PgqlPreparedStatement pgqlPstmt = pgql.prepareStatement(query);
			long startTime = System.currentTimeMillis();
			pgqlPstmt.execute();
			PgqlResultSet pgqlRs = pgqlPstmt.getResultSet();
			long endTime=System.currentTimeMillis();
			long time = endTime-startTime;
			
			List<List<Map<String, Object>>> datas= ResultSetUtils.generateResultSet(pgqlRs);
			List<String> headers = ResultSetUtils.getHeaders(pgqlRs);
			result.put("headers", headers);
			result.put("result", datas);
			result.put("exeTimes", time);
			result.put("graphName", graphName);
		
		} catch (PgqlToSqlException | PgqlException e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public void createGraph(String query) throws SQLException, PgqlToSqlException, PgqlException {
		PgqlConnection pgql = pgConn.getPgqlConnection();
		pgql.prepareStatement(query).execute();
	}


	@Override
	public boolean updateGraph(String graphName, String query) throws SQLException, PgqlToSqlException, PgqlException {
		PgqlConnection pgql = pgConn.getPgqlConnection();
		pgql.setGraph(graphName);
		return pgql.prepareStatement(query).execute();
	}
}
