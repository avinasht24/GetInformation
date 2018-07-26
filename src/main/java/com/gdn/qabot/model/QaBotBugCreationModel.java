package com.gdn.qabot.model;

public class QaBotBugCreationModel {
private String jira_username;
private String jira_password;
private String project_code;
private String title_summary;
private String bug_description;
private String insertFlag;
private String assignee_name;
public String getAssignee_name() {
	return assignee_name;
}
public void setAssignee_name(String assignee_name) {
	this.assignee_name = assignee_name;
}
public String getJira_username() {
	return jira_username;
}
public void setJira_username(String jira_username) {
	this.jira_username = jira_username;
}
public String getJira_password() {
	return jira_password;
}
public void setJira_password(String jira_password) {
	this.jira_password = jira_password;
}
public String getProject_code() {
	return project_code;
}
public void setProject_code(String project_code) {
	this.project_code = project_code;
}
public String getTitle_summary() {
	return title_summary;
}
public void setTitle_summary(String title_summary) {
	this.title_summary = title_summary;
}
public String getBug_description() {
	return bug_description;
}
public void setBug_description(String bug_description) {
	this.bug_description = bug_description;
}
public String getInsertFlag() {
	return insertFlag;
}
public void setInsertFlag(String insertFlag) {
	this.insertFlag = insertFlag;
}


	
	
}
