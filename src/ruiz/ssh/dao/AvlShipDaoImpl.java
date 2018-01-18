package ruiz.ssh.dao;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import ruiz.ssh.model.AvlShip;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AvlShipDaoImpl implements AvlShipDao {
	 private SessionFactory sessionFactory;
	    
	 public void setSessionFactory(SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	 }
	    
	
	@Override
	public boolean addAvlShip(AvlShip avlship) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(avlship);
        session.getTransaction().commit();
        session.close();
		return true;
	}

	@Override
	public boolean deleteAvlShip(int id) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();

        AvlShip avlship = (AvlShip)session.get(AvlShip.class, id);
        session.delete(avlship);
        session.getTransaction().commit();
        session.close();
		return true;
	}

	@Override
	public boolean updateAvlShip(AvlShip avlship) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(avlship);
        session.getTransaction().commit();
        session.close();
        return true;
	}

	@Override
	public AvlShip queryById(int id) {
		 Session session = sessionFactory.openSession();
	     session.beginTransaction();
	     AvlShip avlship = (AvlShip)session.get(AvlShip.class, id);
	     session.getTransaction().commit();
	     session.close();
	     return avlship;
	}

	@Override
	public List<AvlShip> queryAll() {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from AvlShip avlship");
        List<AvlShip> avlships = query.list();
        session.getTransaction().commit();
        session.close();
        return avlships;
	}


	@Override
	public List<Map<String, Object>> querybyschid(String schid) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
       // Query query = session.createQuery("select new map(ship.id as id,ship.vcap as vcap,ship.wcap as wcap,ship.cost as cost,avlship.shipx as x,avlship.shipy as y) from AvlShip avlship,Ship ship ");
       // where avlship.shipid = ship.id
        Query query = session.createQuery("select new map(avlship.shipid as id,ship.vcap as vcap,ship.wcap as wcap,ship.cost as cost,avlship.x as x,avlship.y as y) from AvlShip avlship,Ship ship where avlship.shipid = ship.id and avlship.schid="+schid);
         
        List<Map<String, Object>> list  = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
	}
	
	
}
