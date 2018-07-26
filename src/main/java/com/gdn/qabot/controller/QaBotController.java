package com.gdn.qabot.controller;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gdn.database.BotConfiguration;
import com.gdn.database.BugCreationJira;
import com.gdn.database.GetBotInformation;
import com.gdn.database.GetDatabaseDetails;
import com.gdn.qabot.model.GetPnv;
import com.gdn.qabot.model.QaBotBugCreationModel;
import com.gdn.qabot.model.QaBotConfigModel;
import com.gdn.qabot.model.QaBotModel;

import org.springframework.web.bind.annotation.RestController;
/**
 * Created by avinash.t
 */

@RestController
public class QaBotController {

	String inputFromBot;

    public static Properties botProp=null;

    public static InputStream botPropFile=null;
	public static Logger APP_LOGS=null;

    
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView showForm()
	{
		APP_LOGS=Logger.getLogger("QaBotLogger");
		APP_LOGS.debug("Application Started");

		botProp= new Properties();
		//UpdateLocation
		String file = "/Users/avinash.t/Documents/workspace/qabot/TechBot.properties";
	            botPropFile = this.getClass().getClassLoader().getResourceAsStream(file);
	            try {
	            botProp.load(new FileInputStream("/Users/avinash.t/Documents/workspace/qabot/TechBot.properties"));
	            } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="botconfig", method=RequestMethod.GET)
	public ModelAndView showConfigForm()
	{
		APP_LOGS.debug("Calling Config Controller");
		return new ModelAndView("botconfig");
	}
	
	@RequestMapping(value="bugcreation", method=RequestMethod.GET)
	public ModelAndView showBugForm()
	{	 
		APP_LOGS.debug("Calling Bug Creation Controller");
		return new ModelAndView("bugcreation");
	}
	
	
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView showMainForm()
	{	 
		APP_LOGS.debug("Calling Main Controller");
		return new ModelAndView("/");
	}
	
