package ruiz.ssh.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ruiz.ssh.model.User;
import ruiz.ssh.model.Weather;

public class WeatherDaoImpl implements WeatherDao  {
private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @SuppressWarnings("rawtypes")
   
  
    @Override
	public boolean add(Weather obj) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(obj);
        session.getTransaction().commit();
        session.close();
        return true;
	}
    @Override
	public boolean update(Weather obj) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
        session.close();
        return true;
	}

	@Override
	public boolean delete(int id) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();

        Weather u = (Weather)session.get(Weather.class, id);
        session.delete(u);
        session.getTransaction().commit();
        session.close();
        return true;
	}

	@Override
	public List<Weather> getAll() {

		Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Weather t";
        Query query = session.createQuery(hql);
        List<Weather> objs= query.list();
        session.getTransaction().commit();
        session.close();
        return objs;
	}
	
	@Override
	public List<Weather> match(String s){
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Weather t where t.name like '%"+s+"%'";
        Query query = session.createQuery(hql);
        List<Weather> objs= query.list();
        session.getTransaction().commit();
        session.close();
        return objs;
	}
	
	@Override
	public Weather queryById(int id){
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Weather w = (Weather)session.get(Weather.class, id);
        session.getTransaction().commit();
        session.close();
        return w;
	}
}
