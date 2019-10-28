package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller(value = "districtController1")
@RequestMapping("/admin")
public class DistrictController {
 @Autowired
 private DistrictService districtService;

 @RequestMapping("/getAllDistrict")
 public @ResponseBody List<District> getAllDistrict(){
  return districtService.getAllDistrict();
 }
 @RequestMapping("/getDistrict")
 public @ResponseBody District getDistrict(Integer id){
  return districtService.selectById(id);
 }

 @RequestMapping("/getDistrictBypage")
 public @ResponseBody Map getDistrictBypage (PageUtil pageUtil){

  PageInfo<District> info = districtService.getDisrictBypage(pageUtil);
    Map map=new HashMap();
      map.put("rows",info.getList());
      map.put("total",info.getTotal());
    return map;
 }
//添加
 @RequestMapping("/addDisrict")
 public @ResponseBody boolean addDisrict(District district){
  System.out.println(district);
//调用业务
  int flag = districtService.addDistrict(district);
  if(flag>0) return true;
  return false;
 }
//修改
//修改区域
@RequestMapping("/upDistrit")
@ResponseBody   //{"result":0}
public Map<String,Object> upDistrit(District district){
 //调用业务
 int flag=districtService.updateDistrict(district);
 //使用map封装返回的数据
 // return "{\"result\":"+flag+"}";  //手工拼的json
 Map<String,Object> map=new HashMap<String, Object>();
 System.out.println("llll");
 map.put("result",flag);  //自动将对象转化为json
 return map;
}




 //删除
 //删除区域
 @RequestMapping("/delDistrit")
 @ResponseBody   //{"result":0}
 public Map<String,Object> delDistrit(Integer id){

  System.out.println("into");
  //调用业务
  int flag=districtService.delDistrict(id);
  //使用map封装返回的数据
  // return "{\"result\":"+flag+"}";  //手工拼的json
   Map<String,Object> map=new HashMap<String, Object>();

  map.put("result",flag);  //自动将对象转化为json
  return map;
 }
/*
* 批量删除
* */
 @RequestMapping("delMoreDistrit")
 @ResponseBody   //{"result":0}
 public  int  delMoreDistrit(Integer[] ids){
  List list=new ArrayList();
  for (int i = 0; i <ids.length ; i++) {
   list.add(ids[i]);
  }
  int i = districtService.delMoreDistrict(list);
  return i;
 }
}
