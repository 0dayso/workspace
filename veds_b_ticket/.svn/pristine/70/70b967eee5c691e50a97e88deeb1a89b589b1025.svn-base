<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>


<div class="tabContainer">
	<ul class="tabHead">
		<li tl='1' onclick="loadTitleContent('1',${param.nxdh});" class="${ empty param.tlx or param.tlx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">正常票订单</a></li>
		<li tl='2' onclick="loadTitleContent('2',${param.nxdh});" class="${ param.tlx eq '2' ? 'currentBtn' : ''}"><a style="text-decoration:none;">退废票订单</a></li>
		<li tl='3' onclick="loadTitleContent('3',${param.nxdh});" class="${param.tlx eq '3' ? 'currentBtn' : ''}"><a style="text-decoration:none;">改签票订单</a></li>
	</ul>
</div>
<script type="text/javascript">
$(function(){
    if("${empty param.tlx}"==""){
		loadTitleContent(1,'');
    }
});
function loadTitleContent(tlx,nxdh) {
			var url="${ctx}/vedsb/jpddgl/jpkhdd/viewlist?tlx="+tlx+"&nxdh="+nxdh;
			if(tlx=='2'){
				url="${ctx}/vedsb/jptpgl/jptpd/viewlist?tlx="+tlx+"&nxdh="+nxdh;
			}
			if(tlx=='3'){
				url="${ctx}/vedsb/jpgqgl/jpgqd/viewlist?tlx="+tlx+"&nxdh="+nxdh;
			}
		    window.location.href=url;
		    $("#searchForm").pageSearch();
		}
</script>

