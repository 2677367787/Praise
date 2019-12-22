package com.company.project.dto.env;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Mr.Tang
 * create 2019-12-05 0:02
 **/
@Getter
@Setter
@ToString
public class MapFrameDTO {

    private Integer id;

    private String label;

    private Integer level;

    private String levelName;

    private List<EnvFrameDTO> children;

    private Object tableData;

}
