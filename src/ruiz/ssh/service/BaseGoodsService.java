package ruiz.ssh.service;

import java.util.List;
import java.util.Map;

import ruiz.ssh.model.BaseGoods;

public interface BaseGoodsService {
	//public boolean checkBaseGoods(User user);
    public boolean addBaseGoods(BaseGoods basegoods);
    public boolean deleteBaseGoods(int id);
    public boolean updateBaseGoods(BaseGoods basegoods);
    
    public List<BaseGoods> queryAll();
    public List<Map<String, Object>> querybybaseid(int baseid);
    
    public List<Map<String, Object>> tbqueryById(int id);
    public List<Map<String, Object>> tbqueryByBaseId(int baseid);
    
    public List<Map<String, Object>> querygoods();
    
    public Map<String, Object> tbadd(int materialid, int amount,int baseid) ;
    public boolean tbupdate(int materialid, int amount,int basegoodsid) ;
}
