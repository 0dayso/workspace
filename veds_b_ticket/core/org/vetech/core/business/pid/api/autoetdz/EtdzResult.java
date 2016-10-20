package org.vetech.core.business.pid.api.autoetdz;

import java.util.ArrayList;
import java.util.List;

public class EtdzResult {
	private String acny;
	private String cp_office;
	private String cp_printno;
	private List<EtdzResultTkno> tkno_cjrList=new ArrayList<EtdzResultTkno>();
	public String getAcny() {
		return acny;
	}
	public void setAcny(String acny) {
		this.acny = acny;
	}
	public String getCp_office() {
		return cp_office;
	}
	public void setCp_office(String cp_office) {
		this.cp_office = cp_office;
	}
	public String getCp_printno() {
		return cp_printno;
	}
	public void setCp_printno(String cp_printno) {
		this.cp_printno = cp_printno;
	}
	public void add(EtdzResultTkno etdzResultTkno){
		tkno_cjrList.add(etdzResultTkno);
	}
	public List<EtdzResultTkno> getTkno_cjrList() {
		return tkno_cjrList;
	}
}
