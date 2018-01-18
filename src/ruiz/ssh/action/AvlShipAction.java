package ruiz.ssh.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import java.util.HashMap;
import java.util.List;
import ruiz.ssh.model.AvlShip;
import ruiz.ssh.service.AvlShipService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AvlShipAction {
	private static final long serialVersionUID = 1L;

	private AvlShip avlship;
	private List<AvlShip> avlships;
	private AvlShipService avlshipService;
	private Map<String, Object> request;
	private Map<String, Object> session;
	private String s;
	
	private Map<String,Object> dataMap; 
	
	public AvlShip getAvlship() {
		return avlship;
	}
	public void setAvlship(AvlShip avlship) {
		this.avlship = avlship;
	}
	public List<AvlShip> getAvlships() {
		return avlships;
	}
	public void setAvlships(List<AvlShip> avlships) {
		this.avlships = avlships;
	}
	public AvlShipService getAvlshipService() {
		return avlshipService;
	}
	public void setAvlshipService(AvlShipService avlshipService) {
		this.avlshipService = avlshipService;
	}
	public Map<String, Object> getRequest() {
		return request;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}  
	//跨域访问
	public void setheader() {
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
	}
	//制作JSON数据
	public void json() {
		dataMap = new HashMap<String, Object>();  
		dataMap.put("Result", "OK");   
		dataMap.put("Records", avlships); 
	}
	public String list() {
		avlships=avlshipService.queryAll();
		//制作JSON数据
		json();  
		//跨域访问
		setheader();
		return "success";
	}
	
	public String delete() {
		setheader();
		avlshipService.deleteAvlShip(avlship.getId());
		return list();
	}
	
	public String add() {
		setheader();
		avlshipService.addAvlShip(avlship);
		return list();
	}
	
	public String update() {
		setheader();
		avlshipService.updateAvlShip(avlship);
		return list();
	}
	
	public String query() {
		setheader();
		avlship = avlshipService.queryById(avlship.getId());
		return list();
	}
	
}
