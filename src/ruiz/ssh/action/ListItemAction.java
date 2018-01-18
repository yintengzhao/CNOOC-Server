package ruiz.ssh.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import java.util.HashMap;
import java.util.List;

import ruiz.ssh.model.ListItem;
import ruiz.ssh.service.ListItemService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListItemAction {
	private static final long serialVersionUID = 1L;
	private int schid;
	public int getSchid() {
		return schid;
	}
	public void setSchid(int schid) {
		this.schid = schid;
	}

	private ListItem listitem;
	private List<ListItem> listitems;
	private ListItemService listitemService;
	private Map<String, Object> request;
	private Map<String, Object> session;
	private String s;	
	private Map<String,Object> dataMap;
	
	
	public ListItem getListitem() {
		return listitem;
	}
	public void setListitem(ListItem listitem) {
		this.listitem = listitem;
	}
	public List<ListItem> getListitems() {
		return listitems;
	}
	public void setListitems(List<ListItem> listitems) {
		this.listitems = listitems;
	}
	public ListItemService getListitemService() {
		return listitemService;
	}
	public void setListitemService(ListItemService listitemService) {
		this.listitemService = listitemService;
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
			dataMap.put("Records", listitems); 
		}
		public String list() {
			listitems=listitemService.queryAll();
			//制作JSON数据
			json();  
			//跨域访问
			setheader();
			return "success";
		}
		
		public String delete() {
			setheader();
			listitemService.deleteListItem(listitem.getId());
			return list();
		}
		
		public String add() {
			setheader();
			listitemService.addListItem(listitem);
			return list();
		}
		
		public String update() {
			setheader();
			listitemService.updateListItem(listitem);
			return list();
		}
		
		public String query() {
			setheader();
			listitem = listitemService.queryById(listitem.getId());
			return list();
		}
		
//		public String querybyschid() {
//			setheader();
//			listitems = listitemService.queryBySchid(this.getSchid());
//			return "success";
//		}
}
