package ruiz.ssh.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ruiz.ssh.model.Material;
import ruiz.ssh.model.Ship;
import ruiz.ssh.model.Weather;

public class ShipDaoImpl implements ShipDao{
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public List<Ship> queryById(int id) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Ship m where id ="+id);
        List<Ship> ships = query.list();
        session.getTransaction().commit();
        session.close();
        return ships;
	}

	@Override
	public boolean add(Ship obj) {
		
		return false;
	}

	@Override
	public boolean update(Ship obj) {
		
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Ship s = (Ship)session.get(Ship.class, id);
        session.delete(s);
        session.getTransaction().commit();
        session.close();
        return true;
	}

	@Override
	public List<Ship> getAll() {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Ship m");
        List<Ship> ships = query.list();
        session.getTransaction().commit();
        session.close();
        return ships;
	}

	@Override
	public List<Ship> match(String s) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Ship t where t.name like '%"+s+"%'";
        Query query = session.createQuery(hql);
        List<Ship> objs= query.list();
        session.getTransaction().commit();
        session.close();
        return objs;
	}

	@Override
	public Ship tbadd(String name, String seazone, String nation, String port, String info, String image, int vcap,
			int wcap, int cost) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Ship ship =new Ship();
        ship.setName(name);
        ship.setCost(cost);
        ship.setInfo(info);
        ship.setImage(image);
        ship.setNation(nation);
        ship.setPort(port);
        ship.setSeazone(seazone);
        ship.setVcap(vcap);
        ship.setWcap(wcap);
        System.out.println(name);
        Serializable result =session.save(ship);
        int id =(int)result;
        Ship s = (Ship)session.get(Ship.class, id);
        session.getTransaction().commit();
        session.close();
        return s;
	}

	@Override
	public boolean tbupdate(int id, String name, String seazone, String nation, String port, String info, String image,
			int vcap, int wcap, int cost) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Ship ship =new Ship();
        ship.setId(id);
        ship.setName(name);
        ship.setCost(cost);
        ship.setInfo(info);
        ship.setImage(image);
        ship.setNation(nation);
        ship.setPort(port);
        ship.setSeazone(seazone);
        ship.setVcap(vcap);
        ship.setWcap(wcap);
        System.out.println(name);
        session.update(ship);
        session.getTransaction().commit();
        session.close();
        return true;
	}
	
}
