package com.company.project.service;
import com.company.project.model.Meeting;

import java.util.List;

import com.company.project.core.Service;


/**
 * Created by tang zhi on 2018/05/13.
 */
public interface MeetingService extends Service<Meeting> {
	Meeting getNewest();
	List<Meeting> getChart();
}
