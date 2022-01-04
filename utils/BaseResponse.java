package com.sinocarbon.polaris.commons.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import lombok.Data;

@Data
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	// 返回码
	private Integer code;
	// 返回信息
	private String message;
	// 返回的数据
	private Object data;
	// 返回的结果
	private String status;

	public BaseResponse() {

	}

	private BaseResponse(Integer code, String message) {
		this.code = code;
		this.message = message;
		this.status = Constant.FAIL;
	}

	private BaseResponse(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.status = Constant.FAIL;
		this.data = data;
	}

	private BaseResponse(Object data) {
		this.code = 200;
		this.status = Constant.SUCCESS;
		this.data = data;
	}

	private BaseResponse(String message, Object data) {
		this.code = 200;
		this.message = message;
		this.status = Constant.SUCCESS;
		this.data = data;
	}

	private BaseResponse(String message) {
		this.code = 200;
		this.message = message;
		this.status = Constant.SUCCESS;
	}

	public static BaseResponse successed(Object data) {
		return new BaseResponse(data);
	}

	public static BaseResponse successed(String message, Object data) {
		return new BaseResponse(message, data);
	}

	public static BaseResponse successed(String message) {
		return new BaseResponse(message);
	}

	public static BaseResponse failed(Integer code, String message) {
		return new BaseResponse(code, message);
	}

	public static BaseResponse failed(Integer code, String message, Object data) {
		return new BaseResponse(code, message, data);
	}

	public static BaseResponse successedByPage(Page<?> page) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", page.getRecords());
		data.put("pagination", setPagination(page));
		return new BaseResponse(data);
	}

	public static BaseResponse successedByPage(Object resultData, Object pageData) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", resultData);
		data.put("pagination", pageData);
		return new BaseResponse(data);
	}

	private static Map<String, Object> setPagination(Page<?> page) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("total", page.getTotal());
		pageMap.put("pageSize", page.getSize());
		pageMap.put("current", page.getCurrent());
		return pageMap;
	}

}
