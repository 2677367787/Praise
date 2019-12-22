package com.company.project.service;
import com.company.project.dto.env.EnvFrameDTO;
import com.company.project.model.EnvFrame;
import com.company.project.core.Service;

import java.util.List;


/**
 *
 * @author tang zhi
 * @date 2019/12/18
 */
public interface EnvFrameService extends Service<EnvFrame> {

    /**
     * 查询标签到表格的所有数据
     * @return List<EnvFrameDTO>
     */
    List<EnvFrameDTO> findAllAndTable();
}
