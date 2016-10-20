
<%@page import="org.vetech.core.modules.utils.WebUtils"%>
<%@page import="org.vetech.core.modules.utils.VeDate"%>

 <head>  

 <title>Threads in ldcstudy.com</title>  

 <style>  

 body {font-size:8pt;}  

 ol {line-height:18px;}  

 </style>  

 </head>  

 <body>  
 <strong>date time:</strong>  

 <ul>  

 <li><%=VeDate.getStringDate()%></li>  

 </ul>  

 <br/>  

 <strong>java.io.tmpdir:</strong>  

 <ul>  

 <li><%=System.getProperty("java.io.tmpdir")%></li>  

 </ul>  

 <br/>  
 
  <strong>IP:</strong>  

 <ul>  

 <li>x-forwarded-for:=<%=request.getHeader("x-forwarded-for")%></li>  
 <li>WL-Proxy-Client-IP:=<%=request.getHeader("WL-Proxy-Client-IP")%></li>  
 <li>getRemoteAddr:=<%=request.getRemoteAddr()%></li>  
<li>WebUtils.getRemoteIp:=<%=WebUtils.getRemoteIp(request)%></li>  
 </ul>  

 <br/> 

 <strong>Memory:</strong>  

 <ol>  

 <li>freeMemory=<%=Runtime.getRuntime().freeMemory()/(1024*1024)%>M</li>  

     <li>totalMemory=<%=Runtime.getRuntime().totalMemory()/(1024*1024)%>M</li>  

     <li>maxMemory=<%=Runtime.getRuntime().maxMemory()/(1024*1024)%>M</li>  

 </ol>  

 <br/>  

 <strong>Thread:</strong>  

 <ol>  
<% String color=""; %>
 <%for(Thread t : list_threads()){ 
    if("RUNNABLE".equals(t.getState().name())){
	 color="red";
    }else{
    	color="";
    } %>
 <li><%=t.getName()%>(<b style='color:<%=color%>'><%=t.getState()%></b>) : <%=t.getClass().getName()%>

 <span style='color:<%=color%>'><%=getStackMsg(t) %></span>
 </li>  

 <%}%>  

 </ol>  

 <%!  

 public static java.util.List<Thread> list_threads(){  

     int tc = Thread.activeCount();  

     Thread[] ts = new Thread[tc];  

     Thread.enumerate(ts);  

     return java.util.Arrays.asList(ts);  

 }  
 
 private static String getStackMsg(Thread t) { 
	 StringBuffer sb = new StringBuffer(); 
	 StackTraceElement[] stackArray = t.getStackTrace(); 
	 for (int i = 0; i < stackArray.length; i++) { 
	 	StackTraceElement element = stackArray[i]; 
	 	sb.append(element.toString() + "\n"); 
	 } 
	 return sb.toString(); 
	 } 

 %>  

 </body>  

 </html> 
