package ruiz.ssh.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DaoImpl<T> implements Dao<T> {
	
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @SuppressWarnings("rawtypes")
    private Class cls;
    
    public DaoImpl() {
        this.cls = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
	@Override
	public List<T> queryById(int id) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from " + cls.getName()+" t"+" where t.id = "+ id;
        Query query = session.createQuery(hql);
        List<T> objs= query.list();
        session.getTransaction().commit();
        session.close();
        return objs;
	}

	@Override
	public boolean add(T obj) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(obj);
        session.getTransaction().commit();
        session.close();
        return true;
	}

	@Override
	public boolean update(T obj) {
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

        T u = (T)session.get(cls, id);
        session.delete(u);
        session.getTransaction().commit();
        session.close();
        return true;
	}

	@Override
	public List<T> getAll() {

		Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from " + cls.getName()+" t";
        Query query = session.createQuery(hql);
        List<T> objs= query.list();
        session.getTransaction().commit();
        session.close();
        return objs;
	}
	
	@Override
	public List<T> match(String s){
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from " + cls.getName()+" t where t.name like '%"+s+"%'";
        Query query = session.createQuery(hql);
        List<T> objs= query.list();
        session.getTransaction().commit();
        session.close();
        return objs;
	}

}
