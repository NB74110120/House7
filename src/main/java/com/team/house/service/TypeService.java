package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.util.PageUtil;

import java.util.List;

public interface TypeService {
    //一功能一方法
    List<Type> getAllType();

    //查询所有区域支持分页
    public PageInfo<Type> getDisrictByPage(PageUtil pageInfo);

    /**
     * 添加区域
     * @param Type
     * @return 影响行数
     */
    public int addType(Type Type);

    /**
     * 能过编号获取区域单条记录
     * @param id 编号
     * @return 区域对象
     */
    public Type getType(Integer id);

    /**
     * 修改区域信息
     * @param Type   区域信息的实体
     * @return  影响行数
     */
    public int updateType(Type Type);

    /**
     * 删除区域信息
     * @param id 区域编号
     * @return影响行数
     */
    public int delType(Integer id);

    /**
     * 批量删除区域
     */
    public int delMoreType(Integer[] ids);


}
