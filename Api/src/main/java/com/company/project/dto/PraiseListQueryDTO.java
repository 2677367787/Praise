package com.company.project.dto;

import java.util.Date;

public class PraiseListQueryDTO {
	/**
	 * 工号
	 */
	private String userName;
	/**
	 * 点赞开始时间
	 */
	private String praiseDateBegin;
	/**
	 * 点赞结束时间
	 */
	private String praiseDateEnd;
	
	private String praiseTo;
	
	public String getPraiseDateBegin() {
		return praiseDateBegin;
	}
	public void setPraiseDateBegin(String praiseDateBegin) {
		this.praiseDateBegin = praiseDateBegin;
	}
	public String getPraiseDateEnd() {
		return praiseDateEnd;
	}
	public void setPraiseDateEnd(String praiseDateEnd) {
		this.praiseDateEnd = praiseDateEnd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPraiseTo() {
		return praiseTo;
	}
	public void setPraiseTo(String praiseTo) {
		this.praiseTo = praiseTo;
	}
}
