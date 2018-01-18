package ruiz.ssh.service;

import java.util.List;
import ruiz.ssh.dao.BaseDao;
import ruiz.ssh.model.Base;
import ruiz.ssh.model.BaseGoods;
import ruiz.ssh.model.Material;

public class BaseServiceImpl implements BaseService {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public boolean addBase(Base base) {

		return baseDao.addBase(base);
	}

	@Override
	public boolean deleteBase(int id) {

		return baseDao.deleteBase(id);
	}

	@Override
	public boolean updateBase(Base base) {

		return baseDao.updateBase(base);
	}

	@Override
	public List<Base> queryBase(Base base) {

		return baseDao.queryBase(base);
	}

	@Override
	public List<Base> queryById(int id) {

		return baseDao.queryById(id);
	}

	@Override
	public List<Base> queryAll() {

		return baseDao.queryAll();
	}

	@Override
	public Base query(int id) {
		
		return baseDao.query(id);
	}

	@Override
	public List<Base> match(String s) {
		
		return baseDao.match(s);
	}

	@Override
	public boolean updatexy(Base base) {
		int x=base.getX();
		int y=base.getY();
		int id = base.getId();
		System.out.println(x+" "+y+" "+id);
		baseDao.updatexy(id, x, y);
		return true;
	}

	@Override
	public Base tbadd(String name, int area, int x, int y) {
		
		return baseDao.tbadd(name, area, x, y);
	}

	@Override
	public boolean tbupdate(int id, String name, int area, int x, int y) {
		
		return baseDao.tbupdate(id, name, area, x, y);
	}
	
}
