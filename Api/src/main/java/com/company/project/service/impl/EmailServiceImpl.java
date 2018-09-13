package com.company.project.service.impl;

import com.company.project.dao.EmailMapper;
import com.company.project.model.Email;
import com.company.project.service.EmailService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by tang zhi on 2018/09/13.
 */
@Service
@Transactional
public class EmailServiceImpl extends AbstractService<Email> implements EmailService {
    @Resource
    private EmailMapper emailMapper;

}
