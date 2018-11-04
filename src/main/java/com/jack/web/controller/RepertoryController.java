package com.jack.web.controller;


import com.github.pagehelper.PageInfo;
import com.jack.dto.RepertoryDTO;
import com.jack.exception.NameExistException;
import com.jack.exception.NotFoundCodeException;
import com.jack.pojo.entity.Repertory;
import com.jack.service.RepertoryService;
import com.jack.util.PageQuery;
import com.jack.util.State;
import com.jack.util.TmResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("/rep/")
@Controller
public class RepertoryController {

    private final Logger logger = LoggerFactory.getLogger(RepertoryController.class);

    @Autowired
    private RepertoryService repertoryService;

    /**
     * 添加库房
     * @param repertory
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse addRepertory(Repertory repertory){
        String msg = validate(repertory);
        if(msg != null){
            return TmResponse.fail(msg);
        }
        try{
            boolean res = repertoryService.addRepertory(repertory);
            if(res){
                return TmResponse.success("新增库房成功");
            }else{
                return TmResponse.fail("新增库房失败");
            }
        }catch (NameExistException e){
            return TmResponse.fail(e.getMessage());
        }
    }

    /**
     * 删除指定的库房
     * @param repId
     * @return
     */
    @RequestMapping(value = "del", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse deleteRepertory(Long repId){
        if(repId == null){
            return TmResponse.fail("请传入指定参数");
        }
        if(repertoryService.deleteRepertory(repId)){
            return TmResponse.success("删除成功");
        }else{
            return TmResponse.fail("删除失败");
        }
    }

    /**
     * 查询指定的库房
     * @param repId
     * @return
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse findOne(Long repId){
        if(repId == null){
            return TmResponse.fail("请传入指定参数");
        }
        Repertory repertory = repertoryService.findOne(repId);
        if(repertory == null){
            return TmResponse.fail("未查询到指定的库房");
        }else{
            return TmResponse.success("查询成功",repertory);
        }
    }

    /**
     * 更新库房信息
     * @param repertory
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse updateRepertory(Repertory repertory){
        String msg = validate(repertory);
        if(msg != null){
            return TmResponse.fail(msg);
        }
        if(repertory.getRepId() == null){
            return TmResponse.fail("请传入指定参数");
        }
        try{
            boolean res= repertoryService.updateRepertory(repertory);
            if(res){
                return TmResponse.success("更新成功");
            }else{
                return TmResponse.fail("更新失败");
            }
        }catch (NameExistException e){
            return TmResponse.fail(e.getMessage());
        }
    }
    @RequestMapping(value = "changeState", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse changeRepertoryState(@RequestParam Map<String,String> params){
        String tempRepId = params.get("repId");
        Repertory repertory = new Repertory();
        if(StringUtils.isNotBlank(tempRepId)){
            Long repId;
            try{
                repId = Long.parseLong(tempRepId);
            }catch (NumberFormatException e){
                return TmResponse.fail("参数异常");
            }
            Repertory existedRepertory = repertoryService.findOne(repId);
            if(existedRepertory == null){
                return TmResponse.fail("未查询到指定库房");
            }
            repertory.setRepId(repId);
        }
        String stateCode = params.get("stateCode");
        if(StringUtils.isNotBlank(stateCode)){
            Integer state;
            try{
                state = Integer.parseInt(stateCode);
            }catch (NumberFormatException e){
                return TmResponse.fail("请传入整数状态码");
            }
            try{
                repertory.setState(State.CommonState.codeOf(state));
            }catch (NotFoundCodeException e){
                return TmResponse.fail("状态码参数异常");
            }
        }
        if(repertoryService.updateRepertory(repertory)){
            return TmResponse.success("更改状态成功");
        }else{
            return TmResponse.fail("更改状态失败");
        }
    }
    /**
     * 查询所有可用的库房
     * @param pageQuery
     * @return
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse findAll(PageQuery pageQuery){
        if(pageQuery == null){
            return TmResponse.fail("请传入指定参数");
        }
        PageInfo<RepertoryDTO> repertoryDTOPageInfo = repertoryService.findAllByState(pageQuery, State.CommonState.NORMAL);
        return TmResponse.success("查询所有可用库房",repertoryDTOPageInfo);
    }

    /**
     * 条件查询部门库房
     * @param pageQuery
     * @param params
     * @return
     */
    @RequestMapping(value = "findPart", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse findPart(PageQuery pageQuery, @RequestParam Map<String,String> params){
        if(pageQuery == null){
            return TmResponse.fail("请传入指定参数");
        }
        Repertory repertory = new Repertory();
        String repName = params.get("repName");
        if(StringUtils.isNotBlank(repName)){
            String tempName = new StringBuilder().append("%").append(repName).append("%").toString();
            repertory.setRepName(tempName);
        }
        String stateCode = params.get("stateCode");
        if(StringUtils.isNotBlank(stateCode)){
            Integer state;
            try{
                state = Integer.parseInt(stateCode);
            }catch (NumberFormatException e){
                return TmResponse.fail("请传入整数类型状态码");
            }
            try{
                repertory.setState(State.CommonState.codeOf(state));
            }catch (NotFoundCodeException e){
                return TmResponse.fail("状态码参数异常");
            }
        }
        PageInfo<RepertoryDTO> repertoryDTOPageInfo = repertoryService.findByCondition(pageQuery, repertory);
        return TmResponse.success("条件查询库房成功",repertoryDTOPageInfo);
    }

    /**
     * 查询可用库房集合
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse list(){
        List<Repertory> repertoryList = repertoryService.findAllForSelect();
        if(repertoryList.size() == 0){
            return TmResponse.fail("暂无可用库房");
        }
        return TmResponse.success("查询可用库房成功", repertoryList);
    }










/*------------------------私有方法-----------------------------------------------------*/
    private String validate(Repertory repertory){
        if(repertory == null){
            return "请传入指定参数";
        }
        if(StringUtils.isBlank(repertory.getRepName())){
            return "库房名称不能为空";
        }
        return null;
    }

}
