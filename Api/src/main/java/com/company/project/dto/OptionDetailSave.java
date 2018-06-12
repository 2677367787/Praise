package com.company.project.dto;

import com.company.project.model.VoteOptionDetail;

import java.util.List;

public class OptionDetailSave {
	private Integer voteId;
	private List<VoteOptionDetail> voteOptionDetailList;

	public List<VoteOptionDetail> getVoteOptionDetailList() {
		return voteOptionDetailList;
	}

	public void setVoteOptionDetailList(List<VoteOptionDetail> voteOptionDetailList) {
		this.voteOptionDetailList = voteOptionDetailList;
	}

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}
}
