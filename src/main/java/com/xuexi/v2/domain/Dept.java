package com.xuexi.v2.domain;

public class Dept {
	private Integer deptId;

	private Integer parentDeptId;

	private String deptSimpleName;

	private String deptFullName;

	private String description;

	private String parentName;

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getParentDeptId() {
		return parentDeptId;
	}

	public void setParentDeptId(Integer parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

	public String getDeptSimpleName() {
		return deptSimpleName;
	}

	public void setDeptSimpleName(String deptSimpleName) {
		this.deptSimpleName = deptSimpleName == null ? null : deptSimpleName.trim();
	}

	public String getDeptFullName() {
		return deptFullName;
	}

	public void setDeptFullName(String deptFullName) {
		this.deptFullName = deptFullName == null ? null : deptFullName.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}