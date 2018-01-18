package ruiz.ssh.dao;

import java.util.List;

import ruiz.ssh.model.Material;
import ruiz.ssh.model.Ship;
public interface ShipDao {
	public List<Ship> queryById(int id);
    public boolean add(Ship obj);
    public boolean update(Ship obj);
    public boolean delete(int id);
    public List<Ship> getAll();
    public List<Ship> match(String s);
    
    public Ship tbadd(String name,String seazone,String nation,String port,
    		String info,String image, int vcap,int wcap, int cost);
    public boolean tbupdate(int id,String name,String seazone,String nation,String port,
    		String info,String image, int vcap,int wcap, int cost);

}
