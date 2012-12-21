package com.wufulin.pattern.ChainOfResponsibility;

public abstract class AbstractRequest {

	private String content = null;
	
	public AbstractRequest(String content){
		this.content = content;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public abstract int getRequestLevel();
}
