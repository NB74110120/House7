package com.team.house.pcontroller;

import com.team.house.entity.House;
import com.team.house.entity.Users;
import com.team.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller(value = "houseController2")
@RequestMapping("admin2")
public class HouseController2 {
 @Autowired
 private HouseService houseService;

 @RequestMapping("addHouse")
 public String addHouse(House house, HttpSession session, @RequestParam(value = "pfile", required = false) MultipartFile pfile) {
  System.out.println("into");
  try {
   //1.上传文件
   String sourceFile = pfile.getOriginalFilename();  //文件名
   String extName = sourceFile.substring(sourceFile.lastIndexOf("."));//扩展名
   String bh = System.currentTimeMillis() + "";
   String filename = bh + extName;
   String path = "d:\\images\\" + filename;
   File saveFile = new File(path);
   if(!saveFile.exists()) saveFile.mkdirs();
   pfile.transferTo(saveFile);   //上传

   //2.调用业务将数据保存到数据库
   //设置编号
   house.setId(bh);
   //设置图片
   house.setPath(filename);
   //设置用户编号
   Users user = (Users) session.getAttribute("userinfo");
   house.setUserId(user.getId());
   int i = houseService.addHouse(house);//保存
   if(i>0){
    System.out.println("收到");
    return "guanli";

   }else {
    return "cuowu";
   }

  } catch (IOException e) {
   e.printStackTrace();
  }
  return "error";
 }
}