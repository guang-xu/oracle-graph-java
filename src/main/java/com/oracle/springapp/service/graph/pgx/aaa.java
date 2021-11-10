package com.oracle.springapp.service.graph.pgx;

import oracle.pg.rdbms.*;
import java.sql.Connection;
import java.sql.Statement;
import oracle.pg.rdbms.pgql.PgqlConnection;
import oracle.pg.rdbms.pgql.PgqlStatement;
import oracle.pgx.api.*;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import oracle.ucp.jdbc.PoolDataSource;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This example shows how to get all permissions.
 */
public class aaa
{

  public static void main(String[] args) throws Exception
  {
    int idx=0;
    String host               = "your oracle db ip"; 
    String port               = "port"; 
    String sid                = "servicename"; 
    String user               = "db username"; 
    String password           = "db password";
    String graph              = "graph name";
        
    Connection conn = null;
    PgxPreparedStatement stmt = null;
  
    try {
      
      // Get a jdbc connection
      PoolDataSource  pds = PoolDataSourceFactory.getPoolDataSource();
      pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
      pds.setURL("jdbc:oracle:thin:@"+host+":"+port +"/"+sid);
      pds.setUser(user);
      pds.setPassword(password);     
      conn = pds.getConnection();
      conn.setAutoCommit(false);
      
      ServerInstance instance = GraphServer.getInstance("your graph server ip", user, password.toCharArray());
      PgxSession session = instance.createSession("my-session");

      var statement = Files.readString(Path.of("C:\\developer\\workspace\\eclipse_workspace6\\springapp-graph\\src\\main\\java\\com\\oracle\\springapp\\service\\graph\\pgx\\create_graph_03.pgql"));
      stmt = session.preparePgql(statement);
      stmt.execute();

      PgxGraph g = session.getGraph(graph);
      System.out.println("Graph: "+ g);
      
      String userName = instance.getPgxUsername();
      var userRoles = instance.getPgxUserRoles();
      var genericPermissions = instance.getPgxGenericPermissions();
      String graphPermission = g.getPermission().toString();

      System.out.println("Username is " + userName);
      System.out.println("User Roles are " + userRoles);
      System.out.println("Generic permissions are " + genericPermissions);
      System.out.println("Graph permission is " + graphPermission);

    }

    finally {
      // close the sql statment
      if (stmt != null) {
        stmt.close();
      }
      // close the connection
      if (conn != null) {
        conn.close();
      }
    }
  }
}