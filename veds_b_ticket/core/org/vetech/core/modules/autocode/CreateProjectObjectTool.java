package org.vetech.core.modules.autocode;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.utils.VeDate;

public class CreateProjectObjectTool {
	public static void main(String[] arg){
		Connection conn = getConnect();
		String currentPath = "";
		File directory = new File("");//设定为当前文件夹 
		currentPath = directory.getAbsolutePath()+"/src";//获取绝对路径  E:\JAVA\MYSPRINGSIDE
		//包路径
		String packagepath = "cn.vetech.vecode";
		String apppackage = "fb";
		String stable = "fb_table_log";
		StringBuffer st = new StringBuffer();
		stable = stable.toLowerCase();
		//生成版权信息
		st = createCopyright(StringUtils.capitalize(stable)); 
		//加入PACKAGE
		st.append("package ").append(packagepath).append(".entity.").append(apppackage).append(";\r\n\r\n");
		
		st.append("import java.io.Serializable;\r\n");
		st.append("import javax.persistence.GeneratedValue;\r\n");
		st.append("import javax.persistence.Table;\r\n");
		st.append("import javax.persistence.Id;\r\n");
//		st.append("import javax.persistence.Transient;\r\n\r\n");
		
		st.append("import org.hibernate.validator.constraints.NotBlank;\r\n");
		st.append("import org.vetech.core.modules.entity.AbstractEntity;\r\n\r\n");
//		st.append("import org.vetech.core.modules.mybatis.entity.BusinessId;\r\n\r\n");
//		st.append("import org.vetech.core.modules.utils.VeDate;\r\n\r\n");
		
		st.append("@Table(name = \"" + stable.toUpperCase()+"\")\r\n");
		
		
		
		st.append("public class ").append(StringUtils.capitalize(stable).replaceAll("_", "")).append(" extends   AbstractEntity implements Serializable {\r\n\r\n");
		
		st.append("    @Id\r\n");
		st.append("    @GeneratedValue(generator=\"uuid\")\r\n");
		
		//中间是对应的字段清单
		st.append(createBeanEntity(conn,stable));
		
		st.append("\r\n\r\n}");
		
		stable = stable.replaceAll("_", "");
		
		 try{
			  writeFile(currentPath+"/" +StringUtils.replace(packagepath, ".", "/")+"/entity/"+apppackage,StringUtils.capitalize(stable)+".java", 
				st.toString());
		 }catch(Exception es){
			 es.printStackTrace();
		 }
		//生成DAO对象
		st = new StringBuffer();		
		//生成版权信息
		st = createCopyright(StringUtils.capitalize(stable) +"Dao"); 
		//加入PACKAGE
		st.append("package ").append(packagepath).append(".dao.").append(apppackage).append(";\r\n\r\n");
		st = createDao(st,stable,packagepath,apppackage);
		writeFile(currentPath+"/" +StringUtils.replace(packagepath, ".", "/")+"/dao/"+ apppackage,
				StringUtils.capitalize(stable)+"Dao.java", 
				st.toString());
		
		//生成service
		st = new StringBuffer();
		//生成版权信息
		st =  createCopyright(StringUtils.capitalize(stable)+"ServiceImpl"); 
		//加入PACKAGE
		st.append("package ").append(packagepath).append(".service.").append(apppackage).append(";\r\n\r\n");
		st = createService(st,stable,packagepath,apppackage);
		writeFile(currentPath+"/" +StringUtils.replace(packagepath, ".", "/")+"/service/" + apppackage, 
				StringUtils.capitalize(stable)+"ServiceImpl.java", 
				st.toString());
//		
		//生成controller
		st = new StringBuffer();
		//生成版权信息
		st = createCopyright(StringUtils.capitalize(stable)+"Controller"); 
		//加入PACKAGE
		st.append("package ").append(packagepath).append(".web.").append(apppackage).append(";\r\n\r\n");
		st = createAction(st,stable,packagepath,apppackage);
		writeFile(currentPath+"/" +StringUtils.replace(packagepath, ".", "/")+"/web/" + apppackage, 
				StringUtils.capitalize(stable)+"Controller.java", 
				st.toString());
	}
	
	
	public static StringBuffer createDao(StringBuffer st,String table,String s_package,String apppackage){
		st.append("import org.vetech.core.modules.mybatis.mapper.Mapper;\r\n\r\n");
		
		
		st.append("import ").append(s_package).append(".entity.").append(apppackage).append(".").
		append(StringUtils.capitalize(table.toLowerCase())).append(";\r\n\r\n");
		
		st.append("public interface ").
		append(StringUtils.capitalize(table.toLowerCase())).append("Dao extends Mapper<").
		append(StringUtils.capitalize(table.toLowerCase())).append(">{\r\n");
		  
		st.append("}");
		return st;
	}
	
	
	public static StringBuffer createService(StringBuffer st,String table,String s_package,String apppackage){
		st.append("import org.springframework.stereotype.Service;\r\n\r\n");
		st.append("import org.vetech.core.modules.service.MBaseService;\r\n\r\n");
		
		st.append("import ").append(s_package).append(".entity.").append(apppackage).append(".").
		append(StringUtils.capitalize(table.toLowerCase())).append(";\r\n");
		st.append("import ").append(s_package).append(".dao.").append(apppackage).append(".").
		append(StringUtils.capitalize(table.toLowerCase())).append("Dao;\r\n\r\n\r\n");
		
		st.append("@Service\r\n");
		st.append("public class ").
		append(StringUtils.capitalize(table.toLowerCase())).append("ServiceImpl extends MBaseService<").
		append(StringUtils.capitalize(table.toLowerCase())).append(",").
		append(StringUtils.capitalize(table.toLowerCase())).append("Dao>").
		append("{\r\n\r\n");
		 
		
		st.append("}");
		return st;
	}
	
	
	public static StringBuffer createAction(StringBuffer st,String table,String pack,String apppackage){
		 
		st.append("import org.springframework.stereotype.Controller;\r\n");
		st.append("import org.vetech.core.modules.web.MBaseControl;\r\n\r\n");
		
		st.append("import ").append(pack).append(".entity.").append(apppackage).append(".").
		append(StringUtils.capitalize(table.toLowerCase())).append(";\r\n");
		st.append("import ").append(pack).append(".service.").append(apppackage).append(".").
		append(StringUtils.capitalize(table.toLowerCase())).append("ServiceImpl;\r\n\r\n\r\n");
		
		st.append("@Controller\r\n");
		
		st.append("public class ").
			append(StringUtils.capitalize(table.toLowerCase())).append("Controller extends MBaseControl<").
			append(StringUtils.capitalize(table.toLowerCase())).append(",").
			append(StringUtils.capitalize(table.toLowerCase())).append("ServiceImpl").
			append(">{\r\n\r\n");
		 
		st.append("    @Override\r\n").
			append("    public void insertInitEntity(").append(StringUtils.capitalize(table.toLowerCase())).
			append("  t){\r\n").append("      \r\n").append("    }\r\n\r\n");
		st.append("}");
		return st;
	}
	
