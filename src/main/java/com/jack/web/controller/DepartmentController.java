package com.jack.web.controller;

import com.github.pagehelper.PageInfo;
import com.jack.dto.DepartmentDTO;
import com.jack.exception.NameExistException;
import com.jack.pojo.entity.Department;
import com.jack.service.DepartmentService;
import com.jack.util.PageQuery;
import com.jack.util.TmResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@RequestMapping("/dept/")
@Controller
public class DepartmentController {

    public final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    /**
     * 添加部门
     * @param department
     * @return
     */
    @RequestMapping(value="add", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse addDepartment(Department department){
        String msg = validate(department);
        if ( msg != null){
            return TmResponse.fail(msg);
        }
        try{
            boolean res = departmentService.addDepartment(department);
            if(res){
                return TmResponse.success("新增部门成功");
            }else{
                return TmResponse.fail("新增部门失败");
            }
        }catch (NameExistException  e){
            return TmResponse.fail(e.getMessage());
        }
    }

    /**
     * 删除指定部门
     * @param deptId
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse deleteDepartment(Long deptId){
        if (deptId == null){
           return TmResponse.fail("请传入指定参数");
        }
        List<Department> departmentList = departmentService.findDepartmentByPid(deptId);
        if(departmentList.size() > 0){
            return TmResponse.fail("部门拥有子部门，无法删除");
        }
        if(departmentService.deleteDepartment(deptId)){
            return TmResponse.success("删除成功");
        }else{
            return TmResponse.fail("删除失败");
        }
    }

    /**
     * 根据id查询指定部门
     * @param deptId
     * @return
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse findOne(Long deptId){
        if (deptId == null){
           return TmResponse.fail("请传入指定参数");
        }
        Department department = departmentService.findDepartmentById(deptId);
        if (department == null){
           return TmResponse.fail("未查询到指定部门");
        }
        return TmResponse.success("查询成功",department);
    }
    /**
     * 更新部门信息
     * @param department
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse updateDepartment(Department department){
        String msg = validate(department);
        if(msg != null){
            return TmResponse.fail(msg);
        }
        if(department.getDeptId() == null){
            return TmResponse.fail("请传入指定参数");
        }
        try{
            boolean res = departmentService.updateDepartment(department);
            if (res){
                return TmResponse.success("更新成功");
            }else {
                return TmResponse.fail("更新失败");
            }
        }catch (NameExistException e){
            return TmResponse.fail(e.getMessage());
        }
    }

    /**
     * 所有部门列表
     * @param pageQuery
     * @return
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse findAllDepartment(PageQuery pageQuery){
        if(pageQuery == null){
            return TmResponse.fail("请传入指定参数");
        }
        PageInfo<DepartmentDTO> departmentDTOPageInfo = departmentService.findAllDepartment(pageQuery);
        return TmResponse.success("获取所有部门列表",departmentDTOPageInfo);
    }

    /**
     * 条件查询
     * @param pageQuery  当前页数  页面记录数
     * @param department 部门名称  部门管理员
     * @return
     */
    @RequestMapping(value = "findPart", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse findDepartmentByCondition(PageQuery pageQuery, Department department){
        if(pageQuery == null){
            return TmResponse.fail("请传入指定参数");
        }
        PageInfo<DepartmentDTO> departmentDTOPageInfo= departmentService.findDepartmentByCondition(pageQuery, department);
        return TmResponse.success("获取部门列表",departmentDTOPageInfo);
    }

    /**
     * 分级查询所有部门
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse findForSelect(){
        List<DepartmentDTO> departmentDTOList = departmentService.findDepartmentForSelect();
        return TmResponse.success("获取部门集合",departmentDTOList);
    }







    /*-----------------------------------私有方法-------------------------------------------------------*/
    private String validate(Department department){
        if (department == null){
            return "需要传递指定参数";
        }
        if (StringUtils.isBlank(department.getDeptName())){
            return "部门名称不能为空";
        }
        if (StringUtils.isBlank(department.getDeptManager())){
            return "部门管理员不能为空";
        }
        return null;
    }
}
