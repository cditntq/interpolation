package org.zsl.testmybatis;

import javax.annotation.Resource;

import com.ntq.baseMgr.po.UserInfo;
import com.ntq.baseMgr.service.impl.CompanyPositionInfoServiceImpl;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import com.ntq.baseMgr.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	// private ApplicationContext ac = null;
	@Resource
	private IUserService userService = null;

	@Autowired
	private CompanyPositionInfoServiceImpl companyPositionInfoService;

	// @Before
	// public void before() {
	// ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// userService = (IUserService) ac.getBean("userService");
	// }

	@Test
	public void test1() {
		UserInfo user = userService.getUserById(1);
		// System.out.println(user.getUserName());
		// logger.info("值："+user.getUserName());
		logger.info(JSON.toJSONString(user));
	}

	public void updateCompanyInfo(){
		try {
//            companyPositionInfoService.getTest();
			companyPositionInfoService.withDrawCompanyPositionInfo(1l,"下架");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}