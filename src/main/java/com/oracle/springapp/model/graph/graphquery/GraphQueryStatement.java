package com.oracle.springapp.model.graph.graphquery;

import java.util.Date;

public class GraphQueryStatement {
	private int id;
	private String graphName;
	private String queryName;
	private String queryType;
	private String queryAlias;
    private String queryContent;
    private Date createDate;
    private Date updateDate;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGraphName() {
		return graphName;
	}
	public void setGraphName(String graphName) {
		this.graphName = graphName;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getQueryAlias() {
		return queryAlias;
	}
	public void setQueryAlias(String queryAlias) {
		this.queryAlias = queryAlias;
	}
	public String getQueryContent() {
		return queryContent;
	}
	public void setQueryContent(String queryContent) {
		this.queryContent = queryContent;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "GraphQueryStatement [id=" + id + ", queryName=" + queryName + ", queryType=" + queryType
				+ ", queryAlias=" + queryAlias + ", queryContent=" + queryContent + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
}
