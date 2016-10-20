<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
	    <title>文件上传</title>
	    <script type="text/javascript">
	    	$(function(){
	    		window.parent.refresh();
	    	});
	    	function beforeSubmit(){
	    		var filename=$('#file').val();
		    	if(filename=='' || filename==undefined || filename==null){
		    			layer.msg('请选择文件!',2,3);
		    			return false;
		    		}
		    	var suffix=filename.substr(filename.lastIndexOf(".")+1).toUpperCase();
		    		if(suffix!='XLS' && suffix!='XLSX'){
		    			layer.msg('请上传xls或xlsx格式文件!',2,3);
		    			return false;
		    		}
		    		layer.load('正在上传文件');
		    	}
	    </script>
	</head>
	<body>
		<c:if test="${empty CODE}">
			<form action="${ctx}/vedsb/jpzwgl/jptjsqdr/importJpTjsqDr" method="post" enctype="multipart/form-data" onsubmit="return beforeSubmit();">
			
				<div class="box_border">
					<div class="box_center pt10 pb10">
						<div align="left" style="padding-left:20px;">
							追位订单导入：<input type="file" name="file" class="input-text lh50" size="35" id="file"/>
							<input type="hidden" name="fileName" />
							<input type="hidden" name="moduleType" value="${param.moduleType }"/>
							<input type="hidden" name="closeDiv" value="true"/>
							<input class="ext_btn ext_btn_submit" type="submit" value="导入"/>
						</div>
					</div>
					<!-- 模板下载 -->
					<div class="box_center pt10 pb10">
						<div align="left" style="padding-left:20px;">
							<div>导入文件请参照下载的模板文件&nbsp;<a href="/updownFiles/mb/jp_tjsqdr_import.xls">模板下载</a></div>
						</div>
					</div>
					<!-- 温馨提示 -->
					<div class="box_center pt10 pb10">
						<div align="left" style="padding-left:20px;color:red;">
							<div>温馨提示:</div>
							<div>1.EXCEL模板文件表头文字不要改动，不要删除，不要移动位置，否则会导致导入不成功</div>
							<div>2.表头为红色的代表是必填项</div>
							<div>3.如果导入列有格式要求，把鼠标移到表头上时会有提示</div>
							<div>4.有些输入需要存入对应的值，具体可以参照Sheet2所给出的列表</div> 
						</div>
					</div>
				</div>
			</form>
		</c:if>
		<c:if test="${CODE eq '-1'}">
			<div class="upload_fail">
			  <h2><img src="${ctx}/static/images/tffa/upload_fail.png" width="30" height="30" align="absmiddle" /> 导入失败</h2>
			  <p>${ERROR }</p>
			</div>
		</c:if>
		<c:if test="${CODE eq '1' or CODE eq '0'}">
			<div class="upload_success">
				<h2><img src="${ctx}/static/images/tffa/upload_success.png" width="30" height="30" align="absmiddle" /> ${CODE eq '0' ? '部分' : ''}导入成功</h2>
				<c:if test="${CODE eq '0'}">
					<p>${ERROR }</p>
				</c:if>
			</div>
		</c:if>
	</body>
</html>