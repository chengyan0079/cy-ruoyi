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
	private String retCode;
	private String retMsg;
	private T retData;


	public static R error() {

		return error(ResultEnum.FAIL.code, ResultEnum.FAIL.desc);

	}
	public static R error(TradeErrorEnum errorEnum) {

		return error(errorEnum.code,errorEnum.msg);

	}

	public static R error(String msg) {

		return error(ResultEnum.FAIL.code, msg);
	}

	public static<T> R error(String msg , T retData) {

		return error(ResultEnum.FAIL.code, msg, retData);
	}

	public static R error(String retCode, String msg) {
		R r = new R();
		r.setRetCode(retCode);
		r.setRetMsg(msg);
		return r;
	}

	@SuppressWarnings("unchecked")
	public static<T> R error(String retCode, String msg, T retData) {
		R r = new R();
		r.setRetCode(retCode);
		r.setRetMsg(msg);
		r.setRetData(retData);
		return r;
	}

	public static R ok() {
		R r = new R();
		r.setRetCode(ResultEnum.SUCCESS.code);
		r.setRetMsg(ResultEnum.SUCCESS.desc);
		return r;
	}
	@SuppressWarnings("unchecked")
	public static<T> R ok(T retData) {
		R r = new R();
		r.setRetCode(ResultEnum.SUCCESS.code);
		r.setRetData(retData);
		r.setRetMsg(ResultEnum.SUCCESS.desc);
		return r;
	}
	@SuppressWarnings("unchecked")
	public static<T> R ok(T retData, String msg) {
		R r = new R();
		r.setRetCode(ResultEnum.SUCCESS.code);
		r.setRetData(retData);
		r.setRetMsg(msg);
		return r;
	}
	@Override
	public String toString() {
		return "Result{" +
				"retCode='" + retCode + '\'' +
				", retMsg='" + retMsg + '\'' +
				", retData=" + retData +
				'}';
	}

}
