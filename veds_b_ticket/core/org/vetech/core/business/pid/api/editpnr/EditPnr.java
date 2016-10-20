package org.vetech.core.business.pid.api.editpnr;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;

public class EditPnr {
	private EditPnrParam editPnrParam;
	public EditPnr(EditPnrParam editPnrParam){
		this.editPnrParam = editPnrParam;
	}
	/**
	 * 成功返回值是1
	 * @return
	 * @throws EtermException
	 */
	public String editPNR() throws EtermException{
		valid(editPnrParam);
		String commands = this.getCommand();
		System.out.println("修改PNR命令：" + commands);
		WebEtermService etermService = new WebEtermService(editPnrParam.getUrl());
		String data = "";
		try {
			data = etermService.editPNR(editPnrParam.getPnrno(), commands, editPnrParam.getBstrNeedSealed(), editPnrParam.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
			throw new EtermException("调用PID接口Edit PNR错误！"+e.getMessage());
		}
		if (StringUtils.isBlank(data)) {
			throw new EtermException("Edit PNR 返回结果错误！");
		}
		return data;
	}
	
	private void valid(EditPnrParam editPnrParam) throws EtermException {
		if (StringUtils.isBlank(editPnrParam.getPnrno())) {
			throw new EtermException("PNR不能为空");
		}
	}
	
	private String getCommand(){
		return editPnrParam.getSs() 
				+editPnrParam.getDpr() 
				+ editPnrParam.getGn() 
				+ "\r" + editPnrParam.getYe()
				+ editPnrParam.getFoid() 
				+ editPnrParam.getFqtv() 
				+ editPnrParam.getInft() 
				+ editPnrParam.getRmkic() 
				+ editPnrParam.getTlTime();
	}
	public static void main(String[] args) {
		EditPnrParam editPnrParam =new EditPnrParam();
		editPnrParam.setUrl("http://192.168.1.69:8088");
		editPnrParam.setUserid("8635");
		editPnrParam.setPnrno("KYGRVE");
		editPnrParam.addSSR_DOCA("AA", "R", "CN", "WENYIXIROAD", "YUHANGAREA", "HGH", "310000", "", "P1");
		editPnrParam.addSSR_DOCA("AA", "D", "US", "SMCDONNELLRDSLINKRD", "SANFRANCISCO", "CA", "94128", "", "P1");
		EditPnr ep=new EditPnr(editPnrParam);
		try {
			System.out.println(ep.editPNR());
		} catch (EtermException e) {
			e.printStackTrace();
		}
		//成功返回1
		
	}
}
