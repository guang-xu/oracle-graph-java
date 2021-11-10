package com.oracle.springapp.dao.graph.graphquery;

public interface GraphQuerySQL {
	public static final String UPSERT_GRAPHY_QUERY 
					   = " MERGE INTO TB_GRAPH_TEST_QUERY A "
					   + " USING ( select :id AS ID FROM DUAL) B "
					   + " ON (B.ID = A.ID) "
					   + " WHEN MATCHED THEN "
					   + " UPDATE SET A.GRAPH_NAME    = :graphName "
					   + "           ,A.QUERY_TYPE    = :queryType "
					   + "           ,A.QUERY_NAME    = :queryName "
					   + "           ,A.QUERY_CONTENT = :queryContent "
					   + "           ,A.QUERY_ALIAS   = :queryAlias "
					   + "           ,A.UPDATE_DATE   = SYSDATE "
					   + " WHEN NOT MATCHED THEN "
					   + " INSERT (ID,                      GRAPH_NAME, QUERY_NAME, QUERY_TYPE, QUERY_CONTENT, QUERY_ALIAS, CREATE_DATE) "
					   + " VALUES (SEQ_GRAPH_QUERY.NEXTVAL, :graphName, :queryName, :queryType, :queryContent,  :queryAlias, sysdate ) "
					   + " ";
	
	public static final String SELECT_ALL_GRAPHY_QUERY 
					   = " SELECT * FROM TB_GRAPH_TEST_QUERY A ";
}
