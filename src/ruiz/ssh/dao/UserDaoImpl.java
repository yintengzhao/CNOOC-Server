package ruiz.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ruiz.ssh.model.User;

public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public boolean checkUser(User user) {
        // TODO Auto-generated method stub
       
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Query query = session.createQuery(" from User u where u.username=:username and u.password=:password");

        //query.setParameter("id", user.getId());
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());
        
        User u = (User)query.uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (null != u ) {
            return true;
        }

        return false;
    }
    
    @Override
    public boolean addUser(User user) {
        // TODO Auto-generated method stub

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean deleteUser(int id) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User u = (User)session.get(User.class, id);
        session.delete(u);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<User> queryUser(User user) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        StringBuffer hq = new StringBuffer(" from User u where 1=1");

        ArrayList<String> params = new ArrayList<String>();

        if (user.getUsername() != null && !"".equals(user.getUsername())) {
            hq.append(" and u.username=?");
            params.add(user.getUsername());
        }

        if (user.getEmail() != null && !"".equals(user.getEmail())) {
            hq.append(" and u.email=?");
            params.add(user.getEmail());
        }

        Query query = session.createQuery(hq.toString());
        for (int i = 0; i < params.size(); i++) {
            query.setString(i, params.get(i));
        }
        List<User> users = query.list();

        session.getTransaction().commit();
        session.close();
        return users;
    }
    
    @Override
    public User queryById(int id) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User u = (User)session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        return u;
    }

    @Override
    public List<User> queryAll() {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from User u");
        List<User> users = query.list();
        session.getTransaction().commit();
        session.close();
        return users;
    }
}
