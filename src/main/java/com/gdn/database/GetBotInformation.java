package com.gdn.database;
import java.awt.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.gdn.qabot.controller.QaBotController;
/**
 * Created by avinash.t
 */
public class GetBotInformation extends QaBotController{
	
    public static Connection apiconn =null;
	public String getApiInformation(String environment, String source_type, String bot_keyword)
	{
		connect();
		String sql = "select url from bot_config where environment='"+environment+"' and source_type='"+source_type+"' and bot_keyword='"+bot_keyword+"' ";
		String outputUrl=null;

        try {
            Statement  stmt= apiconn.createStatement();
            APP_LOGS.debug("Statement:"+stmt);

            APP_LOGS.debug("SQL:"+sql);

            ResultSet rs    = stmt.executeQuery(sql);


            // loop through the result set
            while (rs.next()) {
	                outputUrl=rs.getString("url");
	                String temp1="<a href=\"";
	                String temp2="\">Click Here for "+bot_keyword+" API URL<a/>";
	                outputUrl=temp1.concat(outputUrl).concat(temp2);
	                APP_LOGS.debug(outputUrl);
            }
            rs.close();
            stmt.close();
            apiconn.close();
            
            
        } catch (SQLException e) {
        	APP_LOGS.debug(e.getMessage());
            outputUrl=e.getMessage();
        }
		
		
		return outputUrl;
	}
	
	public String getCenterInformation(String environment, String source_type, String bot_keyword)
	{
		connect();
		String sql = "select url from bot_config where environment='"+environment+"' and source_type='"+source_type+"' and bot_keyword='"+bot_keyword+"' ";
		String outputUrl=null;

        try {
            Statement  stmt= apiconn.createStatement();
            APP_LOGS.debug("Statement:"+stmt);
            APP_LOGS.debug("SQL:"+sql);

            ResultSet rs    = stmt.executeQuery(sql);


            // loop through the result set
            while (rs.next()) {
	                outputUrl=rs.getString("url");
	                String temp1="<a href=\"";
	                String temp2="\">Click Here for "+bot_keyword+" Center URL<a/>";
	                outputUrl=temp1.concat(outputUrl).concat(temp2);
	                APP_LOGS.debug(outputUrl);
            }
            rs.close();
            stmt.close();
            apiconn.close();
            
            
        } catch (SQLException e) {
        	APP_LOGS.debug(e.getMessage());
            outputUrl=e.getMessage();
        }
		
		
		return outputUrl;
	}
	
	public String getProductInformation(String environment, String source_type, String bot_keyword)
	{
		connect();
		String sql = "select url from bot_config where environment='"+environment+"' and source_type='"+source_type+"' and bot_keyword='"+bot_keyword+"' ";
		String outputUrl=null;

        try {
            Statement  stmt= apiconn.createStatement();
            APP_LOGS.debug("Statement:"+stmt);

            APP_LOGS.debug("SQL:"+sql);

            ResultSet rs    = stmt.executeQuery(sql);


            // loop through the result set
            while (rs.next()) {
	                outputUrl=rs.getString("url");
	                String temp1="<a href=\"";
	                String temp2="\">Click Here for "+bot_keyword+" Product URL<a/>";
	                outputUrl=temp1.concat(outputUrl).concat(temp2);
	                APP_LOGS.debug(outputUrl);
            }
            rs.close();
            stmt.close();
            apiconn.close();
            
            
        } catch (SQLException e) {
        	APP_LOGS.debug(e.getMessage());
            outputUrl=e.getMessage();
        }
		
		
		return outputUrl;
	}
	
