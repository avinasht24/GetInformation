package com.gdn.database;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.http.HttpServletResponse;

import com.gdn.qabot.controller.QaBotController;
import com.gdn.qabot.model.QaBotConfigModel;

/**
 * Created by avinash.t
 */
public class BotConfiguration extends  QaBotController{
    public static Connection conn =null;


	public String insertBotConfiguration(String inputEnv, String inputSource, String inputKeyword, String inputUrl)
	{
		connect();
		String insertFlag="DB INSERTION IN PROGRESS";
		String insertSql="insert into bot_config(environment,source_type,bot_keyword,url) values(?,?,?,?)";
		try{
            PreparedStatement ps = conn.prepareStatement(insertSql);
            ps.setString(1, inputEnv);
            ps.setString(2, inputSource);
            ps.setString(3, inputKeyword);
            ps.setString(4, inputUrl);
            ps.executeUpdate();
            conn.commit();
            insertFlag="DB INSERTION COMPLETED";
		}
		catch (SQLException e) {
			
			insertFlag=e.getMessage();
            System.out.println(e.getMessage());
        }
        try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}

		return insertFlag;
    }
		
	public List<QaBotConfigModel> getAllConfigData()
	{
		connect();
		String sql = "select environment,source_type,bot_keyword,url from bot_config";
        List<QaBotConfigModel> qaBotConfigs = new ArrayList<QaBotConfigModel>();
        
        try {
            Statement  stmt= conn.createStatement();
            APP_LOGS.debug("Statement:"+stmt);
    		

            System.out.println("SQL:"+sql);

            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                QaBotConfigModel qaBotConfig= new QaBotConfigModel();

            	qaBotConfig.setEnvironment(rs.getString("environment"));
            	qaBotConfig.setSource_type(rs.getString("source_type"));
            	qaBotConfig.setBot_keyword(rs.getString("bot_keyword"));
            	qaBotConfig.setUrl(rs.getString("url"));
            	qaBotConfig.setInsertFlag("TRUE");
            	qaBotConfigs.add(qaBotConfig);
            	
	                
            }
            conn.close();
            stmt.close();
            rs.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return qaBotConfigs;
		
	}
	
	
	
	 public  void connect() {
	        try {
	            // db parameters
	            try {
	                Class.forName("org.sqlite.JDBC");
	            } catch (ClassNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            String url = "jdbc:sqlite:/Users/avinash.t/Avinash/botdb/qabot.db";
	            // create a connection to the database
	            conn = DriverManager.getConnection(url);
conn.setAutoCommit(false);
				APP_LOGS.debug("Connection: " + conn);
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	                APP_LOGS.debug("The driver name is " + meta.getDriverName());
	            }
	            APP_LOGS.debug("Connection to SQLite has been established.");
	        } catch (SQLException e) {
	        	APP_LOGS.debug(e.getMessage());
	        }
	    }
	
}
