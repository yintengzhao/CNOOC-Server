package ruiz.ssh.service;

import java.util.List;

import ruiz.ssh.model.Material;

public interface MaterialService {
	public boolean addMaterial(Material ship);
    public boolean deleteMaterial(int id);
    public boolean updateMaterial(Material ship);
    //public List<User> queryUser(User user);
    public  List<Material>  queryById(int id);
    public List<Material> queryAll();
    public List<Material> match(String s);
    
    public Material tbadd(String name, String type, String detail, int weight, int volume);
	public boolean tbupdate(int id, String name, String type, String detail, int weight, int volume);
}
