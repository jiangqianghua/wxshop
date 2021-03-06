package com.jiang.wxshop.enums;

public enum PlayStatusEnum  implements CodeEnum{
	WAIT(0,"等待支付"),
	SUCCESS(1,"支付成功")
	
	;
	private Integer code ;
	
	private String message ; 
	
	PlayStatusEnum(Integer code , String message) {
		this.code = code ;
		this.message = message ;
		
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
