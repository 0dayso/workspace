package org.vetech.core.modules.web.ztree;



public class ZTreeNode {
	private String id;
	
	private String pId;
	
	private String name;
	
	private String checked;
	
	private String icon;
	
	private Object attributes;
	
	private String isParent;
	public ZTreeNode(){
		
	}
	public ZTreeNode(String id, String pId, String name) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.attributes = new Object();
	}
	/**
	 * 需要其他属性时，attributes=null
	 * @param bh
	 * @param zwmc
	 * @param pyszm
	 * @param attributes
	 */
	public ZTreeNode(String id, String pId, String name,Object attributes) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.attributes = attributes;
	}
	public ZTreeNode(String id, String pId, String name,String checked) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.checked = checked;
		this.attributes = new Object();
	}
	public ZTreeNode(String id, String pId, String name,String checked,Object attributes) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.checked = checked;
		this.attributes = attributes;
	}
	
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the pId
	 */
	public String getpId() {
		return pId;
	}
	/**
	 * @param pId the pId to set
	 */
	public void setpId(String pId) {
		this.pId = pId;
	}
	/**
	 * @return the attributes
	 */
	public Object getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
