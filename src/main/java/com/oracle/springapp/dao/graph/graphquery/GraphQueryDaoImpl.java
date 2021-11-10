package com.oracle.springapp.dao.graph.graphquery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.oracle.springapp.model.graph.graphquery.GraphQueryStatement;

@Component
public class GraphQueryDaoImpl implements GraphQueryDao,GraphQuerySQL{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public int upsertQuery(GraphQueryStatement query) throws SQLException {
		SqlParameterSource sqlparameter = new BeanPropertySqlParameterSource(query);
		return jdbcTemplate.update(UPSERT_GRAPHY_QUERY, sqlparameter);
	}

	@Override
	public List<GraphQueryStatement> getAllGraphQueryStatement() throws SQLException {
		return jdbcTemplate.query(SELECT_ALL_GRAPHY_QUERY, new RowMapper<GraphQueryStatement>() {

			@Override
			public GraphQueryStatement mapRow(ResultSet rs, int rowNum) throws SQLException {
				GraphQueryStatement bean = new GraphQueryStatement();
				bean.setId(rs.getInt("id"));
				bean.setGraphName(rs.getString("graph_name"));
				bean.setQueryName(rs.getString("query_name"));
				bean.setQueryAlias(rs.getString("query_alias"));
				bean.setQueryType(rs.getString("query_type"));
				bean.setQueryContent(rs.getString("query_content"));
				bean.setCreateDate(rs.getDate("create_date"));
				bean.setUpdateDate(rs.getDate("update_date"));
				return bean;
			}
		});
	}

	@Override
	public List<GraphQueryStatement> getByGraphQueryStatementName(String queryName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
