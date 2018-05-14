package com.company.project.service.impl;

import com.company.project.dao.MeetingMapper;
import com.company.project.model.Meeting;
import com.company.project.service.MeetingService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;


/**
 * Created by tang zhi on 2018/05/13.
 */
@Service
@Transactional
public class MeetingServiceImpl extends AbstractService<Meeting> implements MeetingService {
    @Resource
    private MeetingMapper meetingMapper;
	public Meeting getNewest() {
		return meetingMapper.getNewest();
	}
	public List<Meeting> getChart(){
		return meetingMapper.getChart();
	}
}
