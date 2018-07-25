package com.usts.shopdemo.enti;

public class GoodsInfo {
	private int goodsId;
	private String goodsName;
	private String goodsType;
	private double goodsPrice;
	private String goodsDate;
	private int goodsAmount;
	private String goodsImgUrl;
	private String goodsIntroduction;

	public GoodsInfo(int goodsId, String goodsName, String goodsType, double goodsPrice, String goodsDate,
			int goodsAmount, String goodsImgUrl, String goodsIntroduction) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsType = goodsType;
		this.goodsPrice = goodsPrice;
		this.goodsDate = goodsDate;
		this.goodsAmount = goodsAmount;
		this.goodsImgUrl = goodsImgUrl;
		this.goodsIntroduction = goodsIntroduction;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
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

	public String getGoodsDate() {
		return goodsDate;
	}

	public void setGoodsDate(String goodsDate) {
		this.goodsDate = goodsDate;
	}

	public int getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(int goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public String getGoodsImgUrl() {
		return goodsImgUrl;
	}

	public void setGoodsImgUrl(String goodsImgUrl) {
		this.goodsImgUrl = goodsImgUrl;
	}

	public String getGoodsIntroduction() {
		return goodsIntroduction;
	}

	public void setGoodsIntroduction(String goodsIntroduction) {
		this.goodsIntroduction = goodsIntroduction;
	}

	@Override
	public String toString() {
		return "GoodsInfo [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsType=" + goodsType
				+ ", goodsPrice=" + goodsPrice + ", goodsDate=" + goodsDate + ", goodsAmount=" + goodsAmount
				+ ", goodsImgUrl=" + goodsImgUrl + ", goodsIntroduction=" + goodsIntroduction + "]";
	}

}