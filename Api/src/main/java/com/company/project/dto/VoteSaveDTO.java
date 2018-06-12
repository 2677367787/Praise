package com.company.project.dto;

import com.company.project.model.Vote;
import com.company.project.model.VoteOption;

import java.util.List;

/**
 * @author tanggzhi
 */
public class VoteSaveDTO extends Vote {
	private List<VoteOption> voteOptions;

	public List<VoteOption> getVoteOptions() {
		return voteOptions;
	}

	public void setVoteOptions(List<VoteOption> voteOptions) {
		this.voteOptions = voteOptions;
	}
}
