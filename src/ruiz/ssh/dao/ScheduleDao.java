package ruiz.ssh.dao;

import java.util.List;

import ruiz.ssh.model.Schedule;

public interface ScheduleDao {
	public Schedule queryById(int id);
    public boolean add(Schedule obj);
    public boolean update(Schedule obj);
    public boolean delete(int id);
    public List<Schedule> getAll();
    public List<Schedule> match(String s);
    
    public Schedule tbadd(int id,String name,String info,String note 
    		,String userid,String starttime,String endtime);
    public boolean tbupdate(int id,String name,String info,String note 
    		,String userid,String starttime,String endtime);

}
