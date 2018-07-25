package com.usts.shopdemo.enti;

public class MyOrderDetail {
	private String goodsName;
	private String goodsType;
	private Double goodsPrice;
	private String goodsImgUrl;
	private int buyAmount;

	public MyOrderDetail(String goodsName, String goodsType, Double goodsPrice, String goodsImgUrl, int buyAmount) {
		super();
		this.goodsName = goodsName;
		this.goodsType = goodsType;
		this.goodsPrice = goodsPrice;
		this.goodsImgUrl = goodsImgUrl;
		this.buyAmount = buyAmount;
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

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsImgUrl() {
		return goodsImgUrl;
	}

	public void setGoodsImgUrl(String goodsImgUrl) {
		this.goodsImgUrl = goodsImgUrl;
	}

	public int getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(int buyAmount) {
		this.buyAmount = buyAmount;
	}

	@Override
	public String toString() {
		return "MyOrderDetail [goodsName=" + goodsName + ", goodsType=" + goodsType + ", goodsPrice=" + goodsPrice
				+ ", goodsImgUrl=" + goodsImgUrl + ", buyAmount=" + buyAmount + "]";
	}

}
