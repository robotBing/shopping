package com.usts.shopdemo.db;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.usts.shopdemo.enti.CustomerInfo;
import com.usts.shopdemo.enti.GoodsInfo;
import com.usts.shopdemo.enti.MyCartGoods;
import com.usts.shopdemo.enti.MyOrderDetail;
import com.usts.shopdemo.enti.MyOrderList;

public class DBImpl implements DBDao {
	@Override
	public CustomerInfo Login(String userCode, String userPwd) {
		CustomerInfo ci = null;
		try {
			String sql = "select * from shop_customer " + " where name=? and password=?";
			Connection ct = DBUtil.getConnection();
			PreparedStatement st = ct.prepareStatement(sql);
			st.setString(1, userCode);
			st.setString(2, userPwd);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				ci = new CustomerInfo(rs.getInt("userId"), rs.getString("name"), rs.getString("email"),
						rs.getString("address"), rs.getString("callPhone"));
			}
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ci;
	}
	
	public CustomerInfo Login1(String userCode, String userPwd) {
		CustomerInfo ci = null;
		try {
			String sql = "select * from shop_customer " + " where name=? and password=?";
			Connection ct = DBUtil.getConnection();
			PreparedStatement st = ct.prepareStatement(sql);
			st.setString(1, userCode);
			st.setString(2, userPwd);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				ci = new CustomerInfo(rs.getInt("userId"), rs.getString("name"), rs.getString("email"),
						rs.getString("address"), rs.getString("callPhone"));
			}
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ci;
	}

