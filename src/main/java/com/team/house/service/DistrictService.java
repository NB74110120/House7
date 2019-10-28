package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.util.PageUtil;

import java.util.List;

public interface DistrictService {
 //一功能一方法
 List<District> getAllDistrict();
 //查询所有支持分页
public PageInfo<District> getDisrictBypage(PageUtil pageInfo);

/*
* 添加区域
* */
public int addDistrict(District district);

/*
* 修改区域
*
* */
public  int updateDistrict(District district);
/*
* 删除区域信息
* */
public  int delDistrict(Integer id);

//根据id查询
 District selectById(Integer id);

// Integer deleteBatchByParams (List ids);
/*
* 批量删除
*
* */
 public  int  delMoreDistrict(List ids);

}
