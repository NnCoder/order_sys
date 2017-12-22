package com.order.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.order.model.Comment;
import com.order.service.CommentService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.order.model.Food;
import com.order.model.Ordeer;
import com.order.model.Shop;
import com.order.service.ShopService;
import com.order.util.FileUtils;

@Controller
@Scope("prototype")
public class ShopAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1679794536136521463L;
	@Autowired
	private ShopService shopService;
	@Autowired
	private CommentService commentService;
	private Map<String, Object> result;
	private Map<String, Object> session;
	private File img;
	private String imgFileName;
	private String imgFileContentType;
	private int status;
	private int order_type;
	private String msg;
	private double turnover;
	private int recentDay;
	private Shop shop;
	private List<Shop> shops;
	private List<Comment> comments;

	public String queryByKey(){

		shops = shopService.getByKey(msg);
		return SUCCESS;
	}

	/**
	 * 获取已注册商家 吴凯文
	 * @return
	 */
	public String queryAllRegi(){
		shops = shopService.getAll(1);
		return SUCCESS;
	}

	/**
	 * 获取未注册的商家 吴凯文
	 * @return
	 */
	public String queryAllUnRegi(){
		shops = shopService.getAll(0);
		return SUCCESS;
	}

	/**
	 * 更新商家状态：审核通过  吴凯文
	 * @return
	 */
	public String updateStatus(){
		int code = shopService.updateStatus(shop, status);
		result = new HashMap<String, Object>();
		result.put("code", code);
		return "json";
	}
	
	public String queryAll(){
		shops = shopService.getAll(status);
		return SUCCESS;
	}
	
	public String foods() {
		int id = (Integer) session.get("shop_id");

		shop = new Shop();
		shop.setId(id);
		Set<Food> foods = shopService.getFoods(shop);
		shop.setFoods(foods);
		return SUCCESS;
	}

	public String queryTurnover(){
		int id = (Integer) session.get("shop_id");
		shop = new Shop();
		shop.setId(id);
		Set<Ordeer> ordeerSet = shopService.getTurnover(shop,recentDay);
		for(Ordeer ordeer:ordeerSet){
			double money = Double.parseDouble(ordeer.getFood().getPrice());
			turnover+=money;
		}
		shop.setOrdeers(ordeerSet);
		return  SUCCESS;
	}

	public String ordersNot() {
		int id = (int) session.get("shop_id");
		shop = new Shop();
		shop.setId(id);
		Set<Ordeer> ordeers = shopService.getOrdeers(shop, 0,order_type);
		shop.setOrdeers(ordeers);
		return SUCCESS;
	}

	public String ordersAccess() {
		int id = (int) session.get("shop_id");
		shop = new Shop();
		shop.setId(id);
		Set<Ordeer> ordeers = shopService.getOrdeers(shop, 1,order_type);
		shop.setOrdeers(ordeers);
		return SUCCESS;
	}

	public String ordersBack() {
		int id = (int) session.get("shop_id");
		shop = new Shop();
		shop.setId(id);
		Set<Ordeer> ordeers = shopService.getOrdeers(shop, -1,order_type);
		shop.setOrdeers(ordeers);
		return SUCCESS;
	}

	public String ordersComplete() {
		int id = (int) session.get("shop_id");
		shop = new Shop();
		shop.setId(id);
		Set<Ordeer> ordeers = shopService.getOrdeers(shop, 99,order_type);
		shop.setOrdeers(ordeers);
		return SUCCESS;
	}

	public String orders() {
		int id = (int) session.get("shop_id");
		shop = new Shop();
		shop.setId(id);
		Set<Ordeer> ordeers = shopService.getOrdeers(shop, status,order_type);
		shop.setOrdeers(ordeers);
		return SUCCESS;
	}

	public String checkPhoneNum() {
		int code = 1;
		result = new HashMap<String, Object>();
		if (shopService.getByPhoneNum(shop) != null) {
			code = 0;
		}
		result.put("code", code);
		return "json";
	}
	
	public String modifyPsw(){
		int id = (int)session.get("shop_id");
		shop.setId(id);
		int code = shopService.modifyPsw(shop);
		result = new HashMap<String, Object>();
		result.put("code",code);
		return "json";
	}

	public String query() {
		int id = (int) session.get("shop_id");
		shop = new Shop();
		shop.setId(id);
		shop = shopService.get(shop);
		result = new HashMap<String, Object>();
		result.put("shop_id",shop.getId());
		result.put("shop_name", shop.getName());
		result.put("shop_imgSrc", shop.getImgSrc());
		result.put("shop_description", shop.getDescription());
		return "json";
	}

	public String updateOpen(){
		int id = (int) session.get("shop_id");
		shop.setId(id);
		shopService.updateOpen(shop.getId(),shop.getOpen());
		return "json";
	}

	public String queryForCustomer(){
		shop = shopService.get(shop);
		shop.setFoods(shopService.getFoods(shop));
		comments = commentService.getByShop(shop.getId());
		return SUCCESS;
	}

	public String updateInput() {
		int id = (int) session.get("shop_id");
		shop = new Shop();
		shop.setId(id);
		shop = shopService.get(shop);
		return SUCCESS;
	}

	public String update() throws IOException {
		// 获取上传图片
		/*String path = "/imgSrc/";
		String uploadPath = ServletActionContext.getServletContext().getInitParameter("uploadPath")+path;
		String accessPath = ServletActionContext.getServletContext().getInitParameter("accessPath")+path;
		File toFile = new File(uploadPath, imgFileName);
		File file = FileUtils.copyFileWithNoCheck(img, toFile);*/

		int shop_id = (int) session.get("shop_id");
		//String imgSrc = accessPath + file.getName();
		shop.setId(shop_id);
		//shop.setImgSrc(imgSrc);
		int code = shopService.update(shop);
		result = new HashMap<String, Object>();
		result.put("code",code);
		return "json";
	}

	/**
	 * 商家注册 周枫晴
	 * @return
	 * @throws IOException
	 */
	public String register() throws IOException {
		// 获取上传头像
		String path = "/imgSrc/";
		//上传路径与访问路径分离
		String uploadPath = ServletActionContext.getServletContext().getInitParameter("uploadPath")+path;
		String accessPath = ServletActionContext.getServletContext().getInitParameter("accessPath")+path;
		File toFile = new File(uploadPath, imgFileName);
		File file = FileUtils.copyFile(img, toFile);
		// 保存头像相对路径
		String imgSrc = accessPath + file.getName();
		shop.setImgSrc(imgSrc);
		// 注册
		int code = shopService.register(shop);
		result = new HashMap<String, Object>();
		result.put("code", code);
		return "json";
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	public List<Shop> getShops() {
		return shops;
	}

	public int getOrder_type() {
		return order_type;
	}

	public void setOrder_type(int order_type) {
		this.order_type = order_type;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	public int getRecentDay() {
		return recentDay;
	}

	public void setRecentDay(int recentDay) {
		this.recentDay = recentDay;
	}
}
