package ruiz.ssh.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import java.util.HashMap;
import java.util.List;

import ruiz.ssh.dao.MaterialDao;
import ruiz.ssh.model.Material;
import ruiz.ssh.service.MaterialService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MaterialAction {
	private static final long serialVersionUID = 1L;
	
	private Material material;
	private List<Material> materials;
	private MaterialService materialService;
	private Map<String, Object> request;
	private Map<String, Object> session;
	private String s;
	
	//表格参数数
	private int id;
	private String name;
	private String type;
	private String detail;
	// private int amount;
	private int weight;
	private int volume;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private Map<String,Object> dataMap; 
	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }  
	
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public List<Material> getMaterials() {
		return materials;
	}
	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}
	public MaterialService getMaterialService() {
		return materialService;
	}
	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
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
	public String json() {  
        // dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据  
        materials=materialService.queryAll(); 
        dataMap = new HashMap<String, Object>();  
        // 放入一个是否操作成功的标识  
        dataMap.put("Result", "OK");   
        dataMap.put("Records", materials);  
        // 返回结果  
        HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
        return "success";  
    }  
	
	public String list() {
//		request = (Map) ActionContext.getContext().get("request");
//		request.put("materials", materialService.queryAll());
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
		materials=materialService.queryAll();
		return "success";
	}
	
	public String deleteMaterial() {
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
		materialService.deleteMaterial(material.getId());
		return list();
	}

	public String addMaterial() {
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
		//if(material.getId()==0) material.setId(100);
		
		materialService.addMaterial(material);
		dataMap = new HashMap<String, Object>();  
        // 放入一个是否操作成功的标识  
        dataMap.put("Result", "OK");   
        dataMap.put("Records", material);  
		return list();
	}
	
	public String tbaddMaterial() {
		setheader();
		Material m = materialService.tbadd(name, type, detail, weight, volume);
		dataMap = new HashMap<String, Object>();  
        // 放入一个是否操作成功的标识  
        dataMap.put("Result", "OK");   
        dataMap.put("Record", m);  
		return list();
	}
	public String tbupdateMaterial() {
		setheader();
		dataMap = new HashMap<String, Object>();  
        // 放入一个是否操作成功的标识  
        if(materialService.tbupdate(id, name, type, detail, weight, volume))
        	dataMap.put("Result", "OK");   
		return list();
	}
	
	public String tbdeleteMaterial() {
		setheader();
		dataMap = new HashMap<String, Object>(); 
		if(materialService.deleteMaterial(id))
        	dataMap.put("Result", "OK");   
		return list();
	}

	public String updateMaterial() {
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
		material = materialService.queryById(material.getId()).get(0);
		return "update";
	}

	public String updateMaterialImpl() {
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
		materialService.updateMaterial(material);
		dataMap = new HashMap<String, Object>();  
        // 放入一个是否操作成功的标识  
        dataMap.put("Result", "OK");   
        dataMap.put("Records", material);  
		return list();
	}
//	
	public String query() {
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
		//materials = materialService.queryById(material.getId());
		
		materials = materialService.queryById(this.getId());
		return "success";
	}
	
	public String match() {
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
		materials = materialService.match(s);
		System.out.println(s);
		return "success";
	}
	
}