	public static StringBuffer createBeanEntity( Connection conn,String table){
		ResultSet rs=null;
	    Statement stmt = null;
	    StringBuffer sbuffer = new StringBuffer();
	    StringBuffer sf1 = new StringBuffer();
	    try {
		   stmt = conn.createStatement();
		   rs=stmt.executeQuery("select t.column_name CN,t.data_type DT,t.nullable NU,aa.comments CM from  USER_TAB_COLUMNS t ,"
		   		+ " user_col_comments aa where t.TABLE_NAME = aa.table_name and t.COLUMN_NAME=aa.column_name"
		   		+ " and t.table_name='"+table.toUpperCase()+"' order by t.column_id  " );//
		   while(rs.next()){
			   if("N".equals(rs.getString("NU"))){//是否可为空
				   sbuffer.append("\r\n    @NotBlank");  
			   }
			   String cname = rs.getString("CN");
			   sbuffer.append("\r\n	private ").append(getColumnType(rs.getString("DT"))).
	   			append(" ").append(cname.toLowerCase()).append(";");
//			   System.out.println(rs.getString("CM")+"==========");
			   if(StringUtils.isNotBlank(rs.getString("CM"))){
				   sbuffer.append("        //").append(rs.getString("CM"));
			   }
			   sf1.append("\r\n	public ").
			   		append(getColumnType(rs.getString("DT"))).
			   		append(" get").
			   		append(StringUtils.capitalize(cname.toLowerCase())).
			   		append("() {").
			   		append("\r\n		return ").
			   		append(cname.toLowerCase()).
			   		append(";").append("\r\n	}");
			   sf1.append("\r\n	public void set").append(StringUtils.capitalize(cname.toLowerCase())).append("(").
		  		append(getColumnType(rs.getString("DT"))).append(" ").
		  		append(cname.toLowerCase()).
		  		append(") {").
		  		append("\r\n		this.").
		  		append(cname.toLowerCase()).append(" = ").
		  		append(cname.toLowerCase()).append(";").
		  		append("\r\n	}");
			   
		   }
//		   ResultSetMetaData md=rs.getMetaData();   
//		   //下面也不要用while   (rs.next())，原因是记录集可能没有记录，如查询空表等情况。   
//		   for(int   i=1;i<=md.getColumnCount();i++){
//			   System.out.println(md.getColumnLabel(i)+"---");
//			   if(!"ID".equals(md.getColumnName(i).toUpperCase())){
//				   if(md.isNullable(i)==0){//是否可为空
//					   sbuffer.append("\r\n    @NotBlank");  
//				   }
//				   sbuffer.append("\r\n	private ").append(getColumnType(md.getColumnTypeName(i))).
//				   			append(" ").append(md.getColumnName(i).toLowerCase()).append(";");
//				   sf1.append("\r\n	public ").
//				   		append(getColumnType(md.getColumnTypeName(i))).
//				   		append(" get").
//				   		append(StringUtils.capitalize(md.getColumnName(i).toLowerCase())).
//				   		append("() {").
//				   		append("\r\n		return ").
//				   		append(md.getColumnName(i).toLowerCase()).
//				   		append(";").append("\r\n	}");
//				   sf1.append("\r\n	public void set").append(StringUtils.capitalize(md.getColumnName(i).toLowerCase())).append("(").
//			   		append(getColumnType(md.getColumnTypeName(i))).append(" ").
//			   		append(md.getColumnName(i).toLowerCase()).
//			   		append(") {").
//			   		append("\r\n		this.").
//			   		append(md.getColumnName(i).toLowerCase()).append(" = ").
//			   		append(md.getColumnName(i).toLowerCase()).append(";").
//			   		append("\r\n	}");
//			   }
//		   }
		   
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return sbuffer.append("\r\n\r\n").append(sf1);
	}
	
	/**
	 * ColumnType转换
	 * @param stype
	 * @return
	 */
	public static String getColumnType(String stype){
		String st  = "String"; //默认为String
		if("VARCHAR".equals(stype.toUpperCase()) || "CHAR".equals(stype.toUpperCase()))
			st =  "String";
		if("BIGINT".equals(stype.toUpperCase()))
			st="Long";
		if("INT".equals(stype.toUpperCase()))
			st="Integer";
		if("DECIMAL".equals(stype.toUpperCase()))
			st="Float";
		if("DATE".equals(stype.toUpperCase())){
			st="Date";
		}
		return st; //
	}

	
	/**
	 * 创建版权信息
	 * @param JavaName
	 * @return
	 */
	public static StringBuffer createCopyright(String JavaName){
		StringBuffer scopy= new StringBuffer();
		scopy.append("/**\r\n");
		scopy.append("*@Title:       ").append("\r\n");
		scopy.append("*@Description: \r\n");
		scopy.append("*@Author:      ").append("\r\n");
		scopy.append("*@Version:     1.1").append("\r\n");
		scopy.append("*@Date:        ").append(VeDate.getStringDateShort()).append("\r\n");
		scopy.append("*@Modify:      ").append("\r\n");
		scopy.append("*/").append("\r\n\r\n");
		return scopy;
	}
	
	/**
	 * 数据库连接
	 * @return
	 */
	public static  Connection getConnect(){
	   Connection con= null;
			String url = "jdbc:oracle:thin:@192.168.3.91:1521:star";
			String userName = "veds";   
			String password = "veds";
			try{
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
				con=DriverManager.getConnection(url, userName, password);   
			}catch(Exception es){
				System.out.println("ERROR: Can not get DB connect!!!" + es.getMessage());
				return null;
			}
		return con;
	}
	
	/**
	 * 写文件，可以传入编码
	 */
	public static void writeFile(String path, String filename, String fileContent) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(path + "/" + filename);
			PrintWriter pwrite = null;
			pwrite = new PrintWriter(file, "UTF-8");
			pwrite.println(fileContent);
			pwrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


