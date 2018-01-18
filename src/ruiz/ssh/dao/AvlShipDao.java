package ruiz.ssh.dao;

import java.util.List;
import java.util.Map;

import ruiz.ssh.model.AvlShip;

public interface AvlShipDao {
	  //public boolean checkAvl(User user);
    
	    public boolean addAvlShip(AvlShip avlship);
	    
	    public boolean deleteAvlShip(int id);
	    
	    public boolean updateAvlShip(AvlShip avlship);

	  //public List<AvlShip> queryUser(AvlShip user);
	    
	    public AvlShip queryById(int id);

	    public List<AvlShip> queryAll();
	    
	    public List<Map<String, Object>> querybyschid(String schid);
}
