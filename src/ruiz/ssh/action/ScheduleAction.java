package ruiz.ssh.action;

import java.util.Map;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ruiz.ssh.model.AvlShip;
import ruiz.ssh.model.Base;
import ruiz.ssh.model.BaseGoods;
import ruiz.ssh.model.ListItem;
import ruiz.ssh.service.AvlShipService;
import ruiz.ssh.service.BaseGoodsService;
import ruiz.ssh.service.BaseService;
import ruiz.ssh.service.ListItemService;
import ruiz.ssh.utils.*;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.BufferedReader;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;  
import java.net.HttpURLConnection;  
import java.net.MalformedURLException;  
import java.net.URL;  
import java.text.SimpleDateFormat;  
import java.util.Date; 

public class ScheduleAction {
	private static final long serialVersionUID = 1L;
	//计算模块地址
	public static final String ADD_URL = "http://localhost:14567/do_sim";  
	//调度id
	private Map<String, Object> session;
	private int schid;
	//物资清单
	private ListItem listitem;
	private List<ListItem> listitems;
	private ListItemService listitemService;
	//搜索匹配字符串
	private String s;
	//返回的JSON对象
	private Map<String,Object> dataMap;
	private List<Map<String,Object>> list;
	//可用船舶
	private AvlShipService avlshipService;
	private List<Map<String,Object>> avlships;
	//基地存储情况
	private BaseGoodsService basegoodsService;
	private List<Map<String,Object>> basegoodslist;
	private List<Map<String,Object>> goodslist;
	//基地
	private BaseService baseService;
	//跨域访问
	public void setheader() {
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
	}
	
