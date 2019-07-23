package config.demo;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletConfig extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取到配置文件对象，该对象专门用在servlet的配置文件的操作
		javax.servlet.ServletConfig config = getServletConfig();
		
		//获取到的是<servlet-name>HelloServlet</servlet-name>里的文本内容
		
		String str1=config.getServletName();
		
		
		System.out.println("srt1........"+str1);
		
//		String string=config1.getInitParameter("HelloServlet");
//		
//		System.out.println("string....... "+string);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	
	
	
	

}
