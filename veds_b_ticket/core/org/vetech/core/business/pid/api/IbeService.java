package org.vetech.core.business.pid.api;

import java.rmi.RemoteException;

import org.vetech.core.business.pid.api.avh.Avh;
import org.vetech.core.business.pid.api.avh.AvhParam;
import org.vetech.core.business.pid.api.createXcd.CreateXcd2;
import org.vetech.core.business.pid.api.createXcd.CreateXcd2Param;
import org.vetech.core.business.pid.api.createXcd.CreateXcd2Parser;
import org.vetech.core.business.pid.api.detrf.DetrF;
import org.vetech.core.business.pid.api.detrf.DetrFParam;
import org.vetech.core.business.pid.api.detrxml.DetrResult;
import org.vetech.core.business.pid.api.detrxml.DetrXml;
import org.vetech.core.business.pid.api.detrxml.DetrXmlParam;
import org.vetech.core.business.pid.api.devpay.Devpay;
import org.vetech.core.business.pid.api.devpay.DevpayParam;
import org.vetech.core.business.pid.api.editpnr.EditPnr;
import org.vetech.core.business.pid.api.editpnr.EditPnrParam;
import org.vetech.core.business.pid.api.pat.Pat;
import org.vetech.core.business.pid.api.pat.PatParam;
import org.vetech.core.business.pid.api.pnrauth.PnrAuth;
import org.vetech.core.business.pid.api.pnrauth.PnrAuthParam;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.rrtpnr.RrtPnr;
import org.vetech.core.business.pid.api.rrtpnr.RrtPnrParam;
import org.vetech.core.business.pid.api.rtkt.RtKt;
import org.vetech.core.business.pid.api.rtkt.RtKtParam;
import org.vetech.core.business.pid.api.rtkt.RtKtResult;
import org.vetech.core.business.pid.api.rtpnr.PnrRt;
import org.vetech.core.business.pid.api.rtpnr.PnrRtParam;
import org.vetech.core.business.pid.api.tknetrdx.TkneTrdx;
import org.vetech.core.business.pid.api.tknetrdx.TkneTrdxResult;
import org.vetech.core.business.pid.api.tknetrdx.VeTkneTrdxParam;
import org.vetech.core.business.pid.api.trfd.Trfd;
import org.vetech.core.business.pid.api.trfd.TrfdParam;
import org.vetech.core.business.pid.api.tss.Tss;
import org.vetech.core.business.pid.api.tss.TssParam;
import org.vetech.core.business.pid.api.tss.TssResult;
import org.vetech.core.business.pid.api.xeflight.XeFlight;
import org.vetech.core.business.pid.api.xeflight.XeFlightParam;
import org.vetech.core.business.pid.api.xepassenger.XePassenger;
import org.vetech.core.business.pid.api.xepassenger.XePassengerParam;
import org.vetech.core.business.pid.api.xepnr.XePnr;
import org.vetech.core.business.pid.api.xepnr.XePnrParam;
import org.vetech.core.business.pid.exception.EtermException;
/**
 * 对外提供服务
 * @author 章磊
 *
 */
public class IbeService {
	public static String avh(AvhParam avhParam) throws EtermException{
		Avh avh = new Avh();
		return avh.avh(avhParam);
	}
	
	public static Pnr rtPnr(PnrRtParam param) throws EtermException {
		PnrRt pnrrt = new PnrRt();
		return pnrrt.pnrRt(param);
	}
	
	public static Pnr rrtPnr(RrtPnrParam param) throws EtermException {
		RrtPnr rrtPnr = new RrtPnr();
		return rrtPnr.rrtPnr(param);
	}
	
	public static String xePnr(XePnrParam param) throws EtermException {
		XePnr xePnr = new XePnr(param);
		return xePnr.xePnr();
	}

	public static String xePassenger(XePassengerParam param) throws EtermException {
		XePassenger xePassenger = new XePassenger();
		return xePassenger.xepassenger(param);
	}
	
	public static String xeFlight(XeFlightParam param) throws EtermException{
		XeFlight flight = new XeFlight(param);
		return flight.xeFlight(param);
	}
	
	public static Object trfd(TrfdParam param) throws EtermException {
		Trfd trfd = new Trfd();
		return trfd.trfd(param);
	}
	
	public static String detrF(DetrFParam param) throws EtermException {
		DetrF detrF = new DetrF();
		return detrF.detrF(param);
	}
	
	public static DetrResult detrXml(DetrXmlParam detrXmlParam) throws EtermException {
		DetrXml detrXml = new DetrXml(detrXmlParam);
		return detrXml.detr();
	}

	public static String editPnr(EditPnrParam editPnrParam) throws EtermException {
		EditPnr editPnr = new EditPnr(editPnrParam);
		return editPnr.editPNR();
	}
	
	public static boolean pnrAuth(PnrAuthParam pnrAuthParam) throws EtermException {
		PnrAuth pnrAuth = new PnrAuth(pnrAuthParam);
		return pnrAuth.pnrAuth();
	}
	
	public static TkneTrdxResult tkneTrdx(VeTkneTrdxParam param) throws EtermException {
		TkneTrdx tkneTrdx = new TkneTrdx();
		return tkneTrdx.tkneTrdx(param);
	}
	
	public static CreateXcd2Parser createXcd2(CreateXcd2Param createXcd2Param) throws EtermException, RemoteException{
		CreateXcd2 createXcd2 = new CreateXcd2(createXcd2Param);
		return createXcd2.createXcd2();
	}
	
	public static String pat(PatParam param) throws EtermException{
		Pat pat = new Pat(param);
		return pat.pat();
	}
	
	public static TssResult tss(TssParam param) throws EtermException {
		Tss tss = new Tss();
		return tss.tss(param);
	}
	
	public static String devpay(DevpayParam param) throws EtermException{
		Devpay pay = new Devpay();
		return pay.devpay(param);
	}
	
	public static RtKtResult rtkt(RtKtParam param) throws EtermException{
		RtKt rtkt = new RtKt();
		return rtkt.rtKt(param);
	}
	
	public static void main(String[] args) throws EtermException {
//		AvhParam avhParam = new AvhParam();
//		avhParam.setCfcs("WUH");
//		avhParam.setDdcs("DLC");
//		avhParam.setCfrq("2016-03-25");
//		avhParam.setUserid("8402");
//		avhParam.setUrl("http://192.168.1.69:8088");
//		String xml = IbeService.avh(avhParam);
//		
//		System.out.println(xml);
		/*
		PnrRtParam param = new PnrRtParam();
		param.setPnrno("HRNN3B");
		param.setUserid("16072810380935");
		param.setUrl("http://192.168.110.127:8088");
		Pnr pnr = IbeService.rtPnr(param);
		
		System.out.println(pnr.getPnr_lr());*/
		
		TssParam tssParam=new TssParam();
		tssParam.setUserid("16072810380935");
		tssParam.setUrl("http://192.168.110.127:8088");
		tssParam.setTicketno("7319144598890");
		tssParam.setIsSuspended("1");
		tssParam.setOfficeId("SHA384");
		TssResult tssResult=IbeService.tss(tssParam);
		System.out.println(tssResult.getTss());
		
		
		
	}
}
