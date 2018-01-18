package ruiz.ssh.service;

import java.util.List;
import ruiz.ssh.dao.WeatherDao;
import ruiz.ssh.model.Weather;

public class WeatherServiceImpl implements WeatherService {
	
	private WeatherDao weatherDao;
	
	public WeatherDao getWeatherDao() {
		return weatherDao;
	}

	public void setWeatherDao(WeatherDao weatherDao) {
		this.weatherDao = weatherDao;
	}

	@Override
	public boolean addWeather(Weather weather) {

		return weatherDao.add(weather);
	}

	@Override
	public boolean deleteWeather(int id) {

		return weatherDao.delete(id);
	}

	@Override
	public boolean updateWeather(Weather weather) {

		return weatherDao.update(weather);
	}

	@Override
	public Weather queryById(int id) {
		
		return weatherDao.queryById(id);
	}

	@Override
	public List<Weather> queryAll() {

		return weatherDao.getAll();
	}

}
