# 在线订餐及管理系统
基于SSH,MySQL数据库及WebSocket的在线订餐及管理系统，做得稍微有点烂，但交个课程设计什么的应该没什么问题，购物车模块功能还不是太过完善，有意者可以自己添加。
# 关于配置
需在tomcat的server.xml配置  <Host> 标签里添加
	
    <Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">

        <!-- SingleSignOn valve, share authentication between web applications
             Documentation at: /docs/config/valve.html -->
        <!--
        <Valve className="org.apache.catalina.authenticator.SingleSignOn" />
        -->

        <!-- Access log processes all example.
             Documentation at: /docs/config/valve.html
             Note: The pattern used is equivalent to using pattern="common" -->
        <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_access_log." suffix=".txt"
               pattern="%h %l %u %t &quot;%r&quot; %s %b" />
	<Context path="/ord_upload" docBase="C:\\upload\\ord_sys"/>
      </Host>
且需要修改db.properties里的数据库配置修改成自己电脑上的配置：

	  jdbc.user=root   
	  jdbc.password=mysql
	  jdbc.driver=com.mysql.jdbc.Driver
	  jdbc.url=jdbc:mysql://localhost:3306/order_demo 

	  jdbc.minPoolSize=5
	  jdbc.maxPoolSize=10


