package com.tarena.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.tarena.entity.Result;
import com.tarena.entity.User;

/**
 *	记录异常日志
 */
@Component
@Aspect
public class ExceptionLogger {
	
	@Resource
	private HttpServletRequest request;

	@Around("within(com.tarena.web..*)")
	public Object log(ProceedingJoinPoint point) throws Exception {
		Object obj = null;
		try {
			obj = point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			// 记录错误日志
			Logger logger = Logger.getLogger(this.getClass());
			
			User user = (User) request.getSession().getAttribute("user");
			if(user != null) {
				String className = point.getTarget().getClass().getName();
				String method = point.getSignature().getName();
				String date = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss").format(new Date());
				
				StringBuffer sb = new StringBuffer();
				sb.append("用户[").append(user.getCn_user_name()).append("], ");
				sb.append("IP[").append(request.getRemoteHost()).append("], ");
				sb.append("在[").append(date).append("], 执行[");
				sb.append(className).append(".").append(method);
				sb.append("]时，发生如下异常：");
				logger.error(sb.toString());
			}
			
			StackTraceElement[] elems = e.getStackTrace();
			for(StackTraceElement elem : elems) {
				logger.error("\t" + elem.toString());
			}
			
			// 返回错误信息
			Result result = new Result();
			result.setStatus(1);
			if (e instanceof BusinessException) {
				// 对于业务异常，返回异常信息
				result.setMessage(e.getMessage());
			} else {
				// 对于其他不明确异常，返回统一的错误提示
				result.setMessage("系统出错，请联系管理员.");
			}
			return result;
		}
		return obj;
	}

}
