package com.oracle.springapp.controller.graph.pgql;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oracle.springapp.model.graph.graphquery.GraphQueryStatement;
import com.oracle.springapp.service.graph.graphquery.GraphQueryService;
import com.oracle.springapp.service.graph.graphsearch.GraphService;
import com.oracle.springapp.util.Utils;

import oracle.pg.rdbms.pgql.PgqlToSqlException;
import oracle.pgql.lang.PgqlException;

@RestController
@RequestMapping("/api/pgql/")
public class ApiPgqlController {

	@Autowired
	private GraphService service;
	
	@Autowired
	private GraphQueryService graphQueryService;
	
	@GetMapping("graph")
	public Map<String,Object> retrieveGraph(String graphName,String query) throws JsonProcessingException, SQLException {
		Map<String,Object> result = service.getGraphByQuery(graphName,Utils.replaceUrlEscape(query));
		return result;
	}

	@PostMapping("graph")
	public String createGraph(String query) {
		String result = "fail";
		try {
			service.createGraph(Utils.replaceUrlEscape(query));
			result = "success";
		} catch (PgqlToSqlException | SQLException | PgqlException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="graphquery",method=RequestMethod.POST)
	public Map<String,Object> upsertGraphQuery(@RequestBody GraphQueryStatement stmt) throws SQLException {
		System.out.println(stmt.toString());
		int returnValue = graphQueryService.upsertQuery(stmt);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("result", returnValue);
		return result;
	}
}
