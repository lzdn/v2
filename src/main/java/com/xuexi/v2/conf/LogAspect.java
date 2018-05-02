package com.xuexi.v2.conf;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Order(-5)
public class LogAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	/**
	 * 定义一个切入点. 解释下：
	 *
	 * ~ 第一个 * 代表任意修饰符及任意返回值. ~ 第二个 * 任意包名 ~ 第三个 * 代表任意方法. ~ 第四个 * 定义在web包或者子包 ~ 第五个
	 * * 任意方法 ~ .. 匹配任意数量的参数.
	 * 
	 */
	@Pointcut("execution(public * com.xuexi.service..*.*(..))")
	//@Pointcut("execution(public * com.xiangqin.service..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		// 接收到请求，记录请求内容
		logger.info("LogAspect.doBefore()");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
		logger.info("打印所有参数：");
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			logger.info(paraName + ": " + request.getParameter(paraName));
		}
	}

	@Around("webLog()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		final String methodName = proceedingJoinPoint.getSignature().getName();

		Long startTime = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		Long finishTime = System.currentTimeMillis();

		Signature signature = proceedingJoinPoint.getSignature();
		String[] packageName = signature.getDeclaringTypeName().split("\\.");
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < packageName.length; ++i) {
			if (i < packageName.length - 1) {
				stringBuilder.append(packageName[i].substring(0, 1));
			} else {
				stringBuilder.append(packageName[i]);
			}
			stringBuilder.append(".");
		}

		logger.info("Executing: " + stringBuilder + signature.getName() + " took: " + (finishTime - startTime) + " ms");

		Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();

		if (method.getDeclaringClass().isInterface()) {
			method = proceedingJoinPoint.getTarget().getClass().getDeclaredMethod(methodName,
					method.getParameterTypes());
		}

		// 方法上的注解优先级比类上的注解高,可以覆盖类上注解的值
		CalcTimed timed = null;
		if (method.isAnnotationPresent(CalcTimed.class)) {
			// 处理方法上的注解
			timed = method.getAnnotation(CalcTimed.class);
			if (timed.displayArgs()) {
				logArgs(proceedingJoinPoint.getArgs());
			}
		} else {
			// 处理类上面的注解
			Object target = proceedingJoinPoint.getTarget();
			if (target.getClass().isAnnotationPresent(CalcTimed.class)) {
				timed = target.getClass().getAnnotation(CalcTimed.class);
				if (timed.displayArgs()) {
					logArgs(proceedingJoinPoint.getArgs());
				}
			}
		}

		return result;
	}

	/*@AfterReturning("webLog()")
	public void doAfterReturning(JoinPoint joinPoint) {
		// 处理完请求，返回内容
		logger.info("LogAspect.doAfterReturning()");
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
	}*/

	private void logArgs(Object[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < args.length; ++i) {
			stringBuilder.append("[");
			stringBuilder.append(i);
			stringBuilder.append("]: ");
			stringBuilder.append(args[i].toString());
			if (i < args.length - 1) {
				stringBuilder.append(", ");
			}
		}
		if (!stringBuilder.toString().isEmpty())
			logger.info("Argument List: " + stringBuilder);
		else
			logger.info("Argument List: Empty");
	}
}
