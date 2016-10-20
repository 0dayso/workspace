<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>颜色</title>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<style type=text/css>
td {
	FONT-SIZE: 10.8pt
}
body {
	FONT-SIZE: 10.8pt
}
button {
	WIDTH: 5em
}
</style>

<script text="text/JavaScript">
var SelRGB = '';
var DrRGB = '';
var SelGRAY = '120';

var hexch = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F');

function ToHex(n){	
	n = parseInt(n);
	var h, l;
	n = Math.round(n);
	l = n % 16;
	h = Math.floor((n / 16)) % 16;
	return (hexch[h] + hexch[l]);
}

function DoColor(c, l){ 
	l = parseInt(l);
	var r, g, b;
	r = '0x' + c.substring(1, 3);
	g = '0x' + c.substring(3, 5);
	b = '0x' + c.substring(5, 7);
	
	if(l > 120){
		l = l - 120;
	
		r = (r * (120 - l) + 255 * l) / 120;
		g = (g * (120 - l) + 255 * l) / 120;
		b = (b * (120 - l) + 255 * l) / 120;
	}else{
		r = (r * l) / 120;
		g = (g * l) / 120;
		b = (b * l) / 120;
	}
	
	return '#' + ToHex(r) + ToHex(g) + ToHex(b);
}

function EndColor(){ 
	var i;
	
	if(DrRGB != SelRGB){
		DrRGB = SelRGB;
		for(i = 0; i <= 30; i ++){
			document.getElementById("GrayTable").rows[i].bgColor = DoColor(SelRGB, 240 - i * 8);
		}
	}
	var selColor=$("#SelColor");
	var showColor=$("#ShowColor");
	selColor.val(DoColor($("#RGB").html(),$("#GRAY").html()));
	showColor.attr("bgColor",selColor.val());
}

function getEvent(){ 
     if (document.all && window.event) return window.event;
     func = getEvent.caller;
     while (func != null) {
         var arg0 = func.arguments[0];
         if (arg0) {
             if ((arg0.constructor == Event || arg0.constructor == MouseEvent) || (typeof (arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
                 return arg0;
             }
         }
         func = func.caller;
     }
     return null;
}

function colorTableClick(){
	var event = getEvent();
	var obj=event.srcElement ? event.srcElement : event.target;
	SelRGB = obj.bgColor;
	EndColor();
}

function colorTableMouseOver(){
	var event = getEvent();
	var obj=event.srcElement ? event.srcElement : event.target;
	document.getElementById("RGB").innerHTML = obj.bgColor;
	EndColor();
}

function colorTableMouseOut(){
	document.getElementById("RGB").innerHTML = SelRGB;
	EndColor();
}

function grayTableClick(){
	var event = getEvent();
	var obj=event.srcElement ? event.srcElement : event.target;
	SelGRAY = obj.getAttribute("title");
	EndColor();
}

function grayTableMouseOver(){
	var event = getEvent();
	var obj=event.srcElement ? event.srcElement : event.target;
	document.getElementById("GRAY").innerHTML = obj.getAttribute("title");
	EndColor();
}

function grayTableMouseOut(){
	document.getElementById("GRAY").innerHTML = SelGRAY;
	EndColor();
}

function okClick(inp,sp){
	var selColor=$("#SelColor").val();
	if(selColor.length=='7'){
		parent.$("#"+inp).val(selColor);
		parent.$("#"+sp).css("background-color",selColor);
	}
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
}
</script>

<META content="MSHTML 6.00.2900.3199" name=GENERATOR></HEAD>
<body style="background-color: menu;">
<div align=center>
	<center>
		<table cellSpacing=10 cellPadding=0 border=0>
		  <tbody>
		  <tr>
		    <td>
		      <table id="ColorTable" style="cursor: hand" cellSpacing=0 cellPadding=0 border=0 onclick="colorTableClick()" onmouseover="colorTableMouseOver()" onmouseout="colorTableMouseOut()">
		        <script type="text/JavaScript">
					function wc(r, g, b, n){
						r = ((r * 16 + r) * 3 * (15 - n) + 0x80 * n) / 15;
						g = ((g * 16 + g) * 3 * (15 - n) + 0x80 * n) / 15;
						b = ((b * 16 + b) * 3 * (15 - n) + 0x80 * n) / 15;
						
						document.write('<TD bgColor="#' + ToHex(r) + ToHex(g) + ToHex(b) + '" height=8 width=8></TD>');
					}
					
					var cnum = new Array(1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0);
					
					for(i = 0; i < 16; i ++){
						document.write('<TR>');
						
						for(j = 0; j < 30; j ++){
							n1 = j % 5;
							n2 = Math.floor(j / 5) * 3;
							n3 = n2 + 3;
							
							wc((cnum[n3] * n1 + cnum[n2] * (5 - n1)),
							(cnum[n3 + 1] * n1 + cnum[n2 + 1] * (5 - n1)),
							(cnum[n3 + 2] * n1 + cnum[n2 + 2] * (5 - n1)), i);
						}
						
						document.writeln('</TR>');
					}
				</script>
		
		        </table>
		    </td>
		    <td>
		      <table id="GrayTable" style="cursor: hand" cellSpacing=0 cellPadding=0 border=0 onclick="grayTableClick()" onmouseover="grayTableMouseOver()" onmouseout="grayTableMouseOut()">
		        <script type="text/JavaScript">
					for(i = 255; i >= 0; i -= 8.5){
						document.write('<TR bgColor="#' + ToHex(i) + ToHex(i) + ToHex(i) + '"><TD title="' + Math.floor(i * 16 / 17) + '"  width=20></TD></TR>');
					}
				</script>
		
		     </table>
		    </td>
		  </tr>
		</tbody>
		</table>
	</center>
</div>
<div align=center>
	<center>
		<table cellSpacing=10 cellPadding=0 border=0>
		  <tbody>
			  <tr>
				    <td align=middle rowSpan=2>选中色彩 
					      <table id="ShowColor" height=30 cellSpacing=0 cellPadding=0 width=40 border=1>
					        <tbody>
						        <tr>
						          	<td></td>
						        </tr>
					        </tbody>
					      </table>
				   </td>
				   <td rowSpan=2>基色: <span id="RGB"></span><br>亮度: <span id="GRAY">120</span><br>代码: <input type="text" id="SelColor" size=7></TD>
				   <td><input id="Ok" type=submit onclick="okClick('${param.inp}','${param.sp }')" value="确定"></td>
			  </tr>
			  <tr>
		    		<td><input type="button" onclick="window.close();" value="取消"></td>
		 	  </tr>
		   </tbody>
		</table>
	</center>
</div>
</body>
</html>