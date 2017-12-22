package com.order.servlet;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class OnLoadUpServlet extends HttpServlet {

	
	public OnLoadUpServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}


	
	public void init() throws ServletException {
		File file;
		//初始化图片目录
		String dataPath = getServletContext().getRealPath("/imgSrc");
		file = new File(dataPath);
		if(!file.exists()){
			file.mkdirs();
		}
	}

}
