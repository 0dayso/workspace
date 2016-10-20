<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<title></title>
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
		<script type="text/javascript">
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
		
		CGPT = '${vfn:toJSON(vfc:getVeclassLb('100021'))}';
		
		$.findJsonYwmc = function(jsonArray,v){
			jsonArray = eval(jsonArray);
			for(var p in jsonArray){//遍历json数组时，这么写p为索引，0,1
				if(jsonArray[p].ywmc == v){
					return jsonArray[p];
				}
			}
			return {"ywmc":"","label":"","mc":"","pyszm":""};
		}
		
		var zNodes =[
		    <c:choose>
			    <c:when test="${param.pt eq 'OP'}">
				    {id:1, pId:0, name:"支付方式对应设置",url:"${ctx}/vedsb/cpsz/jpcpymsz/getPtFzsz", target:"ptzcright"},
					{id:2, pId:0, name:"平台账号设置"},
					<c:set var="ptzcbs" value="10100000,10100002,10100003,10100004,10100005,10100007,10100011,10100015,10100018"></c:set>
					<c:forEach items='${ptzhList}' var='vc'>
						<c:if test="${fn:contains(ptzcbs,vc.ptzcbs)}">
							{id:'2${vc.ptzcbs}',pId:2,ptzcbs:'${vc.ptzcbs}','name':$.findJsonYwmc(CGPT,'${vc.ptzcbs}').mc,
								url:"${ctx}/vedsb/cgptsz/jpptzczh/getByPtbs?ptzcbs=${vc.ptzcbs}", target:"ptzcright"},
						</c:if>
					</c:forEach>
		   		</c:when>
		   		<c:when test="${param.pt eq 'BPET'}">
		   			{id:1, pId:0, name:"支付方式对应设置",url:"${ctx}/vedsb/cpsz/jpcpymsz/getB2bzffs", target:"bpetright"},
		   			{id:2, pId:0, name:"航空公司"},
		   			<c:forEach items='${hkgsxxList}' var='vc'>
  						{id:'2${vc.id}',pId:2,'name':'${vc.hkgs}${vc.mc}',
  							url:"${ctx}/vedsb/b2bsz/jpb2bdlzh/getHkgsxx?pt=${param.pt}&hkgs=${vc.hkgs}&bca=720102&from=cpym", target:"bpetright"},
					</c:forEach>
		   		</c:when>
		   		<c:when test="${param.pt eq 'CZ'}">
	   			{id:1, pId:0, name:"航空公司设置"},
	   			<c:forEach items='${hkgsxxList}' var='vc'>
						{id:'1${vc.id}',pId:1,'name':'${vc.hkgs}${vc.mc}',
							url:"${ctx}/vedsb/b2bsz/jpb2bdlzh/getHkgsxx?pt=${param.pt}&hkgs=${vc.hkgs}&bca=720104&from=cpym", target:"czright"},
				</c:forEach>
	   			</c:when>
		    </c:choose>
			
		 ];

		$(document).ready(function(){
			var t = $("#tree");
			t = $.fn.zTree.init(t, setting, zNodes);
			demoIframe = $("#ptzcright");
			demoIframe.bind("load", loadReady);
		});

		function loadReady() {
			var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
			htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
			maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
			h = demoIframe.height() >= maxH ? minH:maxH ;
			if (h < 530) h = 530;
			demoIframe.height(h+30);
		}
		
		function focusetree(id){
			var zTree = $.fn.zTree.getZTreeObj("tree");
			zTree.selectNode(zTree.getNodeByParam("id", id));
		}
		</script>
	</head>
	<body>
		<div>
		  	<ul id="tree" class="ztree" style="height:580px;overflow:auto;"></ul>
		</div>
	</body>
</html>