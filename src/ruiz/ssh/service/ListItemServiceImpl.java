package ruiz.ssh.service;

import java.util.List;
import java.util.Map;

import ruiz.ssh.dao.ListItemDao;
import ruiz.ssh.model.ListItem;

public class ListItemServiceImpl implements ListItemService {

	private ListItemDao listitemDao;
	
	public ListItemDao getListitemDao() {
		return listitemDao;
	}

	public void setListitemDao(ListItemDao listitemDao) {
		this.listitemDao = listitemDao;
	}

	@Override
	public boolean addListItem(ListItem listitem) {

		return listitemDao.addListItem(listitem);
	}

	@Override
	public boolean deleteListItem(int id) {

		return listitemDao.deleteListItem(id);
	}

	@Override
	public boolean updateListItem(ListItem listitem) {

		return listitemDao.updateListItem(listitem);
	}

	@Override
	public ListItem queryById(int id) {

		return listitemDao.queryById(id);
	}

	@Override
	public List<ListItem> queryAll() {

		return listitemDao.queryAll();
	}

	@Override
	public List<Map<String, Object>> queryBySchid(String schid) {
		
		return listitemDao.queryBySchid(schid);
	}

	
}
