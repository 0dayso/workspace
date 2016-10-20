<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>字体加载测试</title>
	</head>

	<body>
		<br>
		<br>
		<br>
		<br>
		<div align="center">
			<font style="font-size: 15pt; font-family: 宋体">
				如果下面的字体和红框中图片显示的字体一致，表示字体加载成功 <br>
			 </font>
			<br>
			<br>
			<table style="text-align: center;">
				<tr>
					<td>
						<font style="font-size: 20pt; font-family: TEC"> 赵龙 CN0.00
							2563 VOID PEK SHA <br> HK 0840 12OCT K 7605 HU VOID 上海虹桥</font>
					</td>
				</tr>
				<tr>
					<td>
						<img src="${ctx}/static/images/wdimages/fonttest.jpg">
					</td>
				</tr>
			</table><br><br>
			<font style="font-size: 14pt; font-family: 宋体">
			如果不一致,请 <a href="${ctx}/static/core/print_lodop/fontPrint.zip"><font color="red">[下载字体]</font></a>
			<br>关闭IE后 解压下载的文件双击"点我安装字体.cmd"安装字体
			</font>
			<br><br>
			<font style="font-size: 14pt; font-family: 宋体">
			如果不能打印,请<a href='/static/core/print_lodop/install_lodop6145_64.exe'><font color="red">[安装打印控件]</font></a>
			</font>
		</div>
	</body>
</html>
