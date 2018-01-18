package ruiz.ssh.dao;

import ruiz.ssh.model.Weather;
import java.util.List;

public interface WeatherDao {
	public Weather queryById(int id);
    public boolean add(Weather obj);
    public boolean update(Weather obj);
    public boolean delete(int i);
    public List<Weather> getAll();
    public List<Weather> match(String s);
}
