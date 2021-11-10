package com.oracle.springapp.dao.graph.graphsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.pg.rdbms.pgql.PgqlResultSet;
import oracle.pgql.lang.PgqlException;
import oracle.pgql.lang.ResultSetMetaData;

public class ResultSetUtils {
	public static List<List<Map<String,Object>>> generateResultSet(PgqlResultSet resultSet) throws PgqlException{
		
		ResultSetMetaData metadata = resultSet.getMetaData();
		int numElements = metadata.getColumnCount();
		List<List<Map<String,Object>>> values = new ArrayList<List<Map<String,Object>>>();
		
		while(resultSet.next()) {
			List<Map<String,Object>> row = new ArrayList<Map<String,Object>>();
			for(int i=1; i<=numElements; i++) {
				Map<String,Object> temp = new HashMap<String,Object>();
				String colName = metadata.getColumnName(i);
				
				Object colValue = resultSet.getObject(colName);
				temp.put("colName", colName);
				//int valueType = resultSet.getValueType(colName);
				//temp.put("colType", valueType);
				temp.put("colValue", colValue);
				row.add(temp);
			}
			values.add(row);
		}
		System.out.println(values.toString());
		return values;		
	}
	
	public static List<String> getHeaders(PgqlResultSet resultSet) throws PgqlException{
		
		ResultSetMetaData metadata = resultSet.getMetaData();
		int numElements = metadata.getColumnCount();
		List<String> list = new ArrayList<String>();
		for(int i=1; i<=numElements; i++) {
			list.add(metadata.getColumnName(i));
		}
		return list;
	}

}
