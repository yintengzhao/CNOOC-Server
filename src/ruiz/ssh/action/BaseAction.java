package ruiz.ssh.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import ruiz.ssh.model.Base;
import ruiz.ssh.model.Material;
import ruiz.ssh.service.BaseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private int area;
	private int x;
	private int y;
	private String s;

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	private Base base;
	private List<Base> baselist;
	private BaseService baseService;
	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String,Object> dataMap; 
	
	private Map<String,Object> updateMap; 
	private Map<String,Object> addMap; 
	
	
	public Map<String, Object> getUpdateMap() {
		return updateMap;
	}

	public void setUpdateMap(Map<String, Object> updateMap) {
		this.updateMap = updateMap;
	}

	public Map<String, Object> getAddMap() {
		return addMap;
	}

	public void setAddMap(Map<String, Object> addMap) {
		this.addMap = addMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }  
	
	public List<Base> getBaselist() {
		return baselist;
	}
	public void setBaselist(List<Base> baselist) {
		this.baselist = baselist;
	}
	public Base getBase() {
		return base;
	}
	public void setBase(Base base) {
		this.base = base;
	}
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	// 跨域访问
	public void setheader() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
	}
		
	public String list() {
		request = (Map) ActionContext.getContext().get("request");
		request.put("bases", baseService.queryAll());
		baselist=baseService.queryAll();
		dataMap = new HashMap<String, Object>();  
        // 放入一个是否操作成功的标识  
        dataMap.put("Result", "OK");   
        dataMap.put("Records", baselist); 
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
		return "success";
	}

	public String deleteBase() {
		setheader();
		baseService.deleteBase(base.getId());
		return list();
	}

	public String addBase() {
		setheader();
		baseService.addBase(base);
		return list();
	}

	public String updateBase() {
		setheader();
		base = baseService.query(base.getId());
		return "update";
	}

	public String updateBaseImpl() {
		setheader();
		baseService.updateBase(base);
		return list();
	}

	public String  queryBase() {
		setheader();
		request = (Map) ActionContext.getContext().get("request");
		request.put("bases1", baseService.queryBase(base));
		request.put("tag", 1);
		return list();
	}
	
	public String query() {

		baselist = baseService.queryById(base.getId());
		// ship.setImage(shipService.queryById(ship.getId()).getImage());
		// ship.setInfo("sssss");
		setheader();
		return "success";
	}
	public String tbadd() {
		setheader();
		Base b = baseService.tbadd(name,area, x, y);
		addMap = new HashMap<String, Object>();  
        // 放入一个是否操作成功的标识  
		addMap.put("Result", "OK");   
		addMap.put("Record", b);  
		return list();
	}
	public String tbupdate() {
		setheader();
		updateMap = new HashMap<String, Object>();  
        // 放入一个是否操作成功的标识  
		
        if(baseService.tbupdate(id, name, area, x, y))
        	updateMap.put("Result", "OK");   
		return list();
	}
	
	public String tbdelete() {
		setheader();
		dataMap = new HashMap<String, Object>(); 
		if(baseService.deleteBase(id))
        	dataMap.put("Result", "OK");   
		return list();
	}
	
	public String match()
	{
		setheader();
		baselist = baseService.match(s);
		return "success";
	}
}
