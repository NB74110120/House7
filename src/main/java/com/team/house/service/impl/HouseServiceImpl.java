package com.team.house.service.impl;

import com.team.house.entity.House;
import com.team.house.mapper.HouseMapper;
import com.team.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HouseServiceImpl implements HouseService {
 @Autowired
 private HouseMapper houseMapper;
 @Override
 public int addHouse(House house) {

  return houseMapper.insertSelective(house);
 }


 }



