package com.xuexi.v2.domain.dto;

import com.xuexi.v2.domain.User;

public class UserDto extends User {

	private int pageNo = 1;
	private int pageSize = 10;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
