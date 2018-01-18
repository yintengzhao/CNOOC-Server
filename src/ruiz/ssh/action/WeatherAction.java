package ruiz.ssh.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import ruiz.ssh.model.Weather;
import ruiz.ssh.service.WeatherService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class WeatherAction {
	private static final long serialVersionUID = 1L;

	private Weather weather;
	private List<Weather> weatherlist;
	private WeatherService weatherService;
	private Map<String, Object> request;
	private Map<String, Object> session;
	
	
	public List<Weather> getWeatherlist() {
		return weatherlist;
	}
	public void setWeatherlist(List<Weather> weatherlist) {
		this.weatherlist = weatherlist;
	}
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	public WeatherService getWeatherService() {
		return weatherService;
	}
	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
	
	public String list() {
		request = (Map) ActionContext.getContext().get("request");
		request.put("weathers", weatherService.queryAll());
		weatherlist=weatherService.queryAll();
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
		return "success";
	}

	public String deleteWeather() {
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
		weatherService.deleteWeather(weather.getId());
		return list();
	}

	public String addWeather() {
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
		weatherService.addWeather(weather);
		return list();
	}

//	public String updateWeather() {
//		HttpServletResponse response = ServletActionContext.getResponse();  
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		weather = weatherService.queryById(weather.getId());
//		return "update";
//	}

	public String updateWeatherImpl() {
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setHeader("Access-Control-Allow-Origin", "*");
		weatherService.updateWeather(weather);
		return list();
	}
	
	public String queryWeather() {
		weather = weatherService.queryById(weather.getId());
		return list();
	}
}
