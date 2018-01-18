package ruiz.ssh.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ruiz.ssh.model.Base;
import ruiz.ssh.model.Material;
import ruiz.ssh.model.User;
import ruiz.ssh.model.Weather;

public class BaseDaoImpl implements BaseDao {
	
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public boolean addBase(Base base) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(base);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public boolean deleteBase(int id) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();

        Base b = (Base)session.get(Base.class, id);
        session.delete(b);
        session.getTransaction().commit();
        session.close();
        return true;
	}

	@Override
	public boolean updateBase(Base base) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(base);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public List<Base> queryBase(Base base) {

		Session session = sessionFactory.openSession();
        session.beginTransaction();

        StringBuffer hq = new StringBuffer(" from Base b where 1=1");

        ArrayList<String> params = new ArrayList<String>();

        if (base.getName() != null && !"".equals(base.getName())) {
            hq.append(" and b.name=?");
            params.add(base.getName());
        }

        Query query = session.createQuery(hq.toString());
        for (int i = 0; i < params.size(); i++) {
            query.setString(i, params.get(i));
        }
        List<Base> bases = query.list();

        session.getTransaction().commit();
        session.close();
        return bases;
	}

	@Override
	public List<Base> queryById(int id) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Base u where id ="+id);
        List<Base> bases = query.list();
        session.getTransaction().commit();
        session.close();
        return bases;
	}

	
	@Override
	public Base query(int id) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Base w = (Base)session.get(Base.class, id);
        session.getTransaction().commit();
        session.close();
        return w;
	}

	@Override
	public List<Base> match(String s) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Base t where t.name like '%"+s+"%'";
        Query query = session.createQuery(hql);
        List<Base> objs= query.list();
        session.getTransaction().commit();
        session.close();
        return objs;
	}

	@Override
	public List<Base> queryAll() {

		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Base u");
        List<Base> bases = query.list();
        session.getTransaction().commit();
        session.close();
        return bases;
	}

	@Override
	public boolean updatexy(int id, int x, int y) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery
        		("update Base b" + 
        		" set b.x=:x,b.y=:y where b.id=:id");
        query.setParameter("x", x);
        query.setParameter("y", y);
        query.setParameter("id", id);
        query.executeUpdate();  

		System.out.println(x+" "+y+" "+id);

        session.getTransaction().commit();
        session.close();
        return true;
	}

	@Override
	public Base tbadd(String name, int area, int x, int y) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Base b = new Base();
        b.setName(name);
        b.setArea(area);
        b.setX(x);
        b.setY(y);
        Serializable result =session.save(b);
        int id =(int)result;
        Base m = (Base)session.get(Base.class, id);
        session.getTransaction().commit();
        session.close();
        return m;
	}

	@Override
	public boolean tbupdate(int id, String name, int area, int x, int y) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Base b = new Base();
        b.setArea(area);
        b.setId(id);
        b.setX(x);
        b.setY(y);
        b.setName(name);
        session.update(b);
        session.getTransaction().commit();
        session.close();
        return true;
	}
	
	
}
