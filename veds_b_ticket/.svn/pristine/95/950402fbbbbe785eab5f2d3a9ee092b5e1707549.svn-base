<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
  <head>
    <title>b2b账号选择</title>
    <script type="text/javascript">
    	$(function(){
    		var s = '${firsthkgs}';
    		$("."+s+"_flag").show();
    		<c:forEach items="${strid}" var="strid">
    			$("#"+'${strid}'+"_hkgs").attr("checked","checked");
    			choosezh(document.getElementById('${strid}'+"_hkgs"));
    		</c:forEach>
    	});
    	function choosezh(obj){
    		var $obj = $(obj);
    		var hkgs = $obj.attr("hkgs");
    		var hkgsid = $obj.attr("hkgsid");
    		var hkgsflag = hkgs + ":" +hkgsid;
    		$("#"+hkgs).val(hkgsflag);
    	}
    	//关闭当前弹窗
   		function closepj(){
   			var index=parent.layer.getFrameIndex();
			parent.layer.close(index);
   		}
    	
    	function qrb2bzh(){
    		var s = '';
    		$('input[name="hggsarray"]').each(function(){
    			if($(this).val() != ''){
    				if(s == ''){
    					s = $(this).val();
	    			}else{
	    				s+= "/"+$(this).val();
	    			}
    			}
    		});
    		if($("#hsindex").val() == '1'){
    			window.parent.document.getElementById("b2bCpzhs").value = s;
    			window.parent.document.getElementById("b2bCpzhs").setAttribute("title",s);
    		}else{
    			window.parent.document.getElementById("b2bCpzh").value = s;
    			window.parent.document.getElementById("b2bCpzh").setAttribute("title",s);
    		}
    		closepj();
    	}
    	
    	function tabhkgs(obj){
    		var $obj = $(obj);
    		var s = $obj.text();
    		var hsstr = '${hsstr}';
    		if(hsstr.indexOf(s)<0){
    			$("tr").hide();
    			$obj.parent().addClass("currentBtn");
	    		$obj.parent().siblings().removeClass();
	    		$("#titieflag").show();
	    		$("#czflag").show();
    		}else{
	    		$("."+s+"_flag").siblings().hide();
	    		$("."+s+"_flag").show();
	    		$obj.parent().addClass("currentBtn");
	    		$obj.parent().siblings().removeClass();
	    		$("#titieflag").show();
	    		$("#czflag").show();
    		}
    	}
    </script>
  </head>
  <body>
  	<div class="tabContainer">
  	 <ul class="tabHead">
  	 	<c:forEach items="${hggsarray}" var="array" varStatus="v">
  	 		<li style="float: left;" class="${v.index eq 0 ? 'currentBtn' : ''}"><a onclick="tabhkgs(this)">${array}</a></li>
  	 	</c:forEach>
  	 </ul>
  	 <div class="clear"></div>
  	</div>
  	 <div class="container">
  	 <input type="hidden" id="hsindex" value="${index}">
  	 <c:forEach items="${hggsarray}" var="array">
  	 	<input type="hidden" id="${array}" name="hggsarray"/>
  	 </c:forEach>
  	  <div id="search_bar">
	    <div  class="mt10" style="display:table;">
	        <div class="box span10 oh">
		        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
		        <tr id="titieflag">
					<th width="112px">操作</th>
					<th width="112px">航空公司</th>
					<th width="113.5px">登录账号</th>
					<th width="112px">office</th>
				</tr>
		            <c:forEach items="${list}" var="list" varStatus="s">
		            	<tr class="${list.hkgs}_flag" style="display: none;">
							<td class="td_center"><input type="radio" name="${list.hkgs}" hkgs=${list.hkgs} hkgsid=${list.id} onclick="choosezh(this)" id="${list.id}_hkgs"/></td>
			       			<td class="td_center">${list.hkgs}</td>
							<td class="td_center">${list.zh}</td>
							<td class="td_center">${list.office}</td>
		    			</tr>
		            </c:forEach>
	            <tr class="tr" id="czflag">
	            	<td class="td_center" colspan="6">
	            		<input type="button" value="确认" class="ext_btn ext_btn_success" onclick="qrb2bzh()"/>
	            		<input type="button" value="关闭" class="ext_btn ext_btn_success" onclick="closepj()"/>
	            	</td>
	            </tr>
		        </table>
	        </div>
	     </div>
      </div>
  </div>
  </body>
</html>
