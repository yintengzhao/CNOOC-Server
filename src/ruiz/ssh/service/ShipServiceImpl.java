package ruiz.ssh.service;

import java.util.List;
import ruiz.ssh.dao.ShipDao;
import ruiz.ssh.model.Ship;

public class ShipServiceImpl implements ShipService {
	
	private ShipDao shipDao;
	
	public ShipDao getShipDao() {
		return shipDao;
	}
	
	public void setShipDao(ShipDao shipDao) {
		this.shipDao = shipDao;
	}
	@Override
//	public boolean addShip(
//	    	String ship_name,String seazone,
//	        String nation , String port,
//	        String info, String image) {
//		Ship ship = new Ship(ship_name, seazone, nation, port, info, image);
//		return shipDao.add(ship);
//	}

	public boolean addShip(Ship ship) {
		
		return shipDao.add(ship);
	}
	
	@Override
	public boolean deleteShip(int id) {

		return shipDao.delete(id);
	}

	@Override
	public boolean updateShip(Ship ship) {

		return shipDao.update(ship);
	}

	@Override
	public List<Ship> queryById(int id) {

		return shipDao.queryById(id);
	}

	@Override
	public List<Ship> queryAll() {

		return shipDao.getAll();
	}
	
	@Override
	public List<Ship> match(String s){
		return shipDao.match(s);
	}

	@Override
	public Ship tbadd(String name, String seazone, String nation, String port, String info, String image, int vcap,
			int wcap, int cost) {
		
		return shipDao.tbadd(name, seazone, nation, port, info, image, vcap, wcap, cost);
	}

	@Override
	public boolean tbupdate(int id, String name, String seazone, String nation, String port, String info, String image,
			int vcap, int wcap, int cost) {
		
		return shipDao.tbupdate(id, name, seazone, nation, port, info, image, vcap, wcap, cost);
	}
	
}
