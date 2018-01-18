package ruiz.ssh.service;

import java.util.List;
import java.util.Map;

import ruiz.ssh.dao.BaseGoodsDao;
import ruiz.ssh.dao.MaterialDao;
import ruiz.ssh.model.BaseGoods;
import ruiz.ssh.model.Material;

public class BaseGoodsServiceImpl implements BaseGoodsService {

	private BaseGoodsDao basegoodsDao;
	private MaterialDao materialDao;
	
	public MaterialDao getMaterialDao() {
		return materialDao;
	}

	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	public BaseGoodsDao getBasegoodsDao() {
		return basegoodsDao;
	}

	public void setBasegoodsDao(BaseGoodsDao basegoodsDao) {
		this.basegoodsDao = basegoodsDao;
	}

	@Override
	public boolean addBaseGoods(BaseGoods basegoods) {

		return basegoodsDao.addBaseGoods(basegoods);
	}

	@Override
	public boolean deleteBaseGoods(int id) {

		return basegoodsDao.deleteBaseGoods(id);
	}

	@Override
	public boolean updateBaseGoods(BaseGoods basegoods) {

		return basegoodsDao.updateBaseGoods(basegoods);
	}

	
	@Override
	public List<Map<String, Object>> tbqueryById(int id) {
		
		return basegoodsDao.tbqueryById(id);
	}

	@Override
	public List<Map<String, Object>> tbqueryByBaseId(int baseid) {
		
		return basegoodsDao.tbqueryByBaseId(baseid);
	}

	@Override
	public List<BaseGoods> queryAll() {

		return basegoodsDao.queryAll();
	}

	@Override
	public List<Map<String, Object>> querybybaseid(int baseid) {

		return basegoodsDao.querybybaseid(baseid);
	}

	@Override
	public List<Map<String, Object>> querygoods() {
		
		return basegoodsDao.querygoods();
	}

	@Override
	public Map<String, Object> tbadd(int materialid, int amount,int baseid) {
		
		return basegoodsDao.tbadd(materialid, amount, baseid).get(0);
	}

	@Override
	public boolean tbupdate(int materialid, int amount,int basegoodsid) {
		
		return basegoodsDao.tbupdate(materialid, amount, basegoodsid);
	}
	
}
