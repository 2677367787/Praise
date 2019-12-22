package com.company.project.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mr.Tang
 * create 2019-12-01 12:26
 **/
@RestController
@RequestMapping("/env")
@Api("环境地图")
public class EnvironmentMapController {
//    @Resource
//    private EnvTagService tagService;
//
//    @Resource
//    private EnvTableBodyService envTableBodyService;
//
//    @Resource
//    private EnvPanelService panelService;
//
//    @ApiOperation("返回所有配置的环境资料")
//    @GetMapping("/map")
//    public Result add() {
//
//        List<EnvironmentMap> environmentMap = tagService.findAllEnvMap();
//
//        return ResultGenerator.genSuccessResult(environmentMap);
//    }
//
//    @ApiOperation("返回所有配置的环境资料")
//    @PostMapping("/save")
//    public Result save(@RequestBody MapFrameDTO tag) {
//
//        switch (tag.getType()){
//            case "panel":
//                EnvPanel envPanel = new EnvPanel();
//                envPanel.setTagId(tag.getId());
//                envPanel.setPanelName(tag.getName());
//                panelService.save(envPanel);
//                break;
//            case "tag":
//                EnvTag envTag = new EnvTag();
//                envTag.setTagName(tag.getName());
//                tagService.save(envTag);
//                break;
//            default:
//                break;
//        }
//        return ResultGenerator.genSuccessResult("添加成功!");
//    }
}
