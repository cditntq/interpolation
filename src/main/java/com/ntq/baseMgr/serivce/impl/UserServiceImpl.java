package com.ntq.baseMgr.serivce.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ntq.baseMgr.dao.UserInfoMapper;
import com.ntq.baseMgr.entity.UserInfo;
import com.ntq.baseMgr.service.IUserService;
@Service("userService") 
public class UserServiceImpl implements IUserService {
	@Resource  
	private UserInfoMapper userInfoMapper;  
	
	public UserInfo getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userInfoMapper.selectByPrimaryKey(userId); 
		
	}

}
