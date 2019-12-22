package com.company.project.dto.env;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Mr.Tang
 * create 2019-12-13 23:51
 **/
@Getter
@Setter
@ToString
public class EnvFrameDTO {
    private Integer id;

    private Integer parentId;

    private String label;

    private Integer level;

    private String levelName;

    private List<EnvFrameDTO> children;

    private List<EnvTableDTO> tables;

}
