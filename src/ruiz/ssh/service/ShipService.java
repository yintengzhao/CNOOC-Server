package ruiz.ssh.service;

import java.util.List;
import ruiz.ssh.model.Ship;

public interface ShipService {
	
	//public boolean checkUser(User user);
//    public boolean addShip(
//    	String ship_name,String seazone,
//        String nation , String port,
//        String info, String image);
	public boolean addShip(Ship ship);
    public boolean deleteShip(int id);
    public boolean updateShip(Ship ship);
    //public List<User> queryUser(User user);
    public List<Ship> queryById(int id);
    public List<Ship> queryAll();
    public List<Ship> match(String s);
    
    public Ship tbadd(String name,String seazone,String nation,String port,
    		String info,String image, int vcap,int wcap, int cost);
    public boolean tbupdate(int id,String name,String seazone,String nation,String port,
    		String info,String image, int vcap,int wcap, int cost);
    
}
