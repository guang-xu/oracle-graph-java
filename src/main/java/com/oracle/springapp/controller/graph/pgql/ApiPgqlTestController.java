package com.oracle.springapp.controller.graph.pgql;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oracle.springapp.service.graph.pgqltest.PgqlTestService;
import com.oracle.springapp.service.graph.pgx.MyConstant;


@RestController
@RequestMapping("/api/pgql/")
public class ApiPgqlTestController {
	
	@Autowired
	private PgqlTestService testService;
	
	@GetMapping("graphtest")
	public List<String> testGraph() throws JsonProcessingException, SQLException {
		String granName = "GRAPH_02_V";
		String query = MyConstant.SELECT_QUERY_PARAM2;
		Map<String,Integer> params = MyConstant.parameter;
		List<String> result = testService.pgqlTest(granName, query, params);
		return result;
	}
	
	@GetMapping("graphtest2")
	public List<String> testGraph2() throws JsonProcessingException, SQLException {
		String granName = "GRAPH_02_V";
		String query = MyConstant.SELECT_QUERY_PARAM1;
		Map<String,Integer> params = MyConstant.parameter;
		List<String> result = testService.pgqlTest2(granName, query, params);
		return result;
	}
	
}
