package com.company.project.dto;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class PraiseListQueryDTO {
	/**
	 * 工号
	 */
	private String userName;
	/**
	 * 点赞开始时间
	 */
	@JSONField (format="yyyy-MM-dd") 
	private String praiseDateBegin;
	/**
	 * 点赞结束时间
	 */
	@JSONField (format="yyyy-MM-dd") 
	private String praiseDateEnd;
	
	/**
	 * 点赞
	 */
	private String praiseTo;
	
	/**
	 * 被点赞
	 */
	private String praiseFrom;
	
	private int pageNum;
	
	private int pageSize;
	
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
	public String getPraiseFrom() {
		return praiseFrom;
	}
	public void setPraiseFrom(String praiseFrom) {
		this.praiseFrom = praiseFrom;
	} 
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
