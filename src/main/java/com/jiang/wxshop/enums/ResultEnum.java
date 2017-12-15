package com.jiang.wxshop.enums;

public enum ResultEnum {
	SUCCESS(0,"成功"),
	PARAM_ERROR(1,"参数不正确"),
	PRODUCT_NOT_EXIT(10,"商品不存在"),
	PRODUCTION_STOCK_ERROR(11,"库存不正确"),
	ORDER_NOT_EXIT(12,"订单不存在"),
	ORDERDETAIL_NOT_EXIT(13,"订单详情不存在"),
	ORDER_STATUS_ERROR(14,"订单状态错误"),
	ORDER_UPDATE_FAIL(15,"订单更新失败"),
	ORDER_DETAIL_EMPTY(16,"订单详情为空"),
	ORDER_PAY_STATUS_ERROR(17,"支付状态不正确"),
	CART_EMPTY(18,"购物车为空"),
	ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),
	ORDER_CANCEL_SUCCESS(20,"订单取消成功"),
	ORDER_FINISH_SUCCESS(21,"订单完结成功"),
	PRODUCT_STATUS_ERROR(22,"商品状态不正确"),
	PROCUDE_ONSALE_SUCCESS(23,"商品上架成功"),
	PROCUDE_OFFSALE_SUCCESS(24,"商品下架成功"),
	PROCUDE_SAVE_SUCCESS(25,"商品提交成功")
	;
	private Integer code ;
	private String message ; 
	
	ResultEnum(Integer code , String message) {
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
