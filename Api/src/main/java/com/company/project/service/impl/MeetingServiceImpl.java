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
 *
 * @author tang zhi
 * @date 2018/05/13
 */
@Service
public class MeetingServiceImpl extends AbstractService<Meeting> implements MeetingService {
    @Resource
    private MeetingMapper meetingMapper;
	@Override
    public Meeting getNewest() {
		return meetingMapper.getNewest();
	}
	@Override
    public List<Meeting> getChart(){
		return meetingMapper.getChart();
	}
}
