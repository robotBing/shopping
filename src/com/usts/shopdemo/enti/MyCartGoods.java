package com.usts.shopdemo.enti;

public class MyCartGoods {
	private int goodsId;
	private int orderAmount;
	private String buyTime;
	private String goodsName;
	private String goodsType;
	private double goodsPrice;

	public MyCartGoods() {
		super();

	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public MyCartGoods(int goodsId, int orderAmount, String buyTime, String goodsName, String goodsType,
			double goodsPrice) {
		super();
		this.goodsId = goodsId;
		this.orderAmount = orderAmount;
		this.buyTime = buyTime;
		this.goodsName = goodsName;
		this.goodsType = goodsType;
		this.goodsPrice = goodsPrice;
	}

}
