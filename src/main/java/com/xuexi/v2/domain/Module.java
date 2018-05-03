package com.xuexi.v2.domain;

import java.util.List;

public class Module {
    private Integer moduleId;

    private String icon;

    private String moduleName;

    private String description;

    private Integer orderBy;

    private Integer status;
    
    private List<Menu> menus;

 
	public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public List<Menu> getMenus() {
 		return menus;
 	}

 	public void setMenus(List<Menu> menus) {
 		this.menus = menus;
 	}

}