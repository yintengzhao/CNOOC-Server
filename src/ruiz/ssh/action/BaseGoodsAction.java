package ruiz.ssh.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import java.util.HashMap;
import java.util.List;
import ruiz.ssh.model.BaseGoods;
import ruiz.ssh.service.BaseGoodsService;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseGoodsAction {
	private static final long serialVersionUID = 1L;

	private BaseGoods basegoods;
	private List<BaseGoods> basegoodslist;
	private BaseGoodsService basegoodsService;
	private Map<String, Object> request;
	private Map<String, Object> session;
	
	private int id;
    private int baseid;
    private int materialid;
    private int amount;
    private int basegoodsid;
    
	public int getBasegoodsid() {
		return basegoodsid;
	}
	public void setBasegoodsid(int basegoodsid) {
		this.basegoodsid = basegoodsid;
	}
	public int getBaseid() {
		return baseid;
	}
	public void setBaseid(int baseid) {
		this.baseid = baseid;
	}
	public int getMaterialid() {
		return materialid;
	}
	public void setMaterialid(int materialid) {
		this.materialid = materialid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	private Map<String,Object> dataMap; 
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	private String s;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BaseGoods getBasegoods() {
		return basegoods;
	}
	public void setBasegoods(BaseGoods basegoods) {
		this.basegoods = basegoods;
	}
	public List<BaseGoods> getBasegoodslist() {
		return basegoodslist;
	}
	public void setBasegoodslist(List<BaseGoods> basegoodslist) {
		this.basegoodslist = basegoodslist;
	}
	public BaseGoodsService getBasegoodsService() {
		return basegoodsService;
	}
	public void setBasegoodsService(BaseGoodsService basegoodsService) {
		this.basegoodsService = basegoodsService;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	//øÁ”Ú∑√Œ 
	public void setheader() {
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
	}
	public String list() {
		basegoodslist=basegoodsService.queryAll();
		
		//øÁ”Ú∑√Œ 
		setheader();
		return "success";
	}
	
	public String delete() {
		setheader();
		basegoodsService.deleteBaseGoods(basegoods.getId());
		return list();
	}
	
	public String add() {
		setheader();
		basegoodsService.addBaseGoods(basegoods);
		return list();
	}
	
	public String update() {
		setheader();
		basegoodsService.updateBaseGoods(basegoods);
		return list();
	}
	
	public String query() {
//		setheader();
//		basegoods = basegoodsService.queryById(basegoods.getId());
		return list();
	}
	
	public String tbquery() {
		setheader();
		dataMap = new HashMap<String, Object>(); 
		dataMap.put("Result", "OK");
		dataMap.put("Records",basegoodsService.tbqueryByBaseId(id));
		return list();
	}
	public String tbdelete() {
		setheader();
		dataMap = new HashMap<String, Object>(); 
		if(basegoodsService.deleteBaseGoods(basegoodsid))
			dataMap.put("Result", "OK");
		return list();
	}
	public String tbadd() {
		setheader();
		dataMap = new HashMap<String, Object>(); 
		dataMap.put("Result", "OK");
		dataMap.put("Record",basegoodsService.tbadd(materialid, amount, id));
		return list();
	}
	public String tbupdate() {
		setheader();
		dataMap = new HashMap<String, Object>(); 
		if(basegoodsService.tbupdate(materialid, amount, basegoodsid))
			dataMap.put("Result", "OK");
		return list();
	}
	
}
