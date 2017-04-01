package com.ntq.baseMgr.controller.support;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * 切点类
 */
@Aspect
@Component
public class SystemLogAspect {
    //本地异常日志纪录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
  /*  @Resource
    private LogService logService;*/

    /*定义controller切入点*/
    @Pointcut("@annotation(com.ntq.baseMgr.controller.support.SystemControllerLog)")
    public void controllerAspect() {

    }

    /*定义service切入点*/
    @Pointcut("@annotation(com.ntq.baseMgr.controller.support.SystemServiceLog)")
    public void serviceAspect() {
    }

    /**
     * 前置通知,用于拦截Controller层纪录用户的操作
     *
     * @param joinPoint
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        //接收到请求,记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String beanName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();//获取方法名
        String uri = request.getRequestURI();//请求路径
        String method = request.getMethod();
        HttpSession session = request.getSession();
        //请求ip
        String ip = request.getRemoteAddr();
        //从请求中获取参数列表

        Object[] args = joinPoint.getArgs();
  /*
        Set<String> parameterNames  = (Set<String>) joinPoint.getArgs()[0];
        //Set<String> parameterNames = (Set<String>) request.getParameterNames();
        Map<String, String> requestParams = new HashMap<>();
        for (String parameterName : parameterNames) {
            String value = request.getParameter(parameterName);
            requestParams.put(parameterName, value);
        }*/
        try {
            //*========控制台输出=========*//
            System.out.println("=====前置通知开始=====");
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));
            //*========数据库日志=========*//
            System.out.println("=====前置通知结束=====");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常通知 用于拦截service层纪录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThroing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        // User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
        //获取请求ip
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
   /*     if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params += JSONUtil.toJsonString(joinPoint.getArgs()[i]) + ";";
            }
        }*/
        try {
              /*========控制台输出=========*/
            System.out.println("=====异常通知开始=====");
            System.out.println("异常代码:" + e.getClass().getName());
            System.out.println("异常信息:" + e.getMessage());
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));
          //  System.out.println("请求人:" + user.getName());
            System.out.println("请求IP:" + ip);
            System.out.println("请求参数:" + params);
               /*==========数据库日志=========*/
//            Log log = new Log();
/*            log.setDescription(getServiceMthodDescription(joinPoint));
            log.setExceptionCode(e.getClass().getName());
            log.setType("1");
            log.setExceptionDetail(e.getMessage());
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.setParams(params);
            log.setCreateBy(user);
            log.setCreateDate(DateUtil.getCurrentDate());*/
//            log.setRequestIp(ip);
            //保存数据库
//            logService.insert(log);
            System.out.println("=====异常通知结束=====");
        } catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
         /*==========记录本地异常日志==========*/
      //  logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);

    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
}
