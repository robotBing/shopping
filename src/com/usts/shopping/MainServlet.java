package com.usts.shopping;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.usts.shopdemo.db.DBDao;
import com.usts.shopdemo.db.DBImpl;
import com.usts.shopdemo.enti.CustomerInfo;
import com.usts.shopdemo.enti.GoodsInfo;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "touxiang";
	
	// 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

	public MainServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 定义编码格式
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 2. 获取action
		// 3. 得到输入
		// 4. 业务处理
		// 5. 输出
		System.out.println(request.getMethod());
		/*
		 * user/regedit.action taobao/user/login.action
		 */
		System.out.println(request.getServletPath());
		String path = request.getServletPath();
		String action = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
		System.out.println(action);
		switch (action) {
		case "regedit":
			regedit(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "showGoodsList":
			showGoodsList(request, response);
			break;
		case "buyInList":
			buyInList(request, response);
			break;
		case "deleteMyCartGoods":
			deleteMyCartGoods(request, response);
			break;
		case "payBill":
			payBill(request, response);
			break;
		case "resetPwd":
			resetPwd(request, response);
			break;
		case "registe":
			registe(request, response);
			break;
		case "login1":
			login1(request, response);
			break;
		case "deleteOneGoods":
			deleteOneGoods(request, response);
			break;
		case "addOneGoods":
			addOneGoods(request, response);
			break;
		case "updateOrderAmount":
			updateOrderAmount(request, response);
			break;
		}
	}
	//购物车里修改商品数量
	private void updateOrderAmount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String goodsId = request.getParameter("goodsId");
		String orderAmount = request.getParameter("orderAmount");
		String targetAmount = request.getParameter("targetAmount");
		DBDao dao = new DBImpl();
		boolean bUpdateAmount = dao.updateGoodsAmount(goodsId, orderAmount, targetAmount);
		response.sendRedirect("shoppingCar.jsp?updateAmount="+bUpdateAmount);
	}
	//购物车里增加一个商品
	private void addOneGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String goodsId = request.getParameter("goodsId");
		
		boolean bCheckAmount = new DBImpl().addOneGoods(goodsId);
		response.sendRedirect("shoppingCar.jsp?checkAmount="+bCheckAmount);
	}
	//购物车里减少一个商品
	private void deleteOneGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String goodsId = request.getParameter("goodsId");
		
		new DBImpl().deleteOneGoods(goodsId);
		response.sendRedirect("shoppingCar.jsp");
	}
	
	private void registe(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("userName");
		name = new String(name.getBytes("ISO-8859-1"), "UTF-8");

		String pwd = request.getParameter("userPwd");
		pwd = new String(pwd.getBytes("ISO-8859-1"), "UTF-8");

		String email = request.getParameter("email");
		email = new String(email.getBytes("ISO-8859-1"), "UTF-8");

		String address = request.getParameter("address");
		address = new String(address.getBytes("ISO-8859-1"), "UTF-8");

		String callPhone = request.getParameter("callPhone");
		callPhone = new String(callPhone.getBytes("ISO-8859-1"), "UTF-8");
		DBDao dao = new DBImpl();
		boolean exist = dao.isCustomerExist(name);

		if(exist){
			response.sendRedirect("registe.jsp?status=2");
		}else{
			dao.addUser(name, pwd, email, address, callPhone);
			response.sendRedirect("login.jsp");
		}

	}
	
	
	//上传文件
	private void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        
        // 中文处理
        upload.setHeaderEncoding("UTF-8"); 

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
       
         
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
            System.out.println("创建");
        }
 
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + "123.jpg";
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                       System.out.println("头像上传成功");
                    }
                }
            }
        } catch (Exception ex) {
        	System.out.println("头像上传失败");
            ex.printStackTrace();
        }
	}

	// 重置密码
	// 从数据库里取用户信息，和用户输入的旧密码对比
	// 如果旧密码正确，修改数据库，返回修改成功页面
	// 如果旧密码错误，返回修改失败，重新填写
	private void resetPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DBDao dao = new DBImpl();
		String name = null;
		Cookie[] cks = request.getCookies();
		for (Cookie ck : cks) {
			if (ck.getName().equalsIgnoreCase("name")) {
				name = ck.getValue();
			}
		}
		String oldPwdInput = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		name = URLDecoder.decode(name, "utf-8");
		String oldPwdDB = dao.getPwdByName(name);
		System.out.println(name);
		System.out.println(oldPwdDB);
		if (oldPwdInput.equals(oldPwdDB)) {
			dao.updatePwd(name, newPwd);
			for (Cookie ck : cks) {
				if (ck.getName().equalsIgnoreCase("name")) {
					ck.setMaxAge(0);
					response.addCookie(ck);
				}
				if (ck.getName().equalsIgnoreCase("userId")) {
					ck.setMaxAge(0);
					response.addCookie(ck);
				}
				if (ck.getName().equalsIgnoreCase("address")) {
					ck.setMaxAge(0);
					response.addCookie(ck);
				}
				if (ck.getName().equalsIgnoreCase("callPhone")) {
					ck.setMaxAge(0);
					response.addCookie(ck);
				}

			}
			response.sendRedirect("resetPwdSuccess.html");
		} else {
			response.sendRedirect("resetPwd.jsp?name=" + name + "&status=2");
			//response.sendRedirect("resetPwd.jsp?status=2");
		}

	}

	private void payBill(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 从前台获取前4个数据，从cookies中获取userId
		String total = request.getParameter("total");
		String receivePerson = request.getParameter("receivePerson");
		receivePerson = new String(receivePerson.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("servlet输出：" + receivePerson);
		String receiveAddress = request.getParameter("receiveAddress");
		receiveAddress = new String(receiveAddress.getBytes("ISO-8859-1"), "UTF-8");
		String receiveCall = request.getParameter("receiveCall");
		System.out.println("total: " + total);
		String userId = "";
		Cookie[] cks = request.getCookies();
		for (Cookie ck : cks) {
			if (ck.getName().equalsIgnoreCase("userId")) {
				userId = ck.getValue();
				break;
			}
		}
		System.out.println("user: " + userId);
		// DBImpl().payBill()
		new DBImpl().payBill(userId, total, receivePerson, receiveAddress, receiveCall);
		response.sendRedirect("payBillSuccess.html");

	}

	private void buyInList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 1. 获取商品ID的信息
		String goodsId = request.getParameter("goodsId");
		String targetAmount = request.getParameter("targetAmount");
		String ty = request.getParameter("type");
		// 2. 业务
		boolean result = new DBImpl().buyGoodsById(goodsId, targetAmount);
		// 3. 调用页面goodsCata.jsp
		System.out.println(result + ":" + ty);
		request.getRequestDispatcher("goodCata.jsp?type=" + ty+"&result="+result)
						.forward(request, response);

	}

	private void showGoodsList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// model
		String type = request.getParameter("type");
		String dtype = URLDecoder.decode(URLDecoder.decode(type, "utf-8"), "utf-8");
		DBDao dao = new DBImpl();
		List<GoodsInfo> lst = dao.getGoodsListByType(dtype);
		// 将lst数据放到session中，
		request.getSession().setAttribute("GoodsListByType", lst);
		// 重定向方式调用jsp页面
		response.sendRedirect("goodsList.jsp");
	}

	private void regedit(HttpServletRequest request, HttpServletResponse response) {

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1 getData
		String userCode = request.getParameter("userCode");
		String userPwd = request.getParameter("userPwd");
		userCode = new String(userCode.getBytes("ISO-8859-1"), "UTF-8");
		// userCode = new String(userCode.getBytes("ISO-8859-1"),"UTF-8");

		System.out.println(userCode + ", " + userPwd);
		// jdbc
		CustomerInfo ci = new DBImpl().Login(userCode, userPwd);
		if (ci != null) {
			Cookie ck = new Cookie("name", URLEncoder.encode(ci.getName(), "UTF-8"));
			response.addCookie(ck);
			if (ci.getAddress() != null) {
				Cookie ck2 = new Cookie("address", URLEncoder.encode(ci.getAddress(), "UTF-8"));
				response.addCookie(ck2);
			}
			if (ci.getCallPhone() != null) {
				Cookie ck3 = new Cookie("callPhone", URLEncoder.encode(ci.getCallPhone(), "UTF-8"));
				response.addCookie(ck3);
			}
			Cookie ck4 = new Cookie("userId", URLEncoder.encode(ci.getUserId() + "", "UTF-8"));
			response.addCookie(ck4);
			
			Cookie ck5 = new Cookie("email", URLEncoder.encode(ci.getEmail(),"UTF-8"));
			response.addCookie(ck5);
			response.sendRedirect("success.html");
		} else {
			response.sendRedirect("faild.html");
		}

	}

	private void deleteMyCartGoods(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String goodsId = request.getParameter("goodsId");
		String orderAmount = request.getParameter("orderAmount");
		
		new DBImpl().deleteMyCartGoods(goodsId, orderAmount);
		response.sendRedirect("shoppingCar.jsp");
	}
	
	private void login1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1 getData
		String userCode = request.getParameter("userCode");
		String userPwd = request.getParameter("userPwd");
		userCode = new String(userCode.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(userCode+userPwd);

		
		CustomerInfo ci = new DBImpl().Login1(userCode, userPwd);
		if (ci != null) {
			Cookie ck = new Cookie("name", URLEncoder.encode(ci.getName(), "UTF-8"));
			response.addCookie(ck);
			if (ci.getAddress() != null) {
				Cookie ck2 = new Cookie("address", URLEncoder.encode(ci.getAddress(), "UTF-8"));
				response.addCookie(ck2);
			}
			if (ci.getCallPhone() != null) {
				Cookie ck3 = new Cookie("callPhone", URLEncoder.encode(ci.getCallPhone(), "UTF-8"));
				response.addCookie(ck3);
			}
			Cookie ck4 = new Cookie("userId", URLEncoder.encode(ci.getUserId() + "", "UTF-8"));
			response.addCookie(ck4);
			Cookie ck5 = new Cookie("email", URLEncoder.encode(ci.getEmail(),"UTF-8"));
			response.addCookie(ck5);
				response.sendRedirect("left.jsp");
	
		}else{
			response.sendRedirect("failed1.jsp");
		}
	
	}

}
