package cn.vetech.vedsb.jp.service.jpgqgl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpgqgl.JpGqdHdDao;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdHd;

@Service
public class JpGqdHdServiceImpl extends MBaseService<JpGqdHd, JpGqdHdDao>{
	/**
	 * 通过改签单号获得去除重复的改签单航段集合
	 * @param gqdh
	 * @return
	 */
	public List<JpGqdHd> getHdListByGqdh(String gqdh, String shbh) {
		JpGqdHd jpGqdHd = new JpGqdHd();
		jpGqdHd.setGqdh(gqdh);
		jpGqdHd.setShbh(shbh);
		List<JpGqdHd> hdList = this.getMyBatisDao().select(jpGqdHd);
		return getOneHdListByGqdh(hdList);
	}
	
	/**
	 * 去除改签单航段中的重复航段，多航段id用","拼接
	 * @return
	 */
	public List<JpGqdHd> getOneHdListByGqdh(List<JpGqdHd> hdList) {
		List<JpGqdHd> gqhdList = new ArrayList<JpGqdHd>();
		Iterator<JpGqdHd> jpgqdhditr = hdList.iterator();
		while (jpgqdhditr.hasNext()) {
			JpGqdHd hd = jpgqdhditr.next();
			if (gqhdList == null || gqhdList.isEmpty()) {
				gqhdList.add(hd);
			} else {
				boolean bl=true;
				String keyone = hd.getCfcity() + hd.getDdcity() + hd.getoCfsj();
				Iterator<JpGqdHd> jpGqdhditr=gqhdList.iterator();
				while (jpGqdhditr.hasNext()) {
					JpGqdHd gqhd = jpGqdhditr.next();
					String keytwo = gqhd.getCfcity() + gqhd.getDdcity() + gqhd.getoCfsj();
					if (keyone.equals(keytwo)) {
						gqhd.setId(hd.getId() + "," + gqhd.getId());
						bl = false;
						break;
					}
				}
				if (bl) {
					gqhdList.add(hd);
				}
			}
		}
		return gqhdList;
	}
	
	public int updateHdById(JpGqdHd jpGqdHd) {
		return this.getMyBatisDao().updateByIdSelective(jpGqdHd);
	}
	
	public List<Map<String,Object>> getGqHdByJpHdId(JpGqdHd jpGqdHd) {
		return this.getMyBatisDao().getGqHdByJpHdId(jpGqdHd);
	}
}
