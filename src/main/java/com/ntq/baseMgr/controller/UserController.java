package com.ntq.baseMgr.controller;

import com.ntq.baseMgr.po.UserInfo;
import com.ntq.baseMgr.service.IUploadFileService;
import com.ntq.baseMgr.service.IUserService;
import com.ntq.baseMgr.vo.UploadFileVo;
import com.ntq.baseMgr.po.JobSeekerInfos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    @Resource
    private IUploadFileService uploadDemoService;

    @RequestMapping("/showUser")
    public String toIndex(HttpServletRequest request, Model model) {
        int userId = Integer.parseInt(request.getParameter("id"));
        UserInfo user = this.userService.getUserById(userId);
        model.addAttribute("user", user);
        return "showUser";
    }


    /**
     * 注入用户信息以及简历上传
     *
     * @param request
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadJobSeekerInfoAndResume", method = RequestMethod.POST)
    public String uploadFile(JobSeekerInfos jobSeekerInfos, UploadFileVo vo, HttpServletRequest request) throws Exception {
        uploadDemoService.uploadForm(vo);
        String userId = request.getParameter("fileName");
//		UserInfo user = this.userService.getUserById(userId);
        return "sentResum";
    }

/*	*//**
     * 附件上传
     * @param request
     * @param vo
     * @return
     * @throws Exception
     *//*
    @RequestMapping(value = "/uploadFile",method= RequestMethod.POST)
	public String uploadFile(HttpServletRequest request, UploadFileVo vo) throws Exception {
		uploadDemoService.uploadForm(vo);
		String userId = request.getParameter("fileName");
//		UserInfo user = this.userService.getUserById(userId);
		return "sentResum";
	}*/

}