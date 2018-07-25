package com.usts.shopdemo.db;

import java.util.List;
import java.util.Map;

import com.sun.org.apache.xerces.internal.parsers.CachingParserPool.ShadowedGrammarPool;
import com.usts.shopdemo.enti.CustomerInfo;
import com.usts.shopdemo.enti.GoodsInfo;
import com.usts.shopdemo.enti.MyCartGoods;
import com.usts.shopdemo.enti.MyOrderDetail;
import com.usts.shopdemo.enti.MyOrderList;

public interface DBDao {
	// 通过用户账号，和密码登陆
	public CustomerInfo Login(String userCode, String userPwd);
	
	public CustomerInfo Login1(String userCode, String userPwd);

	// 获取商品类别
	public List<String> getGoodsType();

	//通过商品名称获取商品
	public List<GoodsInfo> getGoodsListByName(String goodsName);
	
	// 通过某一个商品类别的方式 去获取商品列表
	public List<GoodsInfo> getGoodsListByType(String goodsType);

	// 默认首页
	public List<GoodsInfo> getFirstTypeGoodsList();

	// 商品详情
	public GoodsInfo getGoodDetail(String goodId);

	// 通过id购买商品
	public boolean buyGoodsById(String goodsId, String targetAmount);

	// 获取我的购物车商品
	public List<MyCartGoods> getMyCartGoods();

	public List<Map<String, Object>> getMyCartGoodsEx();

	// 通过商品id删除购物车内的商品
	public boolean deleteMyCartGoods(String goodsId, String orderAmount);
	
	//查询当前商品数量
	public int getGoodsAmount(String goodsId);
	
	//商品数量加一
	public boolean addOneGoods(String goodsId);
	
	//修改商品数量
	public boolean updateGoodsAmount(String goodsId, String orderAmount, String targetAmount);
	
	//商品数量减一
	public boolean deleteOneGoods(String goodsId);

	// 支付（商品放置到我的订单中）
	public boolean payBill(String userId, String total, String receivePerson, String receiveAddress,
			String receiveCall);

	// 通过userid查询订单列表
	public List<MyOrderList> getOrderListByUserId(String userId);

	// 通过orderId获取我的订单详情
	public List<MyOrderDetail> getOrderDetailByOrderId(String orderId);

	// 通过用户名得到密码
	public String getPwdByName(String name);

	// 通过用户名更新密码
	public void updatePwd(String name, String pwd);

	// 用户注册
	public void addUser(String userName, String userPwd, String email, String address, String callPhone);
	//通过用户名查找用户是否存在
	public boolean isCustomerExist(String name);

}
