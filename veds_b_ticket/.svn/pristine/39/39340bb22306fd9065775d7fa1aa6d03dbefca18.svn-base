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
		var zNodes =[  
		             	<c:if test='${not empty hkgsxxList}'>
			             	<c:forEach items='${hkgsxxList}' var='vc'>
	      						{'id':'${vc.hkgs}','pId':'${vc.bca}','name':'${vc.hkgs}${vc.mc}'},
	    					</c:forEach>
		             	</c:if>
		             	<c:if test='${empty hkgsxxList}'>
			             	<c:forEach items='${airwayList}' var='vc'>
	      						{'id':'${vc.ezdh}','pId':'${empty param.bca ? '720102' : param.bca}','name':'${vc.ezdh}${vc.shortname}'},
	    					</c:forEach>
	             		</c:if>
					];
					
				var setting = {  
				    isSimpleData : true,              //数据是否采用简单 Array 格式，默认false  
				    treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性  
				    treeNodeParentKey : "pId",        //在isSimpleData格式下，当前节点的父节点id属性  
				    showLine : true,                  //是否显示节点间的连线  
				    checkable : true ,
					callback : {
						onClick : onClick
					}                 //每个节点上是否显示 CheckBox  
				};  
				
			$(document).ready(function() {
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			});
			
			
			//点击的时候去查询Sh_jcsj
			function onClick(event, treeId, treeNode) {
				var id = treeNode.id;
				var parid = treeNode.pId;
				var name = treeNode.name;
				var url="${ctx}/vedsb/b2bsz/jpb2bdlzh/getHkgsxx?pt=${param.pt}&hkgs="+id+"&bca="+parid;
				$("#info_frame",parent.document.body).attr("src",url);
			}
		
		</script>
	</head>
	<body>
		<div>
		  	<ul id="treeDemo" class="ztree" style="height:580px;overflow:auto;width: 200px"></ul>
		</div>
	</body>
</html>