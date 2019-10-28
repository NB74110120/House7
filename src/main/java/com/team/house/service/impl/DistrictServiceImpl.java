package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistrictService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }

   @Override
    public PageInfo<District> getDisrictBypage(PageUtil pageInfo) {
     //1开启分页
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getRows());
    //查询所有
      DistrictExample districtExample=new DistrictExample();
        List<District> list = districtMapper.selectByExample(new DistrictExample());
        //3获取分页
        PageInfo<District> pageInfo1=new PageInfo(list);
        return pageInfo1;
    }

 @Override
 public int addDistrict(District district) {

  return districtMapper.insertSelective(district);
 }

/*
* 进行修改
* */
 @Override
 public int updateDistrict(District district) {
  return districtMapper.updateByPrimaryKeySelective(district );
 }

 @Override
 @Transactional
 public int delDistrict(Integer id) {
  int i = districtMapper.deleteByPrimaryKey(id);
  districtMapper.deleteStreetByDistrict(id);
  return i;
 }

 @Override
 public District selectById(Integer id) {
  return districtMapper.selectByPrimaryKey(id);
 }
 /*
  * 进行删除
  * */
 @Override
 public int delMoreDistrict(List ids) {

  return districtMapper.deleteMoreBatchByParams(ids);
 }







}
