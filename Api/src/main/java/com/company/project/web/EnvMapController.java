package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dto.env.EnvFrameDTO;
import com.company.project.dto.env.EnvTableDTO;
import com.company.project.dto.env.TableStruct;
import com.company.project.model.*;
import com.company.project.service.*;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tang zhi
 * @date 2019/12/13
 */
@RestController
@RequestMapping("/env/map")
public class EnvMapController {
    @Resource
    private EnvFrameService envFrameService;

    @Resource
    private EnvTableDataService envTableDataService;

    @Resource
    private EnvFrameTableService envFrameTableService;

    @Resource
    private EnvTableDataService tableDataService;

    @Resource
    private EnvTableHeadService tableHeadService;

    @Resource
    private EnvTableService tableService;

    @GetMapping
    public Result mapFrameData() {
        Multimap<Integer, Map<String, Object>> tableDataMap = getTableData();
        Multimap<Integer, TableStruct> tableStructMap = getTableStruct();

        //所有面板和表格的关系数据
        List<EnvFrameTable> panelMapTables = envFrameTableService.findAll();


        List<EnvFrameDTO> frameList = envFrameService.findAllAndTable();
        List<EnvFrameDTO> mapFrame = buildEnvMap(frameList, 0);
        mapFrame.get(0).getChildren().forEach(map -> {
            //查询面板下所有的表格
            List<EnvFrameTable> tables = panelMapTables.stream()
                    .filter(f -> f.getPanelId().equals(map.getId()))
                    .collect(Collectors.toList());


            List<EnvTableDTO> envTables = new ArrayList<>(16);
            for (EnvFrameTable table : tables) {
                EnvTableDTO envTable = new EnvTableDTO();

                envTable.setId(table.getTableId());
                //通过表格ID查询表格结构
                Collection<TableStruct> tabStruct = tableStructMap.asMap().get(table.getTableId());
                if (tabStruct != null) {
                    envTable.setTableStruct(tabStruct);
                }

                //通过表格ID查询表格数据
                Collection<Map<String, Object>> arrayListMultimap = tableDataMap.asMap().get(table.getTableId());
                if (arrayListMultimap != null) {
                    envTable.setTableData(arrayListMultimap);
                }
                envTables.add(envTable);
            }
            map.setTables(envTables);
        });
        return ResultGenerator.genSuccessResult(mapFrame);
    }

    private Multimap<Integer, TableStruct> getTableStruct() {
        List<TableStruct> tableStructList = envFrameTableService.findTableFrame(null);

        Multimap<Integer, TableStruct> listMultimap = ArrayListMultimap.create();

        for (TableStruct struct : tableStructList) {
            listMultimap.put(struct.getTableId(), struct);
        }
        return listMultimap;
    }

    @GetMapping("/frame")
    public Result frame() {
        List<EnvFrameDTO> frameList = envFrameService.findAllAndTable();
        return ResultGenerator.genSuccessResult(buildEnvMap(frameList, 0));
    }

    /**
     * 递归构建文档树
     *
     * @param frameList 结构节点集合
     * @param id        父类ID
     * @return 树集合
     */
    private List<EnvFrameDTO> buildEnvMap(List<EnvFrameDTO> frameList, int id) {
        List<EnvFrameDTO> envMap = new ArrayList<>();
        frameList.stream().filter(f -> f.getParentId() == id).forEach(f -> {
            EnvFrameDTO envFrame = new EnvFrameDTO();
            envFrame.setId(f.getId());
            envFrame.setLabel(f.getLabel());
            envFrame.setLevel(f.getLevel());
            envFrame.setLevelName(f.getLevelName());
            envFrame.setChildren(buildEnvMap(frameList, f.getId()));
            envMap.add(envFrame);
        });
        return envMap;
    }

