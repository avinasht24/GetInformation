package com.gdn.database;

import com.atlassian.jira.rest.client.api.AuthenticationHandler;
import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import com.gdn.qabot.controller.QaBotController;
import com.gdn.qabot.model.QaBotBugCreationModel;

import org.joda.time.DateTime;

import java.io.IOException;
import java.net.URI;

public class BugCreationJira extends QaBotController {
	
    public static JiraRestClient restClient;

	
	public String createNewJiraTicket(QaBotBugCreationModel createBug ) throws IOException
	{
		String jiraStatus=null;
		 URI jiraServerUri = URI.create("https://jira.gdn-app.com");
	        URI jiraServerUriIT = URI.create("https://jira.gdn-app.com/rest/api/latest/issuetype/1");

	        AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();

	        AuthenticationHandler auth = new BasicHttpAuthenticationHandler(createBug.getJira_username(), createBug.getJira_password());
	         restClient = factory.create(jiraServerUri, auth);
	        IssueRestClient issueClient = restClient.getIssueClient();
	        Promise<IssueType> itValue;
	        

	        itValue =restClient.getMetadataClient().getIssueType(jiraServerUriIT);
	        System.out.println("IT:"+restClient.getMetadataClient().getIssueType(jiraServerUriIT));
	  
	        Long iId= Long.valueOf(1);

	        try {
	            IssueInputBuilder iib = new IssueInputBuilder();
	            iib.setProjectKey(createBug.getProject_code());
	            iib.setSummary(createBug.getTitle_summary());
	            iib.setIssueTypeId(iId);
	            iib.setDescription(createBug.getTitle_summary());
	            iib.setPriorityId(3L);
	            iib.setAssigneeName(createBug.getAssignee_name());
	     

	            IssueInput issue = iib.build();
	            BasicIssue issueObj = issueClient.createIssue(issue).claim();

	            APP_LOGS.debug("Issue " + issueObj.getKey() + " created successfully");
	            jiraStatus="Issue " + issueObj.getKey() + " created successfully";
	            
	            
	        } finally {
	            restClient.close();
	        }
	        
	        return jiraStatus;

	        }

		
	}
	


