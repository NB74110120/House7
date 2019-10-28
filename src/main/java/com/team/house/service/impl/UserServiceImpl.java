package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UserService;
import com.team.house.util.MD5Utils;
import com.team.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public PageInfo<Users> getUserByCondition(UserCondition condition) {
        //1.开启分页
        PageHelper.startPage(condition.getPage(),condition.getRows());
        List<Users> list = usersMapper.selectLikePage(condition);
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int addUser(Users users) {
        //使用md5工具类对密码加密码
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        //设置isadmin默认为0
        users.setIsadmin(0);
        return usersMapper.insertSelective(users);
    }

    @Override
    //检查用户名是否重复
    public int checkUserName(String name) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria= usersExample.createCriteria();
        //用户名条件
        criteria.andNameEqualTo(name);
        //执行
        List<Users> list=usersMapper.selectByExample(usersExample);
        return list.size();
    }

    @Override
    public Users login(String name, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria= usersExample.createCriteria();
        //用户名和密码条件
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        //执行
        List<Users> list=usersMapper.selectByExample(usersExample);
        if(list.size()==0)
            return null;
        else
        return list.get(0);
    }


}
