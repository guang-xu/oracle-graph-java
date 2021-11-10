package com.oracle.springapp.controller.graph.pgx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.springapp.service.graph.pgx.PgxUtils;

import oracle.pgql.lang.PgqlException;
import oracle.pgx.api.PgxGraph;

@RestController	
public class ApiPGXController {
	
	@Autowired
	PgxUtils pgx;
	
	@GetMapping("pgxgraph/load2pgx")
	public ModelAndView pgxgraph(String query,String graphName) throws SQLException, ExecutionException, InterruptedException, IOException, PgqlException {
		PgxGraph pgxGraph = pgx.getPgxGraph(query, graphName);
		pgxGraph.preparePgql(query);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pgxquery/pgxquery");
		modelAndView.getModel().put("pgxGraph", pgxGraph);
		return modelAndView;
	}
	
	@GetMapping("pgxgraph/syncGraph")
	public String syncGraph(String query,String graphName) throws SQLException, ExecutionException, InterruptedException, IOException, PgqlException {
		pgx.setSessionId("5e5afcc6-5328-4767-91fe-e4f2d3b74f0c");
		PgxGraph graph = pgx.getPgxGraph("GRARPH_02");
		pgx.syncGraph(graph);
		System.out.println(graph.toString());
		pgx.doTest();
		return "xxx";
	}
}
