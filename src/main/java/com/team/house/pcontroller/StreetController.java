package com.team.house.pcontroller;

import com.team.house.entity.Street;
import com.team.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "streetController2")
@RequestMapping("/admin/")
public class StreetController {
 @Autowired
 private StreetService streetService;
 //通过区域编号查询街道
 @RequestMapping("getStreetByDid")
 @ResponseBody
 public List<Street> getStreetByDid(Integer did){

  return streetService.getStreetByDistrictId(did);
 }


}
