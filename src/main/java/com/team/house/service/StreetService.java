package com.team.house.service;

import com.team.house.entity.Street;

import java.util.List;

public interface StreetService {
//一功能一方法
 //通过区域查询对应的街道
 List<Street> getStreetByDistrictId(Integer disstrictId);
}
