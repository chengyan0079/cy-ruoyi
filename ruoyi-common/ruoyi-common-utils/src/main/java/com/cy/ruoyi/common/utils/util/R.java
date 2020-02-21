package com.cy.ruoyi.common.utils.util;

import com.cy.ruoyi.common.utils.enums.ResultEnum;
import com.cy.ruoyi.common.utils.enums.TradeErrorEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @annocation :请求返回类型
 */
@Data
public class R<T> implements Serializable {

	private static final long serialVersionUID = 4145720818048417499L;
	private String code;
	private String msg;
	private T data;


	public static R error() {

		return error(ResultEnum.FAIL.code, ResultEnum.FAIL.desc);

	}
	public static R error(TradeErrorEnum errorEnum) {

		return error(errorEnum.code,errorEnum.msg);

	}

	public static R error(String msg) {

		return error(ResultEnum.FAIL.code, msg);
	}

	public static<T> R error(String msg , T data) {

		return error(ResultEnum.FAIL.code, msg, data);
	}

	public static R error(String code, String msg) {
		R r = new R();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}

	@SuppressWarnings("unchecked")
	public static<T> R error(String code, String msg, T data) {
		R r = new R();
		r.setCode(code);
		r.setMsg(msg);
		r.setData(data);
		return r;
	}

	public static R ok() {
		R r = new R();
		r.setCode(ResultEnum.SUCCESS.code);
		r.setMsg(ResultEnum.SUCCESS.desc);
		return r;
	}
	@SuppressWarnings("unchecked")
	public static<T> R ok(T data) {
		R r = new R();
		r.setCode(ResultEnum.SUCCESS.code);
		r.setData(data);
		r.setMsg(ResultEnum.SUCCESS.desc);
		return r;
	}
	@SuppressWarnings("unchecked")
	public static<T> R ok(T data, String msg) {
		R r = new R();
		r.setCode(ResultEnum.SUCCESS.code);
		r.setData(data);
		r.setMsg(msg);
		return r;
	}
	@Override
	public String toString() {
		return "Result{" +
				"code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", data=" + data +
				'}';
	}

}
