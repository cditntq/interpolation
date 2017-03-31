package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.entity.UserInfo;
import com.ntq.baseMgr.dao.UserInfoMapper;
import com.ntq.baseMgr.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource  
	private UserInfoMapper userInfoMapper;  
	
	public UserInfo getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userInfoMapper.selectByPrimaryKey(userId); 
		
	}

}
