package ruiz.ssh.dao;

import ruiz.ssh.model.Material;
import java.util.List;

public interface MaterialDao {
	public List<Material> queryById(int id);
    public boolean add(Material obj);
    public boolean update(Material obj);
    public boolean delete(int id);
    public List<Material> getAll();
    public List<Material> match(String s);
    
    public Material tbadd(String name,String type,String detail,int weight, int volume);
    public boolean tbupdate(int id,String name,String type,String detail,int weight, int volume);
}
