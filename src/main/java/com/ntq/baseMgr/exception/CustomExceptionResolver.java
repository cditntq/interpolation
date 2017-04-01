package com.ntq.baseMgr.exception;


import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: CustomExceptionResolver
 * @Description: 自定义异常处理器，所有的异常处理器都需要实现HandlerExceptionResolver
 * @Company:
 * @Author 杨爽【247677857yh@gmail.com】
 * @Date: 2016/3/31
 * @Time: 10:04
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {


    /**
     * @param request
     * @param response
     * @param handler  最终要执行的handler，最终身份为HandlerMethod
     * @param ex       接收到的异常信息
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        //输出异常信息
        ex.printStackTrace();
        //统一异常处理代码
        //针对系统自定义的CustomException异常,就可以直接从异常类中获取异常信息,将异常处理在错误页面展示
        //异常信息
        String message = null;
        CustomException customException = null;
        //如果ex使系统自定义的异常,直接取出异常信息
        if (ex instanceof CustomException) {
            customException = (CustomException) ex;

        } else {
            //针对非CustomException异常,对这类宠幸构造一个CustomException，异常信息为"未知错误"
            customException = new CustomException("未知错误");

        }
        //错误信息
        message = customException.getMessage();
        request.setAttribute("message", message);

        try {
            //转向到错误页面
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
