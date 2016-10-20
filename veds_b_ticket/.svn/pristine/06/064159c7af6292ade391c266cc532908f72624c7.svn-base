//计算应退金额
function ytje(name,ps) {
	if (name == "cg") {
			var cgZdj = parseFloat($("#cgZdj").val());
			var cgPj = parseFloat($("#cgPj").val());
			var cgJsf = parseFloat($("#cgJsf").val());
			var cgTax = parseFloat($("#cgTax").val());
			var cgTpf = parseFloat($("#cgTpf").val());
			var cgTkje = (cgPj + cgJsf + cgTax + cgTpf);
			$("#cgTkje").html(cgTkje);

			//合计列
			$("#hj_cgZdj").val(cgZdj * ps);
			$("#hj_cgPj").html(cgPj * ps);
			$("#hj_cgJsf").html(cgJsf * ps);
			$("#hj_cgTax").html(cgTax * ps);
			$("#hj_cgTpf").html(cgTpf * ps);
			$("#hj_cgTkje").html(cgTkje * ps);
		}else if (name == "xs") {
			var xsZdj = parseFloat($("#xsZdj").val());
			var xsPj = parseFloat($("#xsPj").val());
			var xsJsf = parseFloat($("#xsJsf").val());
			var xsTax = parseFloat($("#xsTax").val());
			var xsTpsxf = parseFloat($("#xsTpsxf").val());

        	//合计列
			var xsTkje = (xsPj + xsJsf + xsTax + xsTpsxf);
			$("#xsTkje").html(xsTkje);
			$("#hj_xsZdj").html(parseFloat(xsZdj * ps).toFixed(2));
			$("#hj_xsPj").html(parseFloat(xsPj * ps).toFixed(2));
			$("#hj_xsJsf").html(parseFloat(xsJsf * ps).toFixed(2));
			$("#hj_xsTax").html(parseFloat(xsTax * ps).toFixed(2));
			$("#hj_xsTpsxf").html(parseFloat(xsTpsxf * ps).toFixed(2));
			$("#hj_xsTkje").html(parseFloat(xsTkje * ps).toFixed(2));
		}
}

function toTpfl(name,ps) {
	var Zdj = parseFloat($("#" + name + "Zdj").val());
	var Tpfl = parseFloat($("#" + name + "Tpfl").val());
	if (name == "cg") {
		$("#cgTpf").val(getZs(Zdj * Tpfl / 100));
	}
	if (name == "xs") {
		$("#xsTpsxf").val(getZs(Zdj * Tpfl / 100));
	}
	ytje(name,ps);
}

//转换成正数
function getZs(num){
	if(parseFloat(num) < 0){
		return Math.abs(num);
	}
	return parseFloat(num);
}

//转换成负数
function getFs(num){
	if(parseFloat(num) > 0){
		return 0-num;
	}
	return parseFloat(num);
}