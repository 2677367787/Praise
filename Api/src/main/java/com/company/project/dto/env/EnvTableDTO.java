package com.company.project.dto.env;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

/**
 * @author Mr.Tang
 * create 2019-12-01 12:36
 **/
@Getter
@Setter
@ToString
public class EnvTableDTO {

    private Integer id;

    private Object tableStruct;

    private Object tableData;
}
