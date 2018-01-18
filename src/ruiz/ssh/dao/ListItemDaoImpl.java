package ruiz.ssh.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ruiz.ssh.model.AvlShip;
import ruiz.ssh.model.ListItem;
import ruiz.ssh.model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ListItemDaoImpl implements ListItemDao {

	private SessionFactory sessionFactory;
    
	 public void setSessionFactory(SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	 }
	
	@Override
	public boolean addListItem(ListItem listitem) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(listitem);
        session.getTransaction().commit();
        session.close();
		return true;
	}

	@Override
	public boolean deleteListItem(int id) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();

        ListItem listitem = (ListItem)session.get(ListItem.class, id);
        session.delete(listitem);
        session.getTransaction().commit();
        session.close();
		return true;
	}

	@Override
	public boolean updateListItem(ListItem listitem) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(listitem);
        session.getTransaction().commit();
        session.close();
        return true;
	}

	@Override
	public ListItem queryById(int id) {
		 Session session = sessionFactory.openSession();
	     session.beginTransaction();
	     ListItem listitem = (ListItem)session.get(ListItem.class, id);
	     session.getTransaction().commit();
	     session.close();
	     return listitem;
	}

	@Override
	public List<ListItem> queryAll() {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from ListItem listitem");
        List<ListItem> listitems = query.list();
        session.getTransaction().commit();
        session.close();
        return listitems;
	}

	@Override
	public List<Map<String, Object>> queryBySchid(String schid) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "select new map(materialid as id,num as amount)  from ListItem i where i.schid = "+ schid;
        Query query = session.createQuery(hql);
        List<Map<String, Object>> list  = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
	}
	

}
