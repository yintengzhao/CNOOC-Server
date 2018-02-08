package ruiz.ssh.dao;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ruiz.ssh.model.Material;
import ruiz.ssh.model.User;
import ruiz.ssh.model.Weather;

public class MaterialDaoImpl implements MaterialDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public List<Material> queryById(int id) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Material m where id ="+id);
        List<Material> materials = query.list();
        session.getTransaction().commit();
        session.close();
        return materials;
	}

	@Override
	public boolean add(Material obj) {
		
		return false;
	}

	@Override
	public boolean update(Material obj) {
		
		return false;
	}

	@Override
	public boolean delete(int id) {
		 // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Material m = (Material)session.get(Material.class, id);
        session.delete(m);
        session.getTransaction().commit();
        session.close();
        return true;
	}

	@Override
	public List<Material> getAll() {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Material m");
        List<Material> materials = query.list();
        session.getTransaction().commit();
        session.close();
        return materials;
	}

	@Override
	public List<Material> match(String s) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Material t where t.name like '%"+s+"%'";
        Query query = session.createQuery(hql);
        List<Material> objs= query.list();
        session.getTransaction().commit();
        session.close();
        return objs;
	}

	@Override
	public Material tbadd(String name, String type, String detail, double weight, double volume) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Material material = new Material();
        material.setDetail(detail);
        material.setName(name);
        material.setType(type);
        material.setVolume(volume);
        material.setWeight(weight);
        Serializable result =session.save(material);
        int id =(int)result;
        Material m = (Material)session.get(Material.class, id);
        session.getTransaction().commit();
        session.close();
        return m;
	}

	@Override
	public boolean tbupdate(int id, String name, String type, String detail, double weight, double volume) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Material material = new Material();
        material.setId(id);
        material.setDetail(detail);
        material.setName(name);
        material.setType(type);
        material.setVolume(volume);
        material.setWeight(weight);
        session.update(material);
        session.getTransaction().commit();
        session.close();
        return true;
	}

}
