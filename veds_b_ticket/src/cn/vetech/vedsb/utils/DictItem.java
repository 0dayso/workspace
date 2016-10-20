package cn.vetech.vedsb.utils;

public class DictItem {
	private String value;// 代码
	private String mc;// 名称
	private String type;// 类型
	private String label;// 描叙

	public DictItem(String value, String mc) {
		this.value = value;
		this.mc = mc;
	}

	public DictItem(String value, String mc, String type) {
		this.value = value;
		this.mc = mc;
		this.type = type;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public boolean equals(Object anObject) {
		if (anObject == null) {
			return false;
		}
		if (anObject instanceof DictItem) {
			String c = ((DictItem) anObject).getValue();
			return value.equals(c);
		} else {
			return value.equals(anObject.toString());
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