	@Override
	public List<String> getGoodsType() {
		List<String> lst = new ArrayList<>();
		try {
			String sql = "select distinct goodsType " + "from shop_goods ";
			Connection ct = DBUtil.getConnection();
			Statement st = ct.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				lst.add(rs.getString("goodsType"));
			}
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public List<GoodsInfo> getGoodsListByType(String goodsType) {

		System.out.println(goodsType);
		List<GoodsInfo> lst = new ArrayList<>();
		try {
			String sql = "select * from shop_goods  " + "where goodsType=?";
			Connection ct = DBUtil.getConnection();
			PreparedStatement st = ct.prepareStatement(sql);
			st.setString(1, goodsType);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				lst.add(new GoodsInfo(rs.getInt("goodsId"), rs.getString("goodsName"), rs.getString("goodsType"),
						rs.getDouble("goodsPrice"), rs.getString("goodsDate"), rs.getInt("goodsAmount"),
						rs.getString("goodsImgUrl"), rs.getString("goodsIntroduction")));
			}
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	@Override
	public List<GoodsInfo> getGoodsListByName(String goodsName) {
		List<GoodsInfo> lst = new ArrayList<GoodsInfo>();
		String sql = "select * from shop_goods where goodsName like '%"+goodsName+"%'";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection ct = DBUtil.getConnection();
			Statement st = ct.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				lst.add(new GoodsInfo(rs.getInt("goodsID"),
								rs.getString("goodsName"),
								rs.getString("goodsType"),
								rs.getDouble("goodsPrice"),
								rs.getString("goodsDate"),
								rs.getInt("goodsAmount"),
								rs.getString("goodsImgUrl"),
								rs.getString("goodsIntroduction")));
			}
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lst;
	}
	
	@Override
	public List<GoodsInfo> getFirstTypeGoodsList() {
		List<GoodsInfo> lst = new ArrayList<>();
		try {

			String sql = "select * from shop_goods where goodsType = (select distinct goodsType from shop_goods limit 1);";
			Connection ct = DBUtil.getConnection();
			Statement st = ct.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				lst.add(new GoodsInfo(rs.getInt("goodsId"), rs.getString("goodsName"), rs.getString("goodsType"),
						rs.getDouble("goodsPrice"), rs.getString("goodsDate"), rs.getInt("goodsAmount"),
						rs.getString("goodsImgUrl"), rs.getString("goodsIntroduction")));
			}
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	@Override
	public GoodsInfo getGoodDetail(String goodId) {
		GoodsInfo gi = null;
		try {

			String sql = "select * from shop_goods where goodsId = ?";

			Connection ct = DBUtil.getConnection();
			PreparedStatement st = ct.prepareStatement(sql);
			st.setString(1, goodId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				gi = new GoodsInfo(rs.getInt("goodsId"), rs.getString("goodsName"), rs.getString("goodsType"),
						rs.getDouble("goodsPrice"), rs.getString("goodsDate"), rs.getInt("goodsAmount"),
						rs.getString("goodsImgUrl"), rs.getString("goodsIntroduction"));
			}
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gi;
	}

	@Override
	public boolean buyGoodsById(String goodsId, String targetAmount) {
		//1.该商品库存-targetAmount
				//2.我的购物车如果存在商品，更新记录,数量+targetAmount
				//不存在则添加记录
				boolean bRet = false;
				int goodsAmount = getGoodsAmount(goodsId);
				int targetA = Integer.parseInt(targetAmount);
				
				if(goodsAmount < targetA){
					return bRet;
				}
				
				try {
					Connection ct = DBUtil.getConnection();
					Statement st = ct.createStatement();
					ct.setAutoCommit(false);
					//1
					String sql = "update shop_goods set goodsAmount=goodsAmount- "+targetA
							+ " where goodsId ="+goodsId;
					st.execute(sql);
					
					//2
					sql = "update shop_cart set orderAmount=orderAmount+ "+targetA
							+ " where goodsId ="+goodsId;
					int change = st.executeUpdate(sql);
					if(change == 0){
						//insert into
						String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date());
						
						sql = "insert into shop_cart "
								+ "(goodsId,orderAmount,buyTime) "
								+ "values ("
								+ goodsId
								+ ","+targetA+",'"
								+s
								+"')";
						st.execute(sql);
					}
					
					ct.commit();
					bRet = true;
					
					st.close();
					ct.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return bRet;
	}

	@Override
	public List<MyCartGoods> getMyCartGoods() {
		List<MyCartGoods> lst = new ArrayList<>();
		try {
			String sql = "select c.goodsId,c.orderAmount,c.buyTime," + "g.goodsName,g.goodsType,g.goodsPrice "
					+ "from shop_cart c,shop_goods g " + "where c.goodsId=g.goodsId";
			Connection ct = DBUtil.getConnection();
			PreparedStatement st = ct.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				lst.add(new MyCartGoods(rs.getInt("goodsId"), rs.getInt("orderAmount"), rs.getString("buyTime"),
						rs.getString("goodsName"), rs.getString("goodsType"), rs.getDouble("goodsPrice")));
			}
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public List<Map<String, Object>> getMyCartGoodsEx() {
		List<Map<String, Object>> lst = new ArrayList<>();
		try {
			String sql = "select c.goodsId,c.orderAmount,c.buyTime," + "g.goodsName,g.goodsType,g.goodsPrice "
					+ "from shop_cart c,shop_goods g " + "where c.goodsId=g.goodsId";
			Connection ct = DBUtil.getConnection();
			PreparedStatement st = ct.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("goodsId", rs.getInt("goodsId"));
				map.put("orderAmount", rs.getInt("orderAmount"));
				map.put("buyTime", rs.getString("buyTime"));
				map.put("goodsName", rs.getString("goodsName"));
				map.put("goodsType", rs.getString("goodsType"));
				map.put("goodsPrice", rs.getDouble("goodsPrice"));
				lst.add(map);
			}
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public boolean deleteMyCartGoods(String goodsId, String orderAmount) {
		boolean bRet = false;
		int id = Integer.parseInt(goodsId);
		int amount = Integer.parseInt(orderAmount);
		
		try {
			String sql = "delete from shop_cart where goodsId ="+id;
			Connection ct = DBUtil.getConnection();
			ct.setAutoCommit(false);
			Statement st = ct.createStatement();
			st.execute(sql);
			
			sql = "update shop_goods set goodsAmount = goodsAmount + " + amount
					+ " where goodsId ="+id;
			System.out.println("goodsId="+goodsId+",orderAmount="+orderAmount);
			System.out.println("id="+id+",amount="+amount);
			st.execute(sql);
			
			ct.commit();
			bRet = true;
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bRet;
	}

	@Override
	public boolean deleteOneGoods(String goodsId) {
		boolean bRet = false;
		int id = Integer.parseInt(goodsId);
		int amount = 0;
		String sql = "select orderAmount from shop_cart where goodsId = "+goodsId;
		
		try {
			Connection ct = DBUtil.getConnection();
			ct.setAutoCommit(false);
			Statement st = ct.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				amount = rs.getInt("orderAmount");
			}
			
			if(amount == 1){
				sql = "delete from shop_cart where goodsId ="+id;
				st.execute(sql);
			}else{
				sql = "update shop_cart set orderAmount=orderAmount-1 "
						+ "where goodsId ="+goodsId;
				st.execute(sql);
			}	
			
			sql = "update shop_goods set goodsAmount = goodsAmount + 1 "
						+ " where goodsId ="+id;
			st.execute(sql);
			
			ct.commit();
			bRet = true;
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bRet;
	}
	
	@Override
	public int getGoodsAmount(String goodsId) {
		int id = Integer.parseInt(goodsId);
		int amount = 0;
		String sql = "select goodsAmount from shop_goods where goodsId = "+id;
		
		try {
			Connection ct = DBUtil.getConnection();
			Statement st = ct.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				amount = rs.getInt("goodsAmount");
			}
			System.out.println("amount="+amount);
			
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return amount;
	}

	@Override
	public boolean addOneGoods(String goodsId) {
		boolean bRet = false;
		int id = Integer.parseInt(goodsId);
		
		if(getGoodsAmount(goodsId) == 0){
			return bRet;
		}
		
		try {
			Connection ct = DBUtil.getConnection();
			ct.setAutoCommit(false);
			Statement st = ct.createStatement();
			
			String sql = "update shop_cart set orderAmount=orderAmount+1 "
					+ "where goodsId ="+id;
			st.execute(sql);
			
			sql = "update shop_goods set goodsAmount = goodsAmount - 1 "
					+ " where goodsId ="+id;
			st.execute(sql);
			
			ct.commit();
			bRet = true;
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bRet;
	}

	@Override
	public boolean updateGoodsAmount(String goodsId, String orderAmount, String targetAmount) {
		// TODO Auto-generated method stub
		boolean bRet = false;
		int id = Integer.parseInt(goodsId);
		int orderA = Integer.parseInt(orderAmount);
		int targetA = Integer.parseInt(targetAmount);
		int remainA = getGoodsAmount(goodsId);
		
		if (remainA+orderA < targetA) {
			return bRet;
		}
		
		deleteMyCartGoods(goodsId, orderAmount);
		
		if(targetA == 0){
			return true;
		}
		
		try {
			Connection ct = DBUtil.getConnection();
			Statement st = ct.createStatement();
			ct.setAutoCommit(false);
			//1
			String sql = "update shop_goods set goodsAmount=goodsAmount-"+targetA
					+ " where goodsId ="+id;
			st.execute(sql);
			
			//2
			sql = "update shop_cart set orderAmount="+targetA
					+ " where goodsId ="+id;
			int change = st.executeUpdate(sql);
			if(change == 0){
				//insert into
				String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date());
				
				sql = "insert into shop_cart "
						+ "(goodsId,orderAmount,buyTime) "
						+ "values ("
						+ id
						+ ","+targetA+",'"
						+s
						+"')";
				st.execute(sql);
			}
			
			ct.commit();
			bRet = true;
			
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bRet;
	}
	
	@Override
	public boolean payBill(String userId, String total, String receivePerson, String receiveAddress,
			String receiveCall) {
		// 1. 创建订单（1条记录）
		// 2. 创建订单详情（根据购物车商品数量）
		// 3. 删除我的购物车记录
		try {
			Connection ct = DBUtil.getConnection();
			// 不使用自动提交方式。
			ct.setAutoCommit(false);
			// 1. 创建订单（1条记录）
			String sql = "insert into shop_orderList " + "(userId,receiveName,receiveAddress,"
					+ "receiveTele,allMoney,status," + "buyTime) values (?,?,?,?,?,?,?) ";
			PreparedStatement ps = ct.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, receivePerson);
			System.out.println(receivePerson);
			ps.setString(3, receiveAddress);
			ps.setString(4, receiveCall);
			ps.setString(5, total);
			ps.setString(6, "未支付");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ps.setString(7, sdf.format(new Date()));
			ps.execute();

			// 2. 创建订单详情
			// 通过查询最近一次向具有identity属性(即自增列)的字段，查找到orderId(字段为自增)
			sql = "select @@identity";
			ps = ct.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String orderId = rs.getString("@@identity");
			rs.close();
			sql = "insert into shop_orderDetail ( " + "goodsId,buyAmount,orderId) " + "select goodsId,orderAmount,? "
					+ "from shop_cart";
			ps = ct.prepareStatement(sql);
			ps.setString(1, orderId);
			ps.execute();

			// 3. 删除我的购物车记录
			sql = "delete from shop_cart";
			ps = ct.prepareStatement(sql);
			ps.execute();

			// 提交
			ct.commit();
			ps.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<MyOrderList> getOrderListByUserId(String userId) {
		List<MyOrderList> lst = new ArrayList<MyOrderList>();
		try {
			String sql = "select * from shop_orderList where userId = ?";
			Connection ct = DBUtil.getConnection();
			PreparedStatement st = ct.prepareStatement(sql);
			st.setString(1, userId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				lst.add(new MyOrderList(rs.getInt("orderId"), rs.getInt("userId"), rs.getString("receiveName"),
						rs.getString("receiveAddress"), rs.getString("receiveTele"), rs.getDouble("allMoney"),
						rs.getString("status"), rs.getString("buyTime")));
			}
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public List<MyOrderDetail> getOrderDetailByOrderId(String orderId) {
		List<MyOrderDetail> lst = new ArrayList<>();
		try {
			String sql = "select g.goodsName,g.goodsType," + "g.goodsPrice,g.goodsImgUrl,d.buyAmount "
					+ " from shop_orderDetail d, shop_goods g " + " where d.orderId=? and d.goodsId=g.goodsId";
			Connection ct = DBUtil.getConnection();
			PreparedStatement st = ct.prepareStatement(sql);
			st.setString(1, orderId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				lst.add(new MyOrderDetail(rs.getString("goodsName"), rs.getString("goodsType"),
						rs.getDouble("goodsPrice"), rs.getString("goodsImgUrl"), rs.getInt("buyAmount")));
			}
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;

	}

	@Override
	public String getPwdByName(String name) {
		String pwd = null;
		try {
			String sql = "select password from shop_customer where name = ?";
			Connection ct = DBUtil.getConnection();
			PreparedStatement st = ct.prepareStatement(sql);
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				pwd = rs.getString("password");
			}
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pwd;
	}

	@Override
	public void updatePwd(String name, String pwd) {
		try {
			Connection ct = DBUtil.getConnection();
			Statement st = ct.createStatement();
			String sql = "update shop_customer " + "set password=" + pwd + " where name='" + name + "'";
			st.executeUpdate(sql);

			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addUser(String userName, String userPwd, String email, String address, String callPhone) {
		try {

			Connection ct = DBUtil.getConnection();

			String sql = "insert into shop_customer " + "(name,password,email,"
					+ "address,callPhone) values (?,?,?,?,?) ";
			PreparedStatement ps = ct.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, userPwd);
			ps.setString(3, email);
			ps.setString(4, address);
			ps.setString(5, callPhone);
			ps.execute();
			ps.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean isCustomerExist(String name) {
		boolean exist = false;
		try {
			String sql = "select * from shop_customer where name = ?";
			Connection ct = DBUtil.getConnection();
			PreparedStatement st = ct.prepareStatement(sql);
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				exist = true;
			}
			rs.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(exist+"aaa");
		return exist;
	}

}
