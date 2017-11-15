package com.technology.Model;


public class Json {
	private String msg;
	private Boolean success;
	
	
	public Json(String msg, Boolean success) {
		super();
		this.msg = msg;
		this.success = success;
	}
	public Json() {
		super();
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	} 
	
}
