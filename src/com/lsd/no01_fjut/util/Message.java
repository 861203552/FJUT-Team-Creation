/**
 * FileName:     Message.java
 * @Description: TODO(用一句话描述该文件做什么)
 * Copyright:    Copyright(C) 2016
 * @author:    gre_yu@163.com
 * @version:   V1.0 
 * Create:        2017年1月17日 上午9:36:31
 *
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2017年1月17日       wu.zh          1.0             1.0
 * Why & What is modified: <修改原因描述>
 */
package com.lsd.no01_fjut.util;

 /**
 * @ClassName:     Message
 * @Description:TODO(用于前后端交互)
 * @author:    gre_yu@163.com
 * @date:        2017年1月17日 上午9:36:31
 *
 */
public class Message {
	private int code;
	private String msg;
	private Object data = "";
	
	public Message() {
    }

    public Message(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Message(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
