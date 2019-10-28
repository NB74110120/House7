package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.team.house.entity.Type;
import com.team.house.entity.TypeExample;
import com.team.house.mapper.TypeMapper;
import com.team.house.service.TypeService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@Service
@Transactional
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public List<Type> getAllType() {
        return typeMapper.selectByExample(new TypeExample());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)   //不基于事务执行
    public PageInfo<Type> getDisrictByPage(PageUtil pageInfo) {
        //1.开启分页
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getRows());
        //2.查询所有
        TypeExample TypeExample=new TypeExample();
        List<Type>  list=typeMapper.selectByExample(TypeExample);
        //3.获取分页信息
        PageInfo<Type> pageInfo1=new PageInfo(list);
        return pageInfo1;
    }

    @Override
    public int addType(Type Type) {
        return typeMapper.insertSelective(Type);
    }

    @Override
    public Type getType(Integer id) {
        //调用dao层
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateType(Type Type) {
        return typeMapper.updateByPrimaryKeySelective(Type);
    }

    //删除区域  删除街道
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int delType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delMoreType(Integer[] ids) {
        return typeMapper.deleteMoreType(ids);
    }


}
