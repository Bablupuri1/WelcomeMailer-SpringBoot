package com.ecom.responseapi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {

	public boolean success;
	public String message;
	public Integer status;
	private Object data;
//	private List<String> list;

	public ApiResponse(boolean success, String message, Integer status, Object data) {
		super();
		this.success = success;
		this.message = message;
		this.status = status;
		this.data = data;
//		this.list = list;
	}

}
