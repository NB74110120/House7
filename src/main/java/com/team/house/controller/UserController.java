package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.Users;
import com.team.house.service.TypeService;
import com.team.house.service.UserService;
import com.team.house.util.PageUtil;
import com.team.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
//@RestController=@Controller + @ResponseBody
@RequestMapping("/admin/")
public class UserController {

    @Autowired
    private UserService userService;

    //由于前端使用的datagrid 所以他自动传参 page 页码,rows页大小
    @RequestMapping("getUserByPage")
    public Map<String,Object> getUserByPage(UserCondition condition){

        System.out.println(condition);
        //调用业务
        PageInfo<Users> pageInfo=userService.getUserByCondition(condition);

        //使用map封装返回的 数据
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }


}
