package ruiz.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ruiz.ssh.model.Material;
import ruiz.ssh.model.Schedule;
import ruiz.ssh.model.Ship;
import ruiz.ssh.model.User;

public class ScheduleDaoImpl implements ScheduleDao {
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	@Override
	public Schedule queryById(int id) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Schedule m where id ="+id);
        Schedule schedule =(Schedule)session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        return schedule;
	}

	@Override
	public boolean add(Schedule obj) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(obj);
        session.getTransaction().commit();
        session.close();
        return true;
	}

	@Override
	public boolean update(Schedule obj) {

		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Schedule s = (Schedule)session.get(Schedule.class, id);
        session.delete(s);
        session.getTransaction().commit();
        session.close();
        return true;
	}

	@Override
	public List<Schedule> getAll() {
		// TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Schedule u");
        List<Schedule> schedules = query.list();
        session.getTransaction().commit();
        session.close();
        return schedules;
	}

	@Override
	public List<Schedule> match(String s) {

		return null;
	}

	@Override
	public Schedule tbadd(int id, String name, String info, String note, String userid, String starttime,
			String endtime) {

		return null;
	}

	@Override
	public boolean tbupdate(int id, String name, String info, String note, String userid, String starttime,
			String endtime) {

		return false;
	}

}
