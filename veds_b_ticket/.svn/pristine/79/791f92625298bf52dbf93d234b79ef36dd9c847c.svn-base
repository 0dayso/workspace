<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
<head>
<title>航空公司自动出票设置</title>
<style type="text/css">
	/*下拉菜单背景色样式*/
	.menuContent .ztree{background:#fcf9c8;border:1px solid #999;}
	.ztree{overflow-y: auto;}
	#rMenu {position:absolute; visibility:hidden; top:0; background: #CAE1FF;text-align: left;padding: 1px;}
	#rMenu ul li{margin: 1px 0;padding:5px 20px;cursor:pointer;list-style: none outside none;height:18px;line-height:18px;width:80px;
		filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFCFF', endColorstr='#DEEAFF');
	}
	#rMenu ul li:hover{filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#DEEAFF', endColorstr='#FAFCFF'); }
	.info {position: absolute;left:180px;top:31px;bottom:2px;right:0px;width: auto;background-color: #fff;z-index: 2;}
</style>
<link rel="stylesheet" href="${ctx}/static/core/ztree/zTreeStyle.css"/>
<script type="text/javascript" src="${ctx}/static/core/ztree/jquery.ztree.all-3.5.min.js?v=${VERSION}"></script>
<script type="text/javascript" >
	var zTree;
	var demoIframe;
	var setting = {
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		} 
	};

	var zNodes =[
		{id:1, pId:1, name:"支付账号设置"},
		{id:"11", pId:1, name:"支付宝", 
			  url:"${ctx}/vedsb/b2bsz/jpb2bzfzh/viewlist?zflx=1", target:"hkgsmainIframe"},
	    {id:"12", pId:1, name:"财付通", 
		  url:"${ctx}/vedsb/b2bsz/jpb2bzfzh/viewlist?zflx=2", target:"hkgsmainIframe"},
	    {id:"13", pId:1, name:"汇付", 
		  url:"${ctx}/vedsb/b2bsz/jpb2bzfzh/viewlist?zflx=3", target:"hkgsmainIframe"},
		
		{id:2, pId:2, name:"B2B航空公司设置"},
		<c:forEach items='${hkgsxxList720102}' var='vc'>
	      	{'id':'${vc.hkgs}','pId':2,'name':'${vc.hkgs}${vc.mc}',
	      		url:"${ctx}/vedsb/b2bsz/jpb2bdlzh/getHkgsxx?pt=${param.pt}&hkgs="+'${vc.hkgs}'+"&bca="+720102, target:"hkgsmainIframe"},
	    </c:forEach>
		 
		 
		{id:3, pId:3, name:"B2C航空公司设置"},
		<c:forEach items='${hkgsxxList720104}' var='vc'>
	      	{'id':'${vc.hkgs}','pId':3,'name':'${vc.hkgs}${vc.mc}',
	      		url:"${ctx}/vedsb/b2bsz/jpb2bdlzh/getHkgsxx?pt=${param.pt}&hkgs="+'${vc.hkgs}'+"&bca="+720104, target:"hkgsmainIframe"},
	    </c:forEach>
		 
		 
		{id:4, pId:4, name:"航司B2B自动出票账号设置", url:"${ctx}/vedsb/b2bsz/jpzdcpb2bzh/viewlist?by1=720102", target:"hkgsmainIframe"},
	 	{id:5, pId:5, name:"航司B2C自动出票账号设置", url:"${ctx}/vedsb/b2bsz/jpzdcpb2bzh/viewlist?by1=720104", target:"hkgsmainIframe"}
	];

	$(document).ready(function(){
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting, zNodes);
		$("#tree_1_ico").attr("class","button ico_close");
		$("#tree_2_ico").attr("class","button ico_close");
		demoIframe = $("#hkgsmainIframe");
		demoIframe.bind("load", loadReady);
	});

	function loadReady() {
		demoIframe.height($(window).height());
	}
	function focusetree(id){
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.selectNode(zTree.getNodeByParam("id", id));
	}

 </script>
</head>
<body>
<table border=0  align=left>
	<tr>
		<td width=200px align=left valign=top style="border-right: #999999 1px dashed">
			<ul id="tree" class="ztree" style="width:200px; overflow:auto;"></ul>
		</td>
		<td width=95% align=left valign=top>
		   <iframe id="hkgsmainIframe" name="hkgsmainIframe" src="" frameborder=0 width="95%"  height="100%"></iframe>
	    </td>
	</tr>
</table>
</body>
</html>