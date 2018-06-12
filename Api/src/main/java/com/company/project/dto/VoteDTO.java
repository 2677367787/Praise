package com.company.project.dto;

import com.company.project.model.Vote;
import com.company.project.model.VoteOption;

import java.io.Serializable;
import java.util.List;

/**
 * @author tanggzhi
 */
public class VoteDTO implements Serializable {

	private static final long serialVersionUID = 5964450781198968404L;
	private Integer voteId;
	private Vote vote;
	private List<VoteOption> voteOption;

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public List<VoteOption> getVoteOption() {
		return voteOption;
	}

	public void setVoteOption(List<VoteOption> voteOption) {
		this.voteOption = voteOption;
	}

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}
}
