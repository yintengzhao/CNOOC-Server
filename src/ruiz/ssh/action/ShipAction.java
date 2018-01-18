package ruiz.ssh.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import java.util.HashMap;
import java.util.List;

import ruiz.ssh.model.Material;
import ruiz.ssh.model.Ship;
import ruiz.ssh.service.ShipService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ShipAction {
	private static final long serialVersionUID = 1L;
	
	private Ship ship;
	private List<Ship> ships;
	private ShipService shipService;
	private Map<String, Object> request;
	private Map<String, Object> session;
	private String s;
	
	private int id;
    private String name;
    private String seazone;
    private String nation;
    private String port;
    private String info;
    private String image;
    private int vcap;
    private int wcap;
    private int cost;
    private Map<String,Object> addmap; 
    private Map<String,Object> updatemap; 
    
	public Map<String, Object> getUpdatemap() {
		return updatemap;
	}
	public void setUpdatemap(Map<String, Object> updatemap) {
		this.updatemap = updatemap;
	}
	public Map<String, Object> getAddmap() {
		return addmap;
	}
	public void setAddmap(Map<String, Object> addmap) {
		this.addmap = addmap;
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
	public String getSeazone() {
		return seazone;
	}
	public void setSeazone(String seazone) {
		this.seazone = seazone;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getVcap() {
		return vcap;
	}
	public void setVcap(int vcap) {
		this.vcap = vcap;
	}
	public int getWcap() {
		return wcap;
	}
	public void setWcap(int wcap) {
		this.wcap = wcap;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	private Map<String,Object> dataMap; 
	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }  
	public Ship getShip() {
		return ship;
	}
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	public List<Ship> getShips() {
		return ships;
	}
	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}
	public ShipService getShipService() {
		return shipService;
	}
	public void setShipService(ShipService shipService) {
		this.shipService = shipService;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	// 跨域访问
		public void setheader() {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setHeader("Access-Control-Allow-Origin", "*");
		}
	public String list() {
		
		request = (Map) ActionContext.getContext().get("request");
		request.put("ships", shipService.queryAll());
		ships=shipService.queryAll();
		
		dataMap = new HashMap<String, Object>();  
        // 放入一个是否操作成功的标识  
        dataMap.put("Result", "OK");   
        dataMap.put("Records", ships);  
        
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
		return "success";
	}
	
	public String tbaddShip() {
		setheader();
		Ship s = shipService.tbadd(name, seazone, nation, port, info, image, vcap, wcap, cost);
		addmap = new HashMap<String, Object>();  
        // 放入一个是否操作成功的标识  
		addmap.put("Result", "OK");   
		addmap.put("Record", s);  
		return list();
	}
	public String tbupdateShip() {
		setheader();
		updatemap = new HashMap<String, Object>();  
        // 放入一个是否操作成功的标识  
        if(shipService.tbupdate(id, name, seazone, nation, port, info, image, vcap, wcap, cost))
        	updatemap.put("Result", "OK");   
		return list();
	}
	
	public String tbdeleteShip() {
		setheader();
		dataMap = new HashMap<String, Object>(); 
		if(shipService.deleteShip(id))
        	dataMap.put("Result", "OK");   
		return list();
	}
	
	public String deleteShip() {
		setheader();
		shipService.deleteShip(ship.getId());
		return list();
	}

	public String addShip() {
		setheader();
		shipService.addShip(ship);
		return list();
	}

	public String updateShip() {
		setheader();
		ship = shipService.queryById(ship.getId()).get(0);
		return "update";
	}

	public String updateShipImpl() {
		setheader();
		shipService.updateShip(ship);
		return list();
	}
	
	public String query() {
		
		ships = shipService.queryById(ship.getId());
		//ship.setImage(shipService.queryById(ship.getId()).getImage());
		//ship.setInfo("sssss");
		setheader();
		return "success";
	}
//	public String getShip() {
//		request = (Map) ActionContext.getContext().get("request");
//		request.put("ship", shipService.queryShip(ship));
//		request.put("tag", 2);
//		return list();
//	}
	public String match() {
		 
		ships = shipService.match(s);
		setheader();
		return "success";
	}
}
