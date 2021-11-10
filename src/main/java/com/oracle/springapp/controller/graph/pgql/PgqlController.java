package com.oracle.springapp.controller.graph.pgql;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.springapp.model.graph.graphquery.GraphQueryStatement;
import com.oracle.springapp.service.graph.graphquery.GraphQueryService;
import com.oracle.springapp.util.graph.MyGraphUtils;

@Controller
@RequestMapping("pgql")
public class PgqlController {
	
	@Autowired 
	MyGraphUtils pgUtils;
	
	@Autowired
	GraphQueryService graphQueryService;
	
	@GetMapping("graph")
	public ModelAndView graph() throws SQLException {
		List<String> list = pgUtils.getGraphNames();
		List<GraphQueryStatement> querylist = graphQueryService.getAllGraphQueryStatement();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("graph");
		modelAndView.getModel().put("graphNames", list);
		modelAndView.getModel().put("graphQuery", querylist);
		return modelAndView;
	}
	
	@GetMapping("graphquery")
	public ModelAndView graphquery() throws SQLException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("graphquery");
		
		List<GraphQueryStatement> list = graphQueryService.getAllGraphQueryStatement();
		List<String> names = pgUtils.getGraphNames();
		modelAndView.getModel().put("result", list);
		modelAndView.getModel().put("graphNames", names);
		
		return modelAndView;
	}
}