	public String getUserInformation(String environment, String source_type, String bot_keyword)
	{
		connect();
		String sql = "select url from bot_config where environment='"+environment+"' and source_type='"+source_type+"' and bot_keyword='"+bot_keyword+"' ";
		String outputUrl=null;

        try {
            Statement  stmt= apiconn.createStatement();
            APP_LOGS.debug("Statement:"+stmt);

            APP_LOGS.debug("SQL:"+sql);

            ResultSet rs    = stmt.executeQuery(sql);


            // loop through the result set
            while (rs.next()) {
	                outputUrl=rs.getString("url");
	                String temp2="Username & Password details for "+bot_keyword+" is :";
	                outputUrl=temp2.concat(outputUrl);
	                APP_LOGS.debug(outputUrl);
            }
            rs.close();
            stmt.close();
            apiconn.close();
            
            
        } catch (SQLException e) {
        	APP_LOGS.debug(e.getMessage());
            outputUrl=e.getMessage();
        }
		
		
		return outputUrl;
	}
	
	public String getPromoInformation(String environment, String source_type, String bot_keyword)
	{
		connect();
		String sql = "select url from bot_config where environment='"+environment+"' and source_type='"+source_type+"' and bot_keyword='"+bot_keyword+"' ";
		String outputUrl=null;

        try {
            Statement  stmt= apiconn.createStatement();
            APP_LOGS.debug("Statement:"+stmt);
            APP_LOGS.debug("SQL:"+sql);

            ResultSet rs    = stmt.executeQuery(sql);
          
            // loop through the result set
            while (rs.next()) {
	                outputUrl=rs.getString("url");
	                String temp2="Public Voucher Code :";
	                outputUrl=temp2.concat(outputUrl);
	                APP_LOGS.debug(outputUrl);
            }
            rs.close();
            stmt.close();
            apiconn.close();
            
            
        } catch (SQLException e) {
        	APP_LOGS.debug(e.getMessage());
            outputUrl=e.getMessage();
        }
			
		return outputUrl;
	}
	
	
	public ArrayList<ArrayList<String>> getEnvironment()
	{
		//String [][] environmentList = null;
		ArrayList<ArrayList<String>> environmentList= new ArrayList<ArrayList<String>>();
		
		ArrayList<String> environmentKeyList= new ArrayList<String>();
		ArrayList<String> environmentNameList= new ArrayList<String>();


		
		
		connect();
		
		String sql = "select environment,environment_name from bot_environment";
		String environment=null;
		String environment_name=null;

        try {
            Statement  stmt= apiconn.createStatement();
            APP_LOGS.debug("Statement:"+stmt);

            APP_LOGS.debug("SQL:"+sql);

            ResultSet rs    = stmt.executeQuery(sql);
            int i=0;
          
            while (rs.next()) {
            	int j=0;
            	environment=rs.getString("environment");
            	environment_name=rs.getString("environment_name");
            	environmentKeyList.add(environment);
            	environmentNameList.add(environment_name);
       
            	i++;
            }
            environmentList.add(environmentKeyList);
        	environmentList.add(environmentNameList);
        	APP_LOGS.debug("EnvironementList"+environmentList);

        	APP_LOGS.debug("Env List:"+environmentList);
            rs.close();
            stmt.close();
            apiconn.close();
            
            
        } catch (SQLException e) {
            APP_LOGS.debug(e.getMessage());
            e.getMessage();
        }
		
		return environmentList;
	}
	
	
	
	 public  void connect() {
	        try {
	            // db parameters
	            try {
	                Class.forName("org.sqlite.JDBC");
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            }
	            String url = "jdbc:sqlite:/Users/avinash.t/Avinash/botdb/qabot.db";
	            // create a connection to the database
	            apiconn= DriverManager.getConnection(url);
	            APP_LOGS.debug("Connection: " + apiconn);
	            if (apiconn!= null) {
	                DatabaseMetaData meta = apiconn.getMetaData();
	                APP_LOGS.debug("The driver name is " + meta.getDriverName());
	            }
	            APP_LOGS.debug("Connection to SQLite has been established.");
	        } catch (SQLException e) {
	        	APP_LOGS.debug(e.getMessage());
	        }
	    }
}