	@RequestMapping(value="newbugcreation", method=RequestMethod.POST)
	public ModelAndView newBugCreation(@ModelAttribute("bugDetails")QaBotBugCreationModel createBug)
	{
		
		APP_LOGS.debug("Jira Username  :"+createBug.getJira_username());
		APP_LOGS.debug("Project Added is  :"+createBug.getProject_code());
		APP_LOGS.debug("Project Title is  :"+createBug.getTitle_summary());
		APP_LOGS.debug("Bug Description is  :"+createBug.getBug_description());
	
		String jiraStatus="false";
		BugCreationJira bc=new BugCreationJira();		
		
		try {
			jiraStatus=bc.createNewJiraTicket(createBug);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createBug.setInsertFlag(jiraStatus);
		return new ModelAndView("bugcreation");
	}
	
	
	@RequestMapping(value="botconfiginsert", method=RequestMethod.POST)
	public ModelAndView getBotConfig(@ModelAttribute("configDetails")QaBotConfigModel config)
	{
		APP_LOGS.debug("Checking configuration details");
		APP_LOGS.debug("Environement selected is  :"+config.getEnvironment());
		APP_LOGS.debug("Source selected is  :"+config.getSource_type());
		APP_LOGS.debug("Keyword entered is  :"+config.getBot_keyword());
		APP_LOGS.debug("Url Entered  is  :"+config.getUrl());
		String insertFlag="true";
		BotConfiguration bc=new BotConfiguration();

		if(config.getSource_type().equals("PROMOTIONCODE"))
		{
			insertFlag=bc.insertBotConfiguration(config.getEnvironment(), config.getSource_type(), "promotioncode", config.getUrl());

		}
		else
		{
			insertFlag=bc.insertBotConfiguration(config.getEnvironment(), config.getSource_type(), config.getBot_keyword().toLowerCase(), config.getUrl());

		}
		config.setInsertFlag(insertFlag);
		return new ModelAndView("botconfig");
	}
	
	@RequestMapping(value="botconfigresults", method=RequestMethod.POST)
	public ModelAndView getBotConfigResults() 
	{
		BotConfiguration bc=new BotConfiguration(); 
		java.util.List<QaBotConfigModel> qaBotConfigs = bc.getAllConfigData();
		ModelAndView model= new ModelAndView("botconfig");
		model.addObject("qaBotConfigs",qaBotConfigs);
		APP_LOGS.debug("qqbotconfig"+qaBotConfigs);		
		return model;	
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getInputAPI", method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	public QaBotModel getInputAPI(@RequestParam(value="inputValue", defaultValue="Hello") String inputValue)
	{
		APP_LOGS.debug("from input api: "+inputValue);
		GetBotInformation getEnv=new GetBotInformation();
		ArrayList<ArrayList<String>> getEnvList=getEnv.getEnvironment();
		int getEnvListSize=getEnvList.get(0).size();
		int envMatchFlag=0;
		 inputFromBot=inputValue.toLowerCase();

		//Start calling the routers
		if (inputFromBot.contains("db") || inputFromBot.contains("database"))
        {
			envMatchFlag=0;
			for(int i=0;i<getEnvListSize;i++)
			{
				 if(inputFromBot.contains(getEnvList.get(0).get(i)))
	             {
					 envMatchFlag=1;
	            	 String dbResults= dbParser(getEnvList.get(1).get(i));
		    			return new QaBotModel(dbResults);

	             }
				 
			}

             if(envMatchFlag==0)
             {
                 APP_LOGS.debug("Mention which uat, the order details are required (qa1/qa2/dev1/dev2/pre-prod) and try again  ");
	    			return new QaBotModel("Mention which uat, the order details are required (qa1/qa2/dev1/dev2/pre-prod) and try again  ");
                
             }
             
        }
		 else if ((inputFromBot.contains("pnv") || inputFromBot.contains("otp") ) && !inputFromBot.contains("user"))
	        {

	            APP_LOGS.debug("\nMake sure redis is installed in local machine (To install type brew install redis)\n");
	        	envMatchFlag=0;
				for(int i=0;i<getEnvListSize;i++)
				{
					 if(inputFromBot.contains(getEnvList.get(0).get(i)))
		             {
						 envMatchFlag=1;
		            	 String pnvoutput= pnvParser(getEnvList.get(1).get(i));
			    			return new QaBotModel(pnvoutput);
		             }					 
				}
	            
				if(envMatchFlag==0)
		            {
		                APP_LOGS.debug("Mention which uat, the pnv details are required (qa1/qa2/dev1/dev2) : ");
		                return new  QaBotModel("Mention which uat, the pnv details are required (qa1/qa2/dev1/dev2) : ");
		            }
				 

	        }
	            else if(inputFromBot.contains("api"))
	   		 {
	            	envMatchFlag=0;
					for(int i=0;i<getEnvListSize;i++)
					{
						 if(inputFromBot.contains(getEnvList.get(0).get(i)))
			             {
							 envMatchFlag=1;
			            	 String apiOutput= apiParser(inputFromBot,getEnvList.get(1).get(i));
				    			return new QaBotModel(apiOutput);
			             }					 
					}
					if(envMatchFlag==0)
		   	            {
		   	                APP_LOGS.debug("Mention which uat, the api url details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
			    			return new QaBotModel("Mention which uat, the api url details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
		   	            } 
	            	

	   		 }
	            else if(inputFromBot.contains("center"))
	   		 {
	            	envMatchFlag=0;
					for(int i=0;i<getEnvListSize;i++)
					{
						 if(inputFromBot.contains(getEnvList.get(0).get(i)))
			             {
							 envMatchFlag=1;
			            	 String centerOutput= centerParser(inputFromBot,getEnvList.get(1).get(i));
				    			return new QaBotModel(centerOutput);
			             }					 
					}
					if(envMatchFlag==0)
		   	            {
		   	                APP_LOGS.debug("Mention which uat, the center url details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
			    			return new QaBotModel("Mention which uat, the center url details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
		   	            } 

	   		 }
	            else if(inputFromBot.contains("product"))
	   		 {
	            	envMatchFlag=0;
					for(int i=0;i<getEnvListSize;i++)
					{
						 if(inputFromBot.contains(getEnvList.get(0).get(i)))
			             {
							 envMatchFlag=1;
			            	 String productOutput= productParser(inputFromBot,getEnvList.get(1).get(i));
				    			return new QaBotModel(productOutput);
			             }					 
					}
					if(envMatchFlag==0)
	   	            {
	   	                APP_LOGS.debug("Mention which uat, the product url details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
	   	             return new QaBotModel("Mention which uat, the product url details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
	   	            } 

	   		 }
	   		 else if(inputFromBot.contains("user"))
	   		 {
	   			envMatchFlag=0;
				for(int i=0;i<getEnvListSize;i++)
				{
					 if(inputFromBot.contains(getEnvList.get(0).get(i)))
		             {
						 envMatchFlag=1;
		            	 String userOutput= userParser(inputFromBot,getEnvList.get(1).get(i));
			    			return new QaBotModel(userOutput);
		             }					 
				}
				if(envMatchFlag==0)
	   	            {
	   	                APP_LOGS.debug("Mention which uat, the user details details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
	   	             return new QaBotModel("Mention which uat, the user details details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
	   	            }
	   			 
 
	   		 }
	   		 else if(inputFromBot.contains("promotioncode") || inputFromBot.contains("promotion code") || inputFromBot.contains("promo code"))
	   		 {
	   			envMatchFlag=0;
				for(int i=0;i<getEnvListSize;i++)
				{
					 if(inputFromBot.contains(getEnvList.get(0).get(i)))
		             {
						 envMatchFlag=1;
		            	 String promoOutput= promoParser(inputFromBot,getEnvList.get(1).get(i));
			    			return new QaBotModel(promoOutput);
		             }					 
				}
				if(envMatchFlag==0)
   	            {
   	                APP_LOGS.debug("Mention which uat, the promotion code details details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
   	             return new QaBotModel("Mention which uat, the promotion code details details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
   	            }  
	}
        return new  QaBotModel(" Mention the input correctly. ex: pnv, order etc");
	}	
	
	@ResponseBody
	@RequestMapping(value="/getInput", method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	public ModelAndView getInput(@RequestParam(value="inputValue", defaultValue="Hello") String inputValue)
	{
		APP_LOGS.debug("from input api: "+inputValue);
		GetBotInformation getEnv=new GetBotInformation();
		ArrayList<ArrayList<String>> getEnvList=getEnv.getEnvironment();
		int getEnvListSize=getEnvList.get(0).size();
		int envMatchFlag=0;
		
		ModelAndView model= new ModelAndView("index");
	
		APP_LOGS.debug("from input api: "+inputValue);
		 inputFromBot=inputValue;
		//Start calling the routers
		if (inputFromBot.contains("db") || inputFromBot.contains("database"))
       {
			envMatchFlag=0;
			for(int i=0;i<getEnvListSize;i++)
			{
				 if(inputFromBot.contains(getEnvList.get(0).get(i)))
	             {
					 envMatchFlag=1;
	            	 String dbResults= dbParser(getEnvList.get(1).get(i));
	                 model.addObject("input",dbResults);

	             }
				 
			}
			 if(envMatchFlag==0)
	            {
	                APP_LOGS.debug("Mention which uat, the order details are required (qa1/qa2/dev1/dev2) and try again  ");
	                model.addObject("input","Mention which uat, the order details are required (qa1/qa2/dev1/dev2) and try again  ");
	            }
			

       }
		 else if ((inputFromBot.contains("pnv") || inputFromBot.contains("otp") ) && !inputFromBot.contains("user"))
	        {

	            APP_LOGS.debug("\nMake sure redis is installed in local machine (To install type brew install redis)\n");
	           
	        	envMatchFlag=0;
				for(int i=0;i<getEnvListSize;i++)
				{
					 if(inputFromBot.contains(getEnvList.get(0).get(i)))
		             {
						 envMatchFlag=1;
		            	 String pnvOutput= pnvParser(getEnvList.get(1).get(i));
		                 model.addObject("input",pnvOutput);

		             }
				}
				if(envMatchFlag==0)
			            {
			                APP_LOGS.debug("Mention which uat, the pnv details are required (qa1/qa2/dev1/dev2) : ");
			                model.addObject("input","Mention which uat, the pnv details are required (qa1/qa2/dev1/dev2) : ");
			            }	
	           
	        }
		 else if(inputFromBot.contains("api"))
		 {
			 envMatchFlag=0;
				for(int i=0;i<getEnvListSize;i++)
				{
					 if(inputFromBot.contains(getEnvList.get(0).get(i)))
		             {
						 envMatchFlag=1;
		            	 String apiOutput= apiParser(inputFromBot,getEnvList.get(1).get(i));
		                 model.addObject("input",apiOutput);

		             }
				}
				if(envMatchFlag==0)
			            {
					APP_LOGS.debug("Mention which uat, the api url details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
	                model.addObject("input","Mention which uat, the api url details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
			            }
			 
			
	                
	            
		 }
		 else if(inputFromBot.contains("center"))
		 {
			 envMatchFlag=0;
				for(int i=0;i<getEnvListSize;i++)
				{
					 if(inputFromBot.contains(getEnvList.get(0).get(i)))
		             {
						 envMatchFlag=1;
		            	 String centerOutput= centerParser(inputFromBot,getEnvList.get(1).get(i));
		                 model.addObject("input",centerOutput);

		             }
				}
				if(envMatchFlag==0)
			            {
					APP_LOGS.debug("Mention which uat, the center url details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
	                model.addObject("input","Mention which uat, the center url details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
			            }
			 
			 
		 }
		 else if(inputFromBot.contains("product"))
		 {
			 envMatchFlag=0;
				for(int i=0;i<getEnvListSize;i++)
				{
					 if(inputFromBot.contains(getEnvList.get(0).get(i)))
		             {
						 envMatchFlag=1;
		            	 String productOutput= productParser(inputFromBot,getEnvList.get(1).get(i));
		                 model.addObject("input",productOutput);

		             }
				}
				if(envMatchFlag==0)
			            {
					 APP_LOGS.debug("Mention which uat, the product url details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
		                model.addObject("input","Mention which uat, the product url details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
			            }
			 
			 
			
		 }
		 else if(inputFromBot.contains("user"))
		 {
			 envMatchFlag=0;
				for(int i=0;i<getEnvListSize;i++)
				{
					 if(inputFromBot.contains(getEnvList.get(0).get(i)))
		             {
						 envMatchFlag=1;
		            	 String userOutput= userParser(inputFromBot,getEnvList.get(1).get(i));
		                 model.addObject("input",userOutput);

		             }
				}
				if(envMatchFlag==0)
			            {
					 APP_LOGS.debug("Mention which uat, the user information details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
		                model.addObject("input","Mention which uat, the user information details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
			            }
			 
			  
		 }
		 else if(inputFromBot.contains("promotioncode") || inputFromBot.contains("promotion code") || inputFromBot.contains("promo code"))
		 {
			 envMatchFlag=0;
				for(int i=0;i<getEnvListSize;i++)
				{
					 if(inputFromBot.contains(getEnvList.get(0).get(i)))
		             {
						 envMatchFlag=1;
		            	 String promoOutput= promoParser(inputFromBot,getEnvList.get(1).get(i));
		                 model.addObject("input",promoOutput);

		             }
				}
				if(envMatchFlag==0)
			            {
					APP_LOGS.debug("Mention which uat, the promotion code details details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
	                model.addObject("input","Mention which uat, the promotion code details details are required (qa1/qa2/dev1/dev2/pre-prod) : ");
			            }
			 
			
		 }
		 else
		 {
             model.addObject("Mention the input correctly, else check the configuration details.");
		 }

			return model;
			
            }          
            
    public String dbParser(String uatSource)
    {
        APP_LOGS.debug("Enter the order id : ");
        
        String[] inputFromParser=inputFromBot.split(" ");
    	int correctIndex=0;
    	
    	
    	
    	for(int i=0;i<inputFromParser.length;i++)
    	{
    		if(!inputFromParser[i].equalsIgnoreCase("db") && !inputFromParser[i].equalsIgnoreCase("database") && !inputFromParser[i].equalsIgnoreCase(uatSource.replaceAll("-", "")))
    		{
    			correctIndex=i;
    		}
    	}
        APP_LOGS.debug("DB Order id extraceted before regex:"+inputFromBot);

        inputFromBot=inputFromBot.replaceAll("[^0-9]", "");

        
        APP_LOGS.debug("DB Order id extraceted after regex:"+inputFromBot);

       // String inputOrderId =  inputFromParser[correctIndex];
         
        String inputOrderId = inputFromBot;
        
        APP_LOGS.debug("DB Order id extraceted:"+inputOrderId);
		GetDatabaseDetails db=new GetDatabaseDetails();
		String dbResults=db.getOrderInformation(uatSource,inputOrderId);
		return dbResults;
    }
    
    public String pnvParser(String source)
    {
       String[] inputEmailArray = inputFromBot.split(" ");
		int correctIndex = 2;
		for(int i=0;i<inputEmailArray.length;i++)
    	{
    		if(inputEmailArray[i].contains("@") )
    		{
    			correctIndex=i;
    		}
    	}
		
        
        String inputEmail =  inputEmailArray[correctIndex];
		
		
         String pnvHost=botProp.getProperty("Otp".concat(source));
		GetPnv gl1=new GetPnv();
		String pnvOutput=gl1.getPnv(pnvHost,inputEmail);
		return pnvOutput;

    }
    public String apiParser(String inputFromRouter,String source)
    {
    	String[] inputFromParser=inputFromRouter.split(" ");
    	int correctIndex=0;
    	
    	for(int i=0;i<inputFromParser.length;i++)
    	{
    		if(!inputFromParser[i].equalsIgnoreCase("api") 
    				&& !inputFromParser[i].equalsIgnoreCase(source.replaceAll("-", ""))
    				&& !inputFromParser[i].equalsIgnoreCase("for")
    				&& !inputFromParser[i].equalsIgnoreCase("i")
    				&& !inputFromParser[i].equalsIgnoreCase("need")
    				&& !inputFromParser[i].equalsIgnoreCase("service")
    				&& !inputFromParser[i].equalsIgnoreCase("swagger")
    				&& !inputFromParser[i].equalsIgnoreCase("please")
    				&& !inputFromParser[i].equalsIgnoreCase("in")
    				&& !inputFromParser[i].equalsIgnoreCase("want")
    				&& !inputFromParser[i].equalsIgnoreCase("give")
    				&& !inputFromParser[i].equalsIgnoreCase("url")
    				&& !inputFromParser[i].equalsIgnoreCase("link")
    				&& !inputFromParser[i].equalsIgnoreCase("have")
    				&& !inputFromParser[i].equalsIgnoreCase("can")
    				&& !inputFromParser[i].equalsIgnoreCase("details")
    				&& !inputFromParser[i].equalsIgnoreCase("info")
    				)
    		{
    			correctIndex=i;
    		}
    	}
    	
    	GetBotInformation apiget=new GetBotInformation();
    	String outputUrl=null;
    	 outputUrl=apiget.getApiInformation(source, "API", inputFromParser[correctIndex]);
    	if(outputUrl == null){
    		outputUrl="No Results found for the given API details.<br /> Please check if configuration details are added.";
    	}
    	return outputUrl;
    }
    public String centerParser(String inputFromRouter,String source)
    {
    	String[] inputFromParser=inputFromRouter.split(" ");
    	int correctIndex=0;
    	
    	for(int i=0;i<inputFromParser.length;i++)
    	{
    		if(!inputFromParser[i].equalsIgnoreCase("center") 
    				&& !inputFromParser[i].equalsIgnoreCase(source.replaceAll("-", ""))
    				&& !inputFromParser[i].equalsIgnoreCase("for")
    				&& !inputFromParser[i].equalsIgnoreCase("i")
    				&& !inputFromParser[i].equalsIgnoreCase("need")
    				&& !inputFromParser[i].equalsIgnoreCase("service")
    				&& !inputFromParser[i].equalsIgnoreCase("swagger")
    				&& !inputFromParser[i].equalsIgnoreCase("please")
    				&& !inputFromParser[i].equalsIgnoreCase("in")
    				&& !inputFromParser[i].equalsIgnoreCase("want")
    				&& !inputFromParser[i].equalsIgnoreCase("give")
    				&& !inputFromParser[i].equalsIgnoreCase("url")
    				&& !inputFromParser[i].equalsIgnoreCase("link")
    				&& !inputFromParser[i].equalsIgnoreCase("have")
    				&& !inputFromParser[i].equalsIgnoreCase("can")
    				&& !inputFromParser[i].equalsIgnoreCase("details")
    				&& !inputFromParser[i].equalsIgnoreCase("info")
    				)
    		{
    			correctIndex=i;
    		}
    	}
    	
    	GetBotInformation centerGet=new GetBotInformation();
    	String outputUrl=null;
    	 outputUrl=centerGet.getCenterInformation(source, "CENTER", inputFromParser[correctIndex]);
    	if(outputUrl == null){
    		outputUrl="No Results found for the given Center details.<br /> Please check if configuration details are added.";
    	}
    	return outputUrl;
    }
    public String productParser(String inputFromRouter,String source)
    {
    	String[] inputFromParser=inputFromRouter.split(" ");
    	int correctIndex=0;
    	
    	for(int i=0;i<inputFromParser.length;i++)
    	{
    		if(!inputFromParser[i].equalsIgnoreCase("product") 
    				&& !inputFromParser[i].equalsIgnoreCase(source.replaceAll("-", ""))
    				&& !inputFromParser[i].equalsIgnoreCase("for")
    				&& !inputFromParser[i].equalsIgnoreCase("i")
    				&& !inputFromParser[i].equalsIgnoreCase("need")
    				&& !inputFromParser[i].equalsIgnoreCase("service")
    				&& !inputFromParser[i].equalsIgnoreCase("swagger")
    				&& !inputFromParser[i].equalsIgnoreCase("please")
    				&& !inputFromParser[i].equalsIgnoreCase("in")
    				&& !inputFromParser[i].equalsIgnoreCase("want")
    				&& !inputFromParser[i].equalsIgnoreCase("give")
    				&& !inputFromParser[i].equalsIgnoreCase("url")
    				&& !inputFromParser[i].equalsIgnoreCase("link")
    				&& !inputFromParser[i].equalsIgnoreCase("have")
    				&& !inputFromParser[i].equalsIgnoreCase("can")
    				&& !inputFromParser[i].equalsIgnoreCase("details")
    				&& !inputFromParser[i].equalsIgnoreCase("info")
    				)
    		{
    			correctIndex=i;
    		}
    	}
    	
    	GetBotInformation productGet=new GetBotInformation();
    	String outputUrl=null;
    	 outputUrl=productGet.getProductInformation(source, "PRODUCT", inputFromParser[correctIndex]);
    	if(outputUrl == null){
    		outputUrl="No Results found for the given Product details.<br /> Please check if configuration details are added.";
    	}
    	return outputUrl;
    }
    public String userParser(String inputFromRouter,String source)
    {
    	String[] inputFromParser=inputFromRouter.split(" ");
    	int correctIndex=0;
    	
    	for(int i=0;i<inputFromParser.length;i++)
    	{
    		if((!inputFromParser[i].equalsIgnoreCase("user") 
    				&& !inputFromParser[i].equalsIgnoreCase(source.replaceAll("-", ""))
    				&& !inputFromParser[i].equalsIgnoreCase("for")
    				&& !inputFromParser[i].equalsIgnoreCase("i")
    				&& !inputFromParser[i].equalsIgnoreCase("need")
    				&& !inputFromParser[i].equalsIgnoreCase("service")
    				&& !inputFromParser[i].equalsIgnoreCase("swagger")
    				&& !inputFromParser[i].equalsIgnoreCase("please")
    				&& !inputFromParser[i].equalsIgnoreCase("in")
    				&& !inputFromParser[i].equalsIgnoreCase("want")
    				&& !inputFromParser[i].equalsIgnoreCase("give")
    				&& !inputFromParser[i].equalsIgnoreCase("url")
    				&& !inputFromParser[i].equalsIgnoreCase("link")
    				&& !inputFromParser[i].equalsIgnoreCase("have")
    				&& !inputFromParser[i].equalsIgnoreCase("can")
    				&& !inputFromParser[i].equalsIgnoreCase("details")
    				&& !inputFromParser[i].equalsIgnoreCase("info")
    				) || inputFromParser[i].equalsIgnoreCase("verified")

    				)
    		{
    			correctIndex=i;
    		}
    	}
    	
    	
    	
    	GetBotInformation productGet=new GetBotInformation();
    	String outputUrl=null;
    	 outputUrl=productGet.getUserInformation(source, "USER", inputFromParser[correctIndex].replaceAll("verified", "pnv"));
    	if(outputUrl == null){
    		outputUrl="No Results found for the given User details.<br />Please check if configuration details are added.";
    	}
    	return outputUrl;
    }
    
    public String promoParser(String inputFromRouter,String source)
    {
    	
    	
    	GetBotInformation productGet=new GetBotInformation();
    	String outputUrl=null;
    	 outputUrl=productGet.getPromoInformation(source, "PROMOTIONCODE", "promotioncode");
    	if(outputUrl == null){
    		outputUrl="No Results found for the given Promotion Code details.<br /> Please check if configuration details are added.";
    	}
    	return outputUrl;
    }
}
