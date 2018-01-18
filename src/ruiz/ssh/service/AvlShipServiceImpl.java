package ruiz.ssh.service;

import java.util.List;
import java.util.Map;

import ruiz.ssh.dao.AvlShipDao;
import ruiz.ssh.model.AvlShip;

public class AvlShipServiceImpl implements AvlShipService {

	private AvlShipDao avlshipDao;
	
	public AvlShipDao getAvlshipDao() {
		return avlshipDao;
	}

	public void setAvlshipDao(AvlShipDao avlshipDao) {
		this.avlshipDao = avlshipDao;
	}

	@Override
	public boolean addAvlShip(AvlShip avlship) {

		return avlshipDao.addAvlShip(avlship);
	}

	@Override
	public boolean deleteAvlShip(int id) {

		return avlshipDao.deleteAvlShip(id);
	}

	@Override
	public boolean updateAvlShip(AvlShip avlship) {

		return avlshipDao.updateAvlShip(avlship);
	}

	@Override
	public AvlShip queryById(int id) {

		return avlshipDao.queryById(id);
	}

	@Override
	public List<AvlShip> queryAll() {

		return avlshipDao.queryAll();
	}

	@Override
	public List<Map<String, Object>> querybyschid(String schid) {
		
		return avlshipDao.querybyschid(schid);
	}

	
}
