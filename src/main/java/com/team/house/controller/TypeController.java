package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import com.team.house.util.PageUtil;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("getAllType")
    @ResponseBody
    public List<Type> getAllType(){
        return typeService.getAllType();
    }


    //由于前端使用的datagrid 所以他自动传参 page 页码,rows页大小
    @RequestMapping("getTypeByPage")
    @ResponseBody
    public Map<String,Object> getTypeByPage(PageUtil pageUtil){
        //调用业务
        PageInfo<Type> pageInfo=typeService.getDisrictByPage(pageUtil);

        //使用map封装返回的数据
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }


    //由于前端使用的datagrid 所以他自动传参 page 页码,rows页大小
    @RequestMapping("addType")
    @ResponseBody   //{"result":0}
    public Map<String,Object> addType(Type Type){
        //调用业务
        int flag=typeService.addType(Type);
        //使用map封装返回的数据
       // return "{\"result\":"+flag+"}";  //手工拼的json
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("result",flag);  //自动将对象转化为json
        return map;
    }

    //接由前端传递的区域编号，查询当前记录
    @RequestMapping("getType")
    @ResponseBody   //{"result":0}
    public Type getType(Integer id){
        //调用业务
       Type Type =typeService.getType(id);
        //使用map封装返回的数据
       return Type;
    }

    //修改区域
    @RequestMapping("upType")
    @ResponseBody   //{"result":0}
    public Map<String,Object> upType(Type Type){
        //调用业务
        int flag=typeService.updateType(Type);
        //使用map封装返回的数据
        // return "{\"result\":"+flag+"}";  //手工拼的json
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("result",flag);  //自动将对象转化为json
        return map;
    }


    //删除区域
    @RequestMapping("delType")
    @ResponseBody   //{"result":0}
    public Map<String,Object> delType(Integer id){
        //调用业务
        int flag=typeService.delType(id);
        //使用map封装返回的数据
        // return "{\"result\":"+flag+"}";  //手工拼的json
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("result",flag);  //自动将对象转化为json
        return map;
    }



    //批量删除区域  delMoreDistrit?ids=1006&ids=1007  ====>Integer []ids
   // delMoreDistrit?ids=1006,1007  ====>String ids
    @RequestMapping("delMoreType")
    @ResponseBody   //{"result":0}
    public Map<String,Object> delMoreType(String ids){

        //将字符串转化为数组  {1006,1007}
        String [] list=ids.split(",");
        Integer [] ary=new Integer[list.length];
        for (int i = 0; i <list.length ; i++) {
          ary[i]=Integer.parseInt(list[i]);
        }
        //调用业务
         int flag=typeService.delMoreType(ary);

        //使用map封装返回的数据
        // return "{\"result\":"+flag+"}";  //手工拼的json
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("result",flag);  //自动将对象转化为json
        return map;
    }
}
