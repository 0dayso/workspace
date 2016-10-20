<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="tffaimg">
	<div class="tabContainer">
	<ul class="tabHead">
		<!--  <li lx='1' onclick="loadContent('1',this);"><a>待审核</a></li> -->
		<li lx='1' onclick="loadContentzw('1',this);" class="${empty param.lx or param.lx eq '1' ? 'currentBtn' : '' }"><a style="text-decoration:none;">追位队列</a></li>
		<li lx='2' onclick="loadContentzw('2',this);" class="${param.lx eq '2' ? 'currentBtn' : '' }"><a style="text-decoration:none;">追位成功未处理</a></li>
		<li lx='3' onclick="loadContentzw('3',this);" class="${param.lx eq '3' ? 'currentBtn' : '' }"><a style="text-decoration:none;">追位成功已采用</a></li>
		<li lx='4' onclick="loadContentzw('4',this);" class="${param.lx eq '4' ? 'currentBtn' : '' }"><a style="text-decoration:none;">追位成功已取消</a></li>
		<li lx='5' onclick="loadContentzw('5',this);" class="${param.lx eq '5' ? 'currentBtn' : '' }"><a style="text-decoration:none;">追位成功已预留</a></li>
		<li lx='6' onclick="loadContentzw('6',this);" class="${param.lx eq '6' ? 'currentBtn' : '' }"><a style="text-decoration:none;">未进追位队列</a></li>
	</ul>
	<div class="clear"></div>
	</div>
</div>
<script type="text/javascript">
	function loadContentzw(index,obj){
		var url = "";
		if(index == '1'){
			url = "${ctx}/vedsb/jpzwgl/jptjsq/viewzwdllist?lx="+index+"&zwlx=1";
		}else if(index == '2'){
			url = "${ctx}/vedsb/jpzwgl/jptjsq/viewzwcgwcllist?lx="+index+"&zwlx=1";
		}else if(index == '3'){
			url = "${ctx}/vedsb/jpzwgl/jptjsq/viewzwcgycylist?lx="+index+"&zwlx=1";
		}else if(index == '4'){
			url = "${ctx}/vedsb/jpzwgl/jptjsq/viewzwcgqxlist?lx="+index+"&zwlx=1";
		}else if(index == '5'){
			url = "${ctx}/vedsb/jpzwgl/jptjsq/viewzwcgyyllist?lx="+index+"&zwlx=1";
		}else if(index == '6'){
			url = "${ctx}/vedsb/jpzwgl/jptjsq/viewwjzwdllist?lx="+index+"&zwlx=1";
		}
		document.location.href = url;
	}
</script>