	//按调度id查找调度
	public String querybyschid() {
		setheader();
		return "success";
	}
	//获取请求中的POST数据
	private String getRequestPostData(HttpServletRequest request) throws IOException {
		int contentLength = request.getContentLength();
		if(contentLength<0){
			return null;
			}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength;) {
			int len = request.getInputStream().read(buffer, i, contentLength - i);
			if (len == -1) {
				break;
			}
			i += len;
		}
		return new String(buffer, "utf-8");
	}
	//解析JSON
	public String parse() throws IOException {
		String schid =null;
		int basenum=1;
		Long time=System.currentTimeMillis();
		schid= time.toString();
		//设置response符合angular要求
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", " Origin, X-Requested-With, Content-Type, Accept");
		
		//判断接收到的请求的Content-Type , 是否是JSON
		HttpServletRequest request = ServletActionContext.getRequest();
		String contentType = request.getHeader("content-type");
		if ((contentType != null) && contentType.equalsIgnoreCase("application/json;charset=UTF-8")) {
			//得到POST中的JSON字符串 ，解析为JSON对象
			String rawText = getRequestPostData(request);
			//打印收到的JSON字符串
			System.out.println(rawText);
			JSONObject jsonObj = JSON.parseObject(rawText);
			//解析基地位置
			JSONArray basejson = jsonObj.getJSONArray("base");
			List<Base> bases = JSON.parseArray(basejson.toJSONString(), Base.class);
			basenum=bases.size();
			for (Base base : bases) {
				baseService.updatexy(base);
			}
			//解析所需物资
			JSONArray materialjson = jsonObj.getJSONArray("material");
			List<ListItem> items = JSON.parseArray(materialjson.toJSONString(), ListItem.class);
			for (ListItem item : items) {
				item.setSchid(schid);
				listitemService.addListItem(item);
			}
			//解析可用船舶
			JSONArray shipjson = jsonObj.getJSONArray("ship");
			List<AvlShip> ships = JSON.parseArray(shipjson.toJSONString(), AvlShip.class);
			for (AvlShip ship : ships) {
				ship.setSchid(schid);
				avlshipService.addAvlShip(ship);
			}
		}
		String no = solve(schid,basenum);
		// return "success";
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(no);// 返回的字符串数据
		return null;
	}
	
	public String solve(String schid,int basenum)
	{
		//根据调度ID来获取可用船舶 
		avlships=	avlshipService.querybyschid(schid);	
		//获取物资的参数
		goodslist = basegoodsService.querygoods();
		
		StringBuffer basegoodsstring =new StringBuffer();
		basegoodsstring.append(",\"bases\":[");
		Map<String,Object> basegoods=  new HashMap<String, Object>();
		for(int i=1;i<=basenum;i++)
		{
			JSONObject jsonbasegoods = new JSONObject();
			jsonbasegoods.put("id", i);
			jsonbasegoods.put("x", baseService.query(i).getX());
			jsonbasegoods.put("y", baseService.query(i).getY());
			basegoodslist = basegoodsService.querybybaseid(i);
			jsonbasegoods.put("goods", basegoodsService.querybybaseid(i));
			basegoodsstring.append(jsonbasegoods.toJSONString());
			if(i!=basenum) basegoodsstring.append(",");
		}
		basegoodsstring.append("]");
		//System.out.println(basegoodsstring.toString());
		
		//平台 清单
		Map<String,Object> platform = new HashMap<String, Object>();
		JSONObject jsonplatform = new JSONObject();
		list = listitemService.queryBySchid(schid);
		platform.put("id", 1); 
		platform.put("require_pack", list);
		platform.put("x",100); 
		platform.put("y",200); 
		//System.out.println(jsonplatform.toJSONString());
		
		JSONObject jsonObject = new JSONObject(); 
		//jsonObject.put(null, ships);
		jsonObject.put("ships", avlships);
		jsonObject.put("goods", goodslist);
		jsonObject.put("platform", platform);
		String json = jsonObject.toJSONString();
		json=json.substring(0, json.length()-1);
		String requestjson =json+basegoodsstring.toString()+
				",\"task\":{\"report_name\": \"第一次仿真\",\"excutor\": \"王二\",\"description\": \"本次仿真主要目的是测试系统的基本运行情况，收集测试数据，为完善系统功能提供依据测试人王二有着多年的仿真系统测试经验，认真负责\"}"
				+"}";
		System.out.println(requestjson);
		String no=null;
		try{  
			
	            //创建连接  
	            URL url = new URL(ADD_URL);  
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
	            connection.setDoOutput(true);  
	            connection.setDoInput(true);  
	            connection.setRequestMethod("POST");  
	            connection.setUseCaches(false);  
	            connection.setInstanceFollowRedirects(true);  
	              
	            //connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
	            connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");         
	              
	            connection.connect();  
	  
	            //POST请求  
	            DataOutputStream out = new DataOutputStream(connection.getOutputStream());  
	           
	            //out.writeBytes(obj.toString());//这个中文会乱码  
	            out.write(requestjson.getBytes("UTF-8"));//这样可以处理中文乱码问题  
	            out.flush();  
	            out.close();  
	              
	            //读取响应  
	            BufferedReader reader = new BufferedReader(new InputStreamReader(  
	                    connection.getInputStream()));  
	            String lines;  
	            StringBuffer sb = new StringBuffer("");  
	            while ((lines = reader.readLine()) != null) {  
	                lines = new String(lines.getBytes(), "utf-8");  
	                sb.append(lines);  
	            }  
	            System.out.println(sb); 
	            JSONObject jsonObj = JSON.parseObject(sb.toString());  
	             no = jsonObj.getString("no");  
	            reader.close();  
	            // 断开连接  
	            connection.disconnect();  
	        } catch (MalformedURLException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (UnsupportedEncodingException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
		return no;
	}
	
	
	
	//getter和setter
	public List<Map<String, Object>> getGoodslist() {
		return goodslist;
	}
	public void setGoodslist(List<Map<String, Object>> goodslist) {
		this.goodslist = goodslist;
	}
	public BaseGoodsService getBasegoodsService() {
		return basegoodsService;
	}
	public void setBasegoodsService(BaseGoodsService basegoodsService) {
		this.basegoodsService = basegoodsService;
	}
	public List<Map<String, Object>> getBasegoodslist() {
		return basegoodslist;
	}
	public void setBasegoodslist(List<Map<String, Object>> basegoodslist) {
		this.basegoodslist = basegoodslist;
	}
	public AvlShipService getAvlshipService() {
		return avlshipService;
	}
	public void setAvlshipService(AvlShipService avlshipService) {
		this.avlshipService = avlshipService;
	}
	
	public List<Map<String, Object>> getAvlships() {
		return avlships;
	}

	public void setAvlships(List<Map<String, Object>> avlships) {
		this.avlships = avlships;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

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
	public int getSchid() {
		return schid;
	}
	public void setSchid(int schid) {
		this.schid = schid;
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
}

