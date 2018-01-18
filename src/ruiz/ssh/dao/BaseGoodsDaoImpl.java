package ruiz.ssh.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ruiz.ssh.model.AvlShip;
import ruiz.ssh.model.BaseGoods;
import ruiz.ssh.model.Material;

public class BaseGoodsDaoImpl implements BaseGoodsDao {

	 private SessionFactory sessionFactory;
	    
	 public void setSessionFactory(SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	 }
	    
	 
	@Override
	public boolean addBaseGoods(BaseGoods basegoods) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(basegoods);
        session.getTransaction().commit();
        session.close();
		return true;
	}

	@Override
	public boolean deleteBaseGoods(int id) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();

        BaseGoods basegoods = (BaseGoods)session.get(BaseGoods.class, id);
        session.delete(basegoods);
        session.getTransaction().commit();
        session.close();
		return true;
	}

	@Override
	public boolean updateBaseGoods(BaseGoods basegoods) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(basegoods);
        session.getTransaction().commit();
        session.close();
        return true;
	}

	@Override
	public List<Map<String, Object>> tbqueryById(int basegoodsid) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println(basegoodsid);
        Query query = session.createQuery
        		("select new map(basegoods.id as basegoodsid,material.id as materialid ,material.name as name ,material.type as type,basegoods.amount as amount , material.weight as weight ,material.volume as volume)"
        		+" from BaseGoods basegoods,Material material"
        		+" where basegoods.materialid = material.id"
        		+" and basegoods.id = "+basegoodsid);
        
        List<Map<String, Object>> list  = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
	}

	@Override
	public List<Map<String, Object>> tbqueryByBaseId(int baseid) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery
        		("select new map(basegoods.id as basegoodsid,material.id as materialid ,material.name as name ,material.type as type,basegoods.amount as amount , material.weight as weight ,material.volume as volume)"
        		+" from BaseGoods basegoods,Material material"
        		+" where basegoods.materialid = material.id"
        		+" and basegoods.baseid = "+baseid);
        
        List<Map<String, Object>> list  = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
	}


	@Override
	public List<BaseGoods> queryAll() {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from BaseGoods basegoods");
        List<BaseGoods> basegoods = query.list();
        session.getTransaction().commit();
        session.close();
        return basegoods;
	}

	@Override
	public List<Map<String, Object>> querybybaseid(int baseid) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery
        		("select new map(material.id as goodsid ,basegoods.amount as amount )"
        		+" from BaseGoods basegoods,Material material"
        		+" where basegoods.materialid = material.id"
        		+" and basegoods.baseid = "+baseid);
        
        List<Map<String, Object>> list  = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
	}


	@Override
	public List<Map<String, Object>> querygoods() {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery
        		("select new map(material.id as id ,material.name as name, material.volume as v,material.weight as w )"
        		+" from Material material"
        		//+" where basegoods.materialid = material.id"
        		//+" and basegoods.baseid = "+baseid
        		);
        
        List<Map<String, Object>> list  = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
	}
//


	@Override
	public List<Map<String, Object>> tbadd(int materialid, int amount,int baseid) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Material m = (Material)session.get(Material.class, materialid);
        BaseGoods bg = new BaseGoods();
        bg.setBaseid(baseid);
        bg.setMaterialid(materialid);
        bg.setAmount(amount);
        Serializable result =session.save(bg);
        int id =(int)result;
        session.getTransaction().commit();
        session.close();
        List<Map<String, Object>> list = tbqueryById(id);
        return list;
	}


	@Override
	public boolean tbupdate(int materialid, int amount,int basegoodsid) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        BaseGoods bg = new BaseGoods();
        bg.setId(basegoodsid);
        bg.setMaterialid(materialid);
        bg.setAmount(amount);
        session.update(bg);
        session.getTransaction().commit();
        session.close();
        return true;
	}
	
	

}
