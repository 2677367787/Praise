package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.dto.env.EnvFrameDTO;
import com.company.project.model.EnvFrame;

import java.util.List;

public interface EnvFrameMapper extends Mapper<EnvFrame> {

    /**
     * 查询标签到表格的所有数据
     * @return List<EnvFrameDTO>
     */
    List<EnvFrameDTO> findAllAndTable();

}