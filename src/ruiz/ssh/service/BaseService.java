package ruiz.ssh.service;

import java.util.List;
import ruiz.ssh.model.Base;
import ruiz.ssh.model.BaseGoods;
import ruiz.ssh.model.Material;

public interface BaseService {

    public boolean addBase(Base base);
    public boolean deleteBase(int id);
    public boolean updateBase(Base base);
    public List<Base> queryBase(Base base);
    public List<Base> queryById(int id);
    public Base query(int id);
    public List<Base> queryAll();
    public boolean updatexy(Base base);
    public Base tbadd(String name, int area, int x, int y);
	public boolean tbupdate(int id, String name, int area, int x, int y);
	public List<Base> match(String s);
}