    @PostMapping("/frame/save")
    public Result add(@RequestBody EnvFrame envMapFrame) {
        if (envMapFrame.getId() == null || envMapFrame.getId().equals(0)) {
            envFrameService.save(envMapFrame);
        } else {
            envFrameService.update(envMapFrame);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/frame/delete")
    public Result frameDelete(@RequestBody EnvFrame frame) {
        envFrameService.deleteById(frame.getId());
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/frame/join/table/save")
    public Result frameJoinTableSave(@RequestBody EnvFrameTable frameTable) {
        if (frameTable.getId() == null || frameTable.getId().equals(0)) {
            envFrameTableService.save(frameTable);
        } else {
            envFrameTableService.update(frameTable);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/frame/join/table/delete")
    public Result frameJoinTableDelete(@RequestBody EnvFrameTable frameTable) {
        envFrameTableService.deleteById(frameTable.getId());
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/table/save")
    public Result tableSave(@RequestBody EnvFrame envFrame) {
        EnvTable envTable = new EnvTable();
        envTable.setTableName(envFrame.getLabel());
        envTable.setId(envFrame.getId());
        if (envTable.getId() == null || envTable.getId().equals(0)) {
            tableService.save(envTable);
        } else {
            tableService.update(envTable);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/table/delete")
    public Result tableDelete(@RequestBody EnvTable table) {
        tableService.deleteById(table.getId());
        return ResultGenerator.genSuccessResult();
    }


    @GetMapping("/table/data")
    public Result table() {
        return ResultGenerator.genSuccessResult(getTableData());
    }

    @PostMapping("/table/data/save")
    public Result tableSave(@RequestBody List<EnvTableData> envTableData) {
        long rowId = System.currentTimeMillis();
        envTableData.forEach(data -> {
            if (data.getId() == null || data.getId() == 0) {
                data.setRowId(rowId);
                tableDataService.save(data);
            } else {
                tableDataService.update(data);
            }
        });
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/table/data/delete")
    public Result delete(@RequestBody List<EnvTableData> envTableData) {
        //可根据RowId一次性删除4条,懒
        envTableData.forEach(data -> tableDataService.deleteById(data.getId()));
        return ResultGenerator.genSuccessResult();
    }

    private Multimap<Integer, Map<String, Object>> getTableData() {
        List<TableStruct> tableData = envTableDataService.findAllTableData();
        Multimap<Integer, Map<String, Object>> listMultimap = ArrayListMultimap.create();

        Map<Long, Long> rowMap = new HashMap<>(16);
        Map<String, Object> mapRow = Maps.newHashMap();
        for (TableStruct tab : tableData) {
            Map<String, Object> map = Maps.newHashMap();
            if (!rowMap.containsKey(tab.getRowId())) {
                mapRow = Maps.newHashMap();
                listMultimap.put(tab.getTableId(), mapRow);
                rowMap.put(tab.getRowId(), tab.getRowId());
            }
            map.put("rowId", tab.getRowId());
            map.put("headId", tab.getHeadId());
            map.put("id", tab.getId());
            map.put(tab.getField(), tab.getContent());
            map.put("tips", tab.getTips());
            mapRow.put(tab.getField(), map);
        }
        return listMultimap;
    }

    @GetMapping("/table/frame")
    public Result tableFrame(EnvFrameTable envFrameTable) {

        List<TableStruct> frameTables = envFrameTableService.findTableFrame2(envFrameTable);
        return ResultGenerator.genSuccessResult(frameTables);
    }

    @PostMapping("/table/frame/save")
    public Result tableFrameSave(@RequestBody EnvTableHead tableHead) {
        if (tableHead.getId() == null || tableHead.getId() == 0) {
            tableHeadService.save(tableHead);
        } else {
            tableHeadService.update(tableHead);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/table/frame/delete")
    public Result tableFrameDelete(@RequestBody EnvTableHead tableHead) {
        tableHeadService.deleteById(tableHead.getId());
        return ResultGenerator.genSuccessResult();
    }

}
