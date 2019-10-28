package com.team.house.pcontroller;

import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller(value="typeController2")
public class TypeController {
 @Autowired
 private TypeService typeService;
@RequestMapping("getAllType")
 public List<Type> getAllType(){
return this.typeService.getAllType();
}
}
