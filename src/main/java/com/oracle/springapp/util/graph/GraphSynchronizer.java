package com.oracle.springapp.util.graph;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.pgx.api.FlashbackSynchronizer;
import oracle.pgx.api.PgxGraph;
import oracle.pgx.api.Synchronizer;

public class GraphSynchronizer {
	
	private Synchronizer getSynchronizer(PgxGraph graphName,Connection conn) {
		Synchronizer synchronizer = new Synchronizer.Builder<FlashbackSynchronizer>()
			    .setType(FlashbackSynchronizer.class)
			    .setGraph(graphName)
			    .setConnection(conn)
			    .build();
		return synchronizer;
	}
	
	public void syncGraph(PgxGraph graphName,Connection conn) throws SQLException {
		PgxGraph pgx = getSynchronizer(graphName,conn).sync();
		pgx.toString();
	}
}
