package com.xuexi.v2.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice("com.xuexi")
public class ExceptionHandler {

	/**
	 * 全局异常处理
	 * 
	 * @param response
	 * @param e
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public void globalHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		try {
			StringWriter out = new StringWriter();
			e.printStackTrace(new PrintWriter(out));
			request.setAttribute("errorMessage", out.toString());
			request.getRequestDispatcher("/error").forward(request, response);
		} catch (ServletException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
