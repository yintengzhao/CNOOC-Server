package ruiz.ssh.service;

import java.util.List;
import java.util.Map;

import ruiz.ssh.model.AvlShip;

public interface AvlShipService {
	
	//public boolean checkAvlShip(User user);
    public boolean addAvlShip(AvlShip avlship);
    public boolean deleteAvlShip(int id);
    public boolean updateAvlShip(AvlShip avlship);
    //public List<User> queryUser(User user);
    public AvlShip queryById(int id);
    public List<AvlShip> queryAll();
    public List<Map<String, Object>> querybyschid(String schid);
}
