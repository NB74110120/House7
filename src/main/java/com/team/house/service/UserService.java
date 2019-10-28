package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.util.PageUtil;
import com.team.house.util.UserCondition;


public interface UserService {

    //带条件搜索用户 分页
    PageInfo<Users> getUserByCondition(UserCondition condition);

    /**
     * 注册用户
     */
    int addUser(Users users);

    /**
     * 检查用户名是否存在
     */
    int checkUserName(String name);

    /**
     * 实现用户登入
     */
    Users  login(String name,String password);



}
