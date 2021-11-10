package com.oracle.springapp.service.graph.pgx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oracle.springapp.util.graph.PgConnections;

import oracle.pg.rdbms.GraphServer;
import oracle.pgql.lang.PgqlException;
import oracle.pgx.api.FlashbackSynchronizer;
import oracle.pgx.api.GraphSource;
import oracle.pgx.api.PgqlResultSet;
import oracle.pgx.api.PgxGraph;
import oracle.pgx.api.PgxPreparedStatement;
import oracle.pgx.api.PgxSession;
import oracle.pgx.api.ServerInstance;
import oracle.pgx.api.Synchronizer;
import oracle.pgx.common.types.PropertyType;
import oracle.pgx.config.GraphConfigBuilder;
import oracle.pgx.config.PartitionWhileLoading;
import oracle.pgx.config.PgRdbmsGraphConfig;

@Component
public class PgxUtils {
	private String baseUrl = "your graph server ip";
	private String username = "username";
	private String password = "password";
	private PgxSession session = null; 
	private ServerInstance instance = null;
	private String query = "";
	private String sessionId = "";
	private String jdbcUrl = "jdbc:oracle:thin:@ip:port/servicename";
	
	
	@Autowired
	private PgConnections conn;
	//sessionID = 123401a8-52fb-4df3-908d-3ce0e87af697
	
