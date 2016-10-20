<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>


<div class="tabContainer">
	<ul class="tabHead">
		<li tl='1' onclick="loadTitleContent('1');" class="${ param.lx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">票证退回</a></li>
		<li tl='2' onclick="loadTitleContent('2');" class="${empty param.lx or param.lx eq '2' ? 'currentBtn' : ''}"><a style="text-decoration:none;">退回查询</a></li>
	</ul>
</div>
<script type="text/javascript">
$(function(){
    if("${empty param.lx}"==""){
		loadTitleContent(2);
    }
});
function loadTitleContent(lx) {
			var url="${ctx}/vedsb/pzzx/pzth/viewth_xcd_add?lx="+lx;
			if(lx=='2'){
				url="${ctx}/vedsb/pzzx/pzth/viewlist?lx="+lx;
			}
		   // $("#titlePage").load(url); 
		    window.location.href=url;
		    $("#searchForm").pageSearch();
		}
</script>

