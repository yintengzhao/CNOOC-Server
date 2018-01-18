package ruiz.ssh.service;

import java.util.List;
import java.util.Map;

import ruiz.ssh.model.ListItem;

public interface ListItemService {
	//public boolean checkAvlShip(User user);
    public boolean addListItem(ListItem listitem);
    public boolean deleteListItem(int id);
    public boolean updateListItem(ListItem listitem);
    //public List<User> queryUser(User user);
    public ListItem queryById(int id);
    public List<ListItem> queryAll();
    public List<Map<String, Object>> queryBySchid(String schid);
}
