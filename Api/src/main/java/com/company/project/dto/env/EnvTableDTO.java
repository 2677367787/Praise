package com.company.project.dto.env;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Tang
 * create 2019-12-01 12:36
 **/
@Getter
@Setter
@ToString
public class EnvTableDTO {

    private Integer id;

    private List<TableStruct> tableStruct;

    private List<Map<String, Map<String, Object>>> tableData;
}
