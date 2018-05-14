package com.company.project.dao;

import java.util.List;

import com.company.project.core.Mapper;
import com.company.project.model.Meeting;

public interface MeetingMapper extends Mapper<Meeting> {

	Meeting getNewest();

	List<Meeting> getChart();
}