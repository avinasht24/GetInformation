package com.gdn.qabot.model;
/**
 * Created by avinash.t
 */
public class QaBotConfigModel {

	private String environment;
	private String source_type;
	private String bot_keyword;
	private String insertFlag=null;
	public String getInsertFlag() {
		return insertFlag;
	}
	public void setInsertFlag(String insertFlag) {
		this.insertFlag = insertFlag;
	}
	private String url;
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getSource_type() {
		return source_type;
	}
	public void setSource_type(String source_type) {
		this.source_type = source_type;
	}
	public String getBot_keyword() {

			return bot_keyword;

	}
	public void setBot_keyword(String bot_keyword) {

			this.bot_keyword = bot_keyword;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
	
}
