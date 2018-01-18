package ruiz.ssh.service;

import java.util.List;
import ruiz.ssh.model.Weather;

public interface WeatherService {
    public boolean addWeather(Weather weather);
    public boolean deleteWeather(int id);
    public boolean updateWeather(Weather weather);
    public Weather queryById(int id);
    public List<Weather> queryAll();	
}
