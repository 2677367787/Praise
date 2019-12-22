package com.company.project.dto.env;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Mr.Tang
 * create 2019-12-18 22:22
 **/
@Getter
@Setter
@ToString
public class TableStruct {
    private Integer id;
    private String tableName;
    private Integer headId;
    private Integer tableId;
    private String field;
    private String cellName;
    private String cellType;
    private Long rowId;
    private String content;
    private String tips;

    private List<TableStruct> tableStructList;
}
