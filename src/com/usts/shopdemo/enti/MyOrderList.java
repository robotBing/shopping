package com.usts.shopdemo.enti;

public class MyOrderList {
	private int orderId;
	private int userId;
	private String receiveName;
	private String receiveAddress;
	private String receiveTele;
	private double allMoney;
	private String status;
	private String buyTime;

	public MyOrderList(int orderId, int userId, String receiveName, String receiveAddress, String receiveTele,
			double allMoney, String status, String buyTime) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.receiveName = receiveName;
		this.receiveAddress = receiveAddress;
		this.receiveTele = receiveTele;
		this.allMoney = allMoney;
		this.status = status;
		this.buyTime = buyTime;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getReceiveTele() {
		return receiveTele;
	}

	public void setReceiveTele(String receiveTele) {
		this.receiveTele = receiveTele;
	}

	public double getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(double allMoney) {
		this.allMoney = allMoney;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}

	@Override
	public String toString() {
		return "MyOrderList [orderId=" + orderId + ", userId=" + userId + ", receiveName=" + receiveName
				+ ", receiveAddress=" + receiveAddress + ", receiveTele=" + receiveTele + ", allMoney=" + allMoney
				+ ", status=" + status + ", buyTime=" + buyTime + "]";
	}

}
