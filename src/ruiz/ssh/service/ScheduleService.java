package ruiz.ssh.service;

import java.util.List;

import ruiz.ssh.model.Schedule;

public interface ScheduleService {
	public boolean addSchedule(Schedule sch);
    public boolean deleteSchedule(int id);
    public boolean updateSchedule(Schedule sch);
    //public List<User> queryUser(User user);
    public  Schedule  queryById(int id);
    public List<Schedule> queryAll();
    public List<Schedule> match(String s);
    
    public Schedule tbadd(int id,String name,String info,String note 
    		,String userid,String starttime,String endtime);
	public boolean tbupdate(int id,String name,String info,String note 
    		,String userid,String starttime,String endtime);

}