	public PgxUtils() {
		try {
			instance = GraphServer.getInstance(baseUrl, username, password.toCharArray());
			session = instance.createSession("graph_session");
		} catch (IOException | ExecutionException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public PgxUtils(String sessionId) {
		try {
			instance = GraphServer.getInstance(baseUrl, username, password.toCharArray());
			session = instance.getSession(sessionId);
		} catch (IOException | ExecutionException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Set<String> getLoadedPgxGraphs() {
		Set<String> graphs = session.getAvailableCompiledProgramIds();
		return graphs;
	}
	
	private Synchronizer getSynchronizer(PgxGraph graphName) throws SQLException {
		Synchronizer synchronizer = new Synchronizer.Builder<FlashbackSynchronizer>()
			    .setType(FlashbackSynchronizer.class)
			    .setGraph(graphName)
			    .setConnection(conn.getPgqlConnection().getJdbcConnection())
			    .build();
		return synchronizer;
	}
	
	public void syncGraph(PgxGraph graphName) throws SQLException {
		Synchronizer synchronizer = getSynchronizer(graphName);
		PgxGraph pgxGraph = synchronizer.sync();
		System.out.println(pgxGraph.toString());
	}
	
	public PgxGraph getPgxGraph(String query,String graphName) throws IOException, ExecutionException, InterruptedException, PgqlException {
		PgxGraph graph = session.getGraph(graphName);
		PgxPreparedStatement prepqredStatment = session.preparePgql(Files.readString(Paths.get("C:\\developer\\workspace\\eclipse_workspace6\\springapp-graph\\src\\main\\java\\com\\oracle\\springapp\\service\\graph\\pgx\\create_graph_03.pgql")));
		PgqlResultSet resutSet = prepqredStatment.executeQuery();
		System.out.println(resutSet.toString());
	    return graph;
	}
	
	public PgxGraph getPgxGraph(String graphName) throws IOException, ExecutionException, InterruptedException, PgqlException {
		PgxGraph graph = session.getGraph(graphName);
	    return graph;
	}
	
	public boolean loadgraphToPGX() throws PgqlException, IOException {
		PgxPreparedStatement prepqredStatment = session.preparePgql(Files.readString(Paths.get("C:\\developer\\workspace\\eclipse_workspace6\\springapp-graph\\src\\main\\java\\com\\oracle\\springapp\\service\\graph\\pgx\\create_graph_03.pgql")));
		return prepqredStatment.execute();
	}
	
	public void loadgraphviewToPGX(String graphName) throws PgqlException, IOException {
		PgxGraph pgxGraph = session.readGraphByName(graphName, GraphSource.PG_VIEW);
		pgxGraph.publish();
	}
	
	public PgxGraph loadgraphToPGXByName(String GraphName) throws PgqlException, IOException, ExecutionException, InterruptedException {
		PgxGraph graph = session.readGraphWithProperties(getConfig(GraphName));
		return graph;
	}
	
	public void doTest() throws PgqlException, ExecutionException, InterruptedException {
		PgxGraph graph = session.getGraph("GRARPH_02");
		//graph.publish();
		//PgxGraph graph = session.readGraphByName("GRAPH_03", GraphSource.PG_VIEW);
		System.out.println("session id = "+session.getId());
		Set<Entry<String, Integer>> params = MyConstant.parameter.entrySet();
		Iterator<Entry<String, Integer>> it = params.iterator();
		while(it.hasNext()) {
			Entry<String, Integer> entity = it.next();
			String skucode = entity.getKey();
			int channelId = entity.getValue();
			
			//String query_param2 = MyConstant.SELECT_QUERY_PARAM2.replace(":skuCode",skucode );
			//query_param2 = query_param2.replace(":channelId",channelId+"" );
			
			String query_param1 = MyConstant.SELECT_QUERY_PARAM1.replace(":skuCode",skucode );
			
			int skc = Integer.parseInt(skucode);
			long startDate = System.currentTimeMillis();
			PgxPreparedStatement pgxPreparedStatement = graph.preparePgql(query_param1);
			pgxPreparedStatement.setString(1, skucode);
			pgxPreparedStatement.execute();
			pgxPreparedStatement.getResultSet().print();
			long executetime = System.currentTimeMillis() - startDate;
			
			//System.out.println("sku_code:"+skucode +", "+"ChannelId:"+channelId+", execue_time:"+executetime+"ms");
			System.out.println("sku_code:"+skucode +", execue_time:"+executetime+"ms");
		}
	}
	
	
	private PgRdbmsGraphConfig getConfig(String graphName) {
		PgRdbmsGraphConfig  config = GraphConfigBuilder.forPropertyGraphRdbms()
										.setJdbcUrl(jdbcUrl)
										.setUsername(username)
										.setPassword(password)
										.setName(graphName)
										.setPartitionWhileLoading(PartitionWhileLoading.BY_LABEL)
										.setLoadVertexLabels(true)
										.setLoadEdgeLabel(true)
										.addVertexProperty("ID", PropertyType.STRING)
										.addVertexProperty("SKU_CODE", PropertyType.STRING)
										.addVertexProperty("CHANNEL_ID", PropertyType.STRING)
										.addVertexProperty("CHANNEL_NAME", PropertyType.STRING)
										.addVertexProperty("SHARE_STORE_ID", PropertyType.STRING)
										.addVertexProperty("SHARE_STORE_NAME", PropertyType.STRING)
										.addVertexProperty("WAREHOUSE_ID", PropertyType.STRING)
										.addVertexProperty("WAREHOUSE_NAME", PropertyType.STRING)
										.addVertexProperty("QTY_PREOUT", PropertyType.INTEGER)
										.addVertexProperty("QTY_PREIN", PropertyType.INTEGER)
										.addVertexProperty("QTY_STORAGE", PropertyType.INTEGER)
										.addVertexProperty("QTY_SHARE", PropertyType.INTEGER)
										.addVertexProperty("QTY_AVAILABLE", PropertyType.INTEGER)
										.addEdgeProperty("RATIO", PropertyType.STRING)
										//.setAutoRefresh(true)
										//.setUpdateIntervalSec(60)
										//.setFetchIntervalSec(60)
										.setCreateEdgeIdIndex(true)
										.setCreateEdgeIdMapping(true)
										.setCreateVertexIdIndex(true)
										.setCreateVertexIdMapping(true)
										.build();
		return config;
	}
	
	public static void main(String args[]) throws PgqlException, IOException, ExecutionException, InterruptedException, SQLException {
		//PgxUtils utils = new PgxUtils();
		//utils.loadgraphviewToPGX("GRAPH_02");
		//utils.loadgraphToPGXByName("GRARPH_02");
		//System.out.println(utils.session.getId());
		//PgxGraph graph = utils.session.getGraph("GRARPH_02");
		
		PgxUtils utils = new PgxUtils("5e5afcc6-5328-4767-91fe-e4f2d3b74f0c");
		PgxGraph graph = utils.session.getGraph("GRARPH_02");
		//Iterator<Entry<String, PgxGraph>> it = utils.session.getGraphs().entrySet().iterator();
		//while(it.hasNext()) {
		//	Entry<String, PgxGraph> entry = it.next();
		//	System.out.println(entry.getKey() +"  ===  "+entry.getValue());
		//}
		//GraphConfig config = graph.getConfig();
		//System.out.println(config.toString()); 
		//utils.syncGraph(graph);
		System.out.println(graph.toString());
		utils.doTest();
	}
}
