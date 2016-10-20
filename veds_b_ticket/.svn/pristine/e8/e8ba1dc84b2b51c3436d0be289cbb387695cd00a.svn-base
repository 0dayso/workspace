<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<span id="banklistspanid">
 <div id="tbl-container2" style="overflow: auto;height:280px;">
<display:table id="banklistid" name="banklist"  class="list_table" 
decorator="org.displaytag.decorator.TotalTableDecorator" style="width:520px;white-space: nowrap;" length="3">
	<display:column title="序号" style="text-align: center;">${banklistid_rowNum}</display:column>
		<c:forEach var="headone" items="${headList }" >    
			  <c:if test="${not empty headone}">
			 	 <display:column  title="${headone }" 
			 	 style="${headone eq '发生金额' ? 'background-color:#00E000;color:#000000;text-align: right;' : ''}"
			 	 property="${headone}" 
			 	 total="${headone eq '发生金额'}"  
			 	 format="${(headone eq '发生金额') ? '{0,number,#0.00}' : ''}"
			 	 maxLength="28"
			 	 />
			  </c:if>
		</c:forEach>			
</display:table>
</div>
</span>
<script type="text/javascript">
	$(function(){
	  	var errorUrl='${fileerror}';
		parent.document.getElementById('wcydz').style.display="none";
		if(!!errorUrl){
			parent.document.getElementById('wsbfile').value=errorUrl;
			parent.document.getElementById('wcydz').style.display="";
		}
		highlightTableRows('banklistid');
	})
</script>