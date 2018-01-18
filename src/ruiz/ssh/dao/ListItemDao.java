package ruiz.ssh.dao;

import java.util.List;
import java.util.Map;

import ruiz.ssh.model.ListItem;;

public interface ListItemDao {
	 //public boolean checkListItem(User listitem);
    
    public boolean addListItem(ListItem listitem);
    
    public boolean deleteListItem(int id);
    
    public boolean updateListItem(ListItem listitem);

  //public List<AvlShip> queryListItem(ListItem user);
    
    public ListItem queryById(int id);

    public List<ListItem> queryAll();
    
    public List<Map<String, Object>>  queryBySchid(String schid);
}